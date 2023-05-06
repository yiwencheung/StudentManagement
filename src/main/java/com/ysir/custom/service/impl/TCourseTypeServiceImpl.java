package com.ysir.custom.service.impl;

import java.util.Date;
import java.util.List;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysir.custom.mapper.TCourseTypeMapper;
import com.ysir.custom.entity.TCourseType;
import com.ysir.custom.service.ITCourseTypeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程性质管理Service业务层处理
 *
 */
@Service
@Transactional
public class TCourseTypeServiceImpl implements ITCourseTypeService {

    @Autowired
    private TCourseTypeMapper tCourseTypeMapper;

    /**
     * 查询课程性质管理
     * 
     * @param id 课程性质管理主键
     * @return 课程性质管理
     */
    @Override
    public TCourseType selectTCourseTypeById(Long id) {
        return tCourseTypeMapper.selectTCourseTypeById(id);
    }

    /**
     * 查询课程性质管理列表
     * 
     * @param tCourseType 课程性质管理
     * @return 课程性质管理
     */
    @Override
    public List<TCourseType> selectTCourseTypeList(TCourseType tCourseType) {
        return tCourseTypeMapper.selectTCourseTypeList(tCourseType);
    }

    /**
     * 新增课程性质管理
     * 
     * @param tCourseType 课程性质管理
     * @return 结果
     */

    @Override
    public int insertTCourseType(TCourseType tCourseType) {
        tCourseType.setCreateTime(new Date());
        return tCourseTypeMapper.insertTCourseType(tCourseType);
    }

    /**
     * 修改课程性质管理
     * 
     * @param tCourseType 课程性质管理
     * @return 结果
     */

    @Override
    public int updateTCourseType(TCourseType tCourseType) {
        tCourseType.setUpdateTime(new Date());
        return tCourseTypeMapper.updateTCourseType(tCourseType);
    }

    /**
     * 批量删除课程性质管理
     * 
     * @param ids 需要删除的课程性质管理主键
     * @return 结果
     */

    @Override
    public int deleteTCourseTypeByIds(Long[] ids) {
        return tCourseTypeMapper.deleteTCourseTypeByIds(ids);
    }

    /**
     * 删除课程性质管理信息
     * 
     * @param id 课程性质管理主键
     * @return 结果
     */

    @Override
    public int deleteTCourseTypeById(Long id) {
        return tCourseTypeMapper.deleteTCourseTypeById(id);
    }
}
