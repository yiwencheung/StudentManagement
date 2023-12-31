package com.ysir.custom.service;

import java.util.List;

import com.ysir.custom.common.AjaxResult;
import com.ysir.custom.entity.TUserCourse;

/**
 * 学生选课管理Service接口
 *
 */
public interface ITUserCourseService {

    /**
     * 查询学生选课管理
     * 
     * @param id 学生选课管理主键
     * @return 学生选课管理
     */
    TUserCourse selectTUserCourseById(Long id);

    /**
     * 查询学生选课管理列表
     * 
     * @param tUserCourse 学生选课管理
     * @return 学生选课管理集合
     */
    List<TUserCourse> selectTUserCourseList(TUserCourse tUserCourse);

    /**
     * 新增学生选课管理
     * 
     * @param tUserCourse 学生选课管理
     * @return 影响行数量
     */
    int insertTUserCourse(TUserCourse tUserCourse);

    /**
     * 修改学生选课管理
     * 
     * @param tUserCourse 学生选课管理
     * @return 影响行数量
     */
    int updateTUserCourse(TUserCourse tUserCourse);

    /**
     * 批量删除学生选课管理
     * 
     * @param ids 需要删除的学生选课管理主键集合
     * @return 影响行数量
     */
    AjaxResult deleteTUserCourseByIds(Long[] ids);

    /**
     * 删除学生选课管理信息
     * 
     * @param id 学生选课管理主键
     * @return 影响行数量
     */
    int deleteTUserCourseById(Long id);

    AjaxResult addBatch(List<Long> ids);

    AjaxResult paySuccess(Long[] ids);
}
