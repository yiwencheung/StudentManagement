package com.ysir.custom.mapper;

import java.util.List;
import com.ysir.custom.entity.TUserCourse;

/**
 * 学生选课管理Mapper接口
 *
 */
public interface TUserCourseMapper {

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
     * 删除学生选课管理
     * 
     * @param id 学生选课管理主键
     * @return 影响行数量
     */
     int deleteTUserCourseById(Long id);

    /**
     * 批量删除学生选课管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 影响行数量
     */
     int deleteTUserCourseByIds(Long[] ids);
}
