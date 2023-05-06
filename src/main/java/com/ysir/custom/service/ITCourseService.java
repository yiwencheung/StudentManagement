package com.ysir.custom.service;

import java.util.List;
import com.ysir.custom.entity.TCourse;

/**
 * 课程管理Service接口
 *
 */
public interface ITCourseService {

    /**
     * 查询课程管理
     * 
     * @param id 课程管理主键
     * @return 课程管理
     */
    TCourse selectTCourseById(Long id);

    /**
     * 查询课程管理列表
     * 
     * @param tCourse 课程管理
     * @return 课程管理集合
     */
    List<TCourse> selectTCourseList(TCourse tCourse);

    /**
     * 新增课程管理
     * 
     * @param tCourse 课程管理
     * @return 影响行数量
     */
    int insertTCourse(TCourse tCourse);

    /**
     * 修改课程管理
     * 
     * @param tCourse 课程管理
     * @return 影响行数量
     */
    int updateTCourse(TCourse tCourse);

    /**
     * 批量删除课程管理
     * 
     * @param ids 需要删除的课程管理主键集合
     * @return 影响行数量
     */
    int deleteTCourseByIds(Long[] ids);

    /**
     * 删除课程管理信息
     * 
     * @param id 课程管理主键
     * @return 影响行数量
     */
    int deleteTCourseById(Long id);
}
