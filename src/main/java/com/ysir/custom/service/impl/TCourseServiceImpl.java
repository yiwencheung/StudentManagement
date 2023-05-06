package com.ysir.custom.service.impl;

import java.util.Date;
import java.util.List;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysir.custom.mapper.TCourseMapper;
import com.ysir.custom.entity.TCourse;
import com.ysir.custom.service.ITCourseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程管理Service业务层处理
 *
 */
@Service
@Transactional
public class TCourseServiceImpl implements ITCourseService {

    @Autowired
    private TCourseMapper tCourseMapper;

    /**
     * 查询课程管理
     * 
     * @param id 课程管理主键
     * @return 课程管理
     */
    @Override
    public TCourse selectTCourseById(Long id) {
        return tCourseMapper.selectTCourseById(id);
    }

    /**
     * 查询课程管理列表
     * 
     * @param tCourse 课程管理
     * @return 课程管理
     */
    @Override
    public List<TCourse> selectTCourseList(TCourse tCourse) {
        return tCourseMapper.selectTCourseList(tCourse);
    }

    /**
     * 新增课程管理
     * 
     * @param tCourse 课程管理
     * @return 结果
     */

    @Override
    public int insertTCourse(TCourse tCourse) {
        tCourse.setCreateTime(new Date());
        return tCourseMapper.insertTCourse(tCourse);
    }

    /**
     * 修改课程管理
     * 
     * @param tCourse 课程管理
     * @return 结果
     */

    @Override
    public int updateTCourse(TCourse tCourse) {
        tCourse.setUpdateTime(new Date());
        return tCourseMapper.updateTCourse(tCourse);
    }

    /**
     * 批量删除课程管理
     * 
     * @param ids 需要删除的课程管理主键
     * @return 结果
     */

    @Override
    public int deleteTCourseByIds(Long[] ids) {
        return tCourseMapper.deleteTCourseByIds(ids);
    }

    /**
     * 删除课程管理信息
     * 
     * @param id 课程管理主键
     * @return 结果
     */

    @Override
    public int deleteTCourseById(Long id) {
        return tCourseMapper.deleteTCourseById(id);
    }
}
