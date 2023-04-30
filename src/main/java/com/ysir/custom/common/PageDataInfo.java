package com.ysir.custom.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 */
@Data
public class PageDataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 表格数据对象
     */
    public PageDataInfo() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }


}
