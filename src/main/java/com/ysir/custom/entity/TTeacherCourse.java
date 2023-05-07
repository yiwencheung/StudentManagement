package com.ysir.custom.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysir.custom.common.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 教师课程管理对象 t_teacher_course
 *
 */
@Data
public class TTeacherCourse extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 选课编号 */
    private Long id;

    /** 教师编号 */
    private Long teacherId;

    /** 教师名称 */
    private String teacherName;

    /** 课程编号 */
    private Long courseId;

    /** 课程名称 */
    private String courseName;

    /** 可容纳学生数 */
    private Long studentNum;

    /** 已选择学生数 */
    private Long selectedNum;

    private BigDecimal credit;

    private BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
