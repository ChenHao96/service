package com.github.chenhao96.model.bo;

public class PageBo {

    private Integer page;
    private Integer rows;
    private String sort = "asc";
    private String order;
    private boolean like;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void process() {

        Integer page = getPage();
        Integer rows = getRows();
        if(page==null||rows==null)return;

        if (page < 1) {
            page = 1;
        }
        setRows(rows * page);

        page--;
        setLike(true);
        setPage((page * rows) + 1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageBo{");
        sb.append("page=").append(page);
        sb.append(", rows=").append(rows);
        sb.append(", sort='").append(sort).append('\'');
        sb.append(", order='").append(order).append('\'');
        sb.append(", like=").append(like);
        sb.append('}');
        return sb.toString();
    }
}
