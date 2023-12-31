package com.ysir.custom.common;

import lombok.Data;

@Data
public class PageDomain {


    private static final long serialVersionUID = 1L;


    /**
     * 当前记录起始索引
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String orderBy;
}
