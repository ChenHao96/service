package com.github.chenhao96.model.vo;

import java.util.List;

public class PageVo<T> {

    private List<T> rows;
    private long total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageVo{");
        sb.append("rows=").append(rows);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
