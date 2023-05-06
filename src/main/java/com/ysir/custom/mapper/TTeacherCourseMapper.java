package com.ysir.custom.mapper;

import java.util.List;
import com.ysir.custom.entity.TTeacherCourse;

/**
 * 教师课程管理Mapper接口
 *
 */
public interface TTeacherCourseMapper {

    /**
     * 查询教师课程管理
     * 
     * @param id 教师课程管理主键
     * @return 教师课程管理
     */
     TTeacherCourse selectTTeacherCourseById(Long id);

    /**
     * 查询教师课程管理列表
     * 
     * @param tTeacherCourse 教师课程管理
     * @return 教师课程管理集合
     */
     List<TTeacherCourse> selectTTeacherCourseList(TTeacherCourse tTeacherCourse);

    /**
     * 新增教师课程管理
     * 
     * @param tTeacherCourse 教师课程管理
     * @return 影响行数量
     */
     int insertTTeacherCourse(TTeacherCourse tTeacherCourse);

    /**
     * 修改教师课程管理
     * 
     * @param tTeacherCourse 教师课程管理
     * @return 影响行数量
     */
     int updateTTeacherCourse(TTeacherCourse tTeacherCourse);

    /**
     * 删除教师课程管理
     * 
     * @param id 教师课程管理主键
     * @return 影响行数量
     */
     int deleteTTeacherCourseById(Long id);

    /**
     * 批量删除教师课程管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 影响行数量
     */
     int deleteTTeacherCourseByIds(Long[] ids);
}
