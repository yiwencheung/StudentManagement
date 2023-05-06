package com.ysir.custom.entity;

import com.ysir.custom.common.BaseEntity;
import lombok.Data;

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

}
