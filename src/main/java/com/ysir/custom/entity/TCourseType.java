package com.ysir.custom.entity;

import com.ysir.custom.common.BaseEntity;
import lombok.Data;

/**
 * 课程性质管理对象 t_course_type
 *
 */
@Data
public class TCourseType extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 课程性质 */
    private String name;

}
