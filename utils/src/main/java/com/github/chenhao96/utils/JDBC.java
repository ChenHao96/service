package com.github.chenhao96.utils;

import com.github.chenhao96.utils.exception.JdbcSqlException;

import java.sql.*;
import java.util.*;

public class JDBC {

    private Connection connection = null;

    public JDBC(String driverClass, String url, String userName, String password) {

        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            throw new JdbcSqlException(e);
        }
    }

    public Map<String, Object> querySingleton(String sql, Object... params) {

        List<Map<String, Object>> result = query(sql, params);
        if (result == null || result.size() == 0) return null;
        if (result.size() > 0) {
            throw new JdbcSqlException(String.format("Many Result! query size:%d", result.size()));
        }

        return result.get(0);
    }

    public List<Map<String, Object>> query(String sql, Object... params) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = addPsParams(connection, sql, params);
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            Set<String> columnNames = new HashSet<>(columnCount);
            for (int i = 0; i < columnCount; i++) {
                columnNames.add(resultSetMetaData.getColumnName(i + 1));
            }
            int listSize = resultSet.getFetchSize();
            List<Map<String, Object>> result = new ArrayList<>(listSize);

            while (resultSet.next()) {
                Map<String, Object> vo = new HashMap<>(columnCount);
                for (String column : columnNames) {
                    vo.put(column, resultSet.getObject(column));
                }
                result.add(vo);
            }

            return result;
        } catch (Exception e) {
            throw new JdbcSqlException(e);
        } finally {
            safeClose(resultSet, preparedStatement);
        }
    }

    public int update(String sql, Object... params) {

        int result = 0;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = addPsParams(connection, sql, params);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new JdbcSqlException(e);
        } finally {
            safeClose(preparedStatement);
        }

        return result;
    }

    public int prepareCallUpdate(String sql, Object... params) {

        int result = 0;
        CallableStatement callableStatement = null;

        try {
            callableStatement = addPsParams4Call(connection, sql, params);
            result = callableStatement.executeUpdate();
        } catch (Exception e) {
            throw new JdbcSqlException(e);
        } finally {
            safeClose(callableStatement);
        }

        return result;
    }

    private static CallableStatement addPsParams4Call(Connection connection, String sql, Object... params) throws SQLException {
        checkConnection(connection);
        CallableStatement callableStatement = connection.prepareCall(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                callableStatement.setObject(i + 1, params[i]);
            }
        }
        return callableStatement;
    }


    private static PreparedStatement addPsParams(Connection connection, String sql, Object... params) throws SQLException {
        checkConnection(connection);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement;
    }

    private static void checkConnection(Connection connection) throws SQLException {
        if (connection == null) {
            throw new IllegalArgumentException("connection can not be null.");
        }
        if (connection.isClosed()) {
            throw new SQLException("connection is closed!");
        }
    }

    private static void safeClose(AutoCloseable... closeables) {
        if (closeables != null && closeables.length > 0) {
            for (AutoCloseable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                        throw new JdbcSqlException(e);
                    }
                }
            }
        }
    }

    public void commit() {
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new JdbcSqlException(e);
        }
    }

    public void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new JdbcSqlException(e);
        }
    }

    public void close() {
        safeClose(connection);
    }
}
