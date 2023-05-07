package com.ysir.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysir.custom.common.BaseEntity;
import lombok.Data;

/**
 * 课程管理对象 t_course
 *
 */
@Data
public class TCourse extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long id;

    /** 课程名称 */
    private String name;

    /** 课程性质编号 */
    private Long typeId;

    /** 课程性质 */
    private String typeName;

    /** 总学时 */
    private Long totalPeriod;

    /** 授课学时 */
    private Long teachingPeriod;

    /** 上机学时 */
    private Long computerPeriod;

    /** 实验学时 */
    private Long experimentalPeriod;

    /** 学分 */
    private BigDecimal credit;

    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
