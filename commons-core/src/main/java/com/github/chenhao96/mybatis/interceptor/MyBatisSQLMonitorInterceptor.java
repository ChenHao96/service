package com.github.chenhao96.mybatis.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@Intercepts(
        {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MyBatisSQLMonitorInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisSQLMonitorInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        String sqlMappingID = "";
        int transactionID = 0;
        try {
            Transaction transaction = ((Executor) invocation.getTarget()).getTransaction();
            transactionID = transaction.hashCode();
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            sqlMappingID = mappedStatement.getId();
        } catch (Throwable e) {
            logger.warn("get db url from invocation error !", e);
        }

        long beginTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            logger.debug("SQL:[id:{}][ts:{}][cost:{}ms] ", sqlMappingID, transactionID, endTime - beginTime);
        }
        return result;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}
