package com.ysir.custom.service;

import java.util.List;
import com.ysir.custom.entity.TCourseType;

/**
 * 课程性质管理Service接口
 *
 */
public interface ITCourseTypeService {

    /**
     * 查询课程性质管理
     * 
     * @param id 课程性质管理主键
     * @return 课程性质管理
     */
    TCourseType selectTCourseTypeById(Long id);

    /**
     * 查询课程性质管理列表
     * 
     * @param tCourseType 课程性质管理
     * @return 课程性质管理集合
     */
    List<TCourseType> selectTCourseTypeList(TCourseType tCourseType);

    /**
     * 新增课程性质管理
     * 
     * @param tCourseType 课程性质管理
     * @return 影响行数量
     */
    int insertTCourseType(TCourseType tCourseType);

    /**
     * 修改课程性质管理
     * 
     * @param tCourseType 课程性质管理
     * @return 影响行数量
     */
    int updateTCourseType(TCourseType tCourseType);

    /**
     * 批量删除课程性质管理
     * 
     * @param ids 需要删除的课程性质管理主键集合
     * @return 影响行数量
     */
    int deleteTCourseTypeByIds(Long[] ids);

    /**
     * 删除课程性质管理信息
     * 
     * @param id 课程性质管理主键
     * @return 影响行数量
     */
    int deleteTCourseTypeById(Long id);
}
