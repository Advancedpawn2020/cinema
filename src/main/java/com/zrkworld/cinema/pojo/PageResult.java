package com.zrkworld.cinema.pojo;

import java.util.List;

/**
 * 会员分页数据的bean
 * @author zrk
 */
public class PageResult<T> {
    private Long pageTotal;
    private List<T> list;

    public Long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
