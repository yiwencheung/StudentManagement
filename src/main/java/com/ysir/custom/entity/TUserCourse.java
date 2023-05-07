package com.ysir.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysir.custom.common.BaseEntity;
import lombok.Data;

/**
 * 学生选课管理对象 t_user_course
 *
 */
@Data
public class TUserCourse extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 学生编号 */
    private Long userId;

    /** 学生姓名 */
    private String userName;

    /** 选课编号 */
    private Long teacherCourseId;

    /** 课程编号 */
    private Long courseId;

    /** 课程名称 */
    private String courseName;

    /** 教师编号 */
    private Long teacherId;

    /** 教师名称 */
    private String teacherName;

    /** 课程学分 */
    private BigDecimal credit;

    /** 课程成绩 */
    private BigDecimal score;

    /** 考试状态 */
    private Long examStatus;

    /** 支付状态 */
    private Long payStatus;

    private BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
