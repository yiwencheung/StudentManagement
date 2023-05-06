package com.ysir.custom.service.impl;

import java.util.Date;
import java.util.List;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysir.custom.mapper.TUserCourseMapper;
import com.ysir.custom.entity.TUserCourse;
import com.ysir.custom.service.ITUserCourseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生选课管理Service业务层处理
 *
 */
@Service
@Transactional
public class TUserCourseServiceImpl implements ITUserCourseService {

    @Autowired
    private TUserCourseMapper tUserCourseMapper;

    /**
     * 查询学生选课管理
     * 
     * @param id 学生选课管理主键
     * @return 学生选课管理
     */
    @Override
    public TUserCourse selectTUserCourseById(Long id) {
        return tUserCourseMapper.selectTUserCourseById(id);
    }

    /**
     * 查询学生选课管理列表
     * 
     * @param tUserCourse 学生选课管理
     * @return 学生选课管理
     */
    @Override
    public List<TUserCourse> selectTUserCourseList(TUserCourse tUserCourse) {
        return tUserCourseMapper.selectTUserCourseList(tUserCourse);
    }

    /**
     * 新增学生选课管理
     * 
     * @param tUserCourse 学生选课管理
     * @return 结果
     */

    @Override
    public int insertTUserCourse(TUserCourse tUserCourse) {
        tUserCourse.setCreateTime(new Date());
        return tUserCourseMapper.insertTUserCourse(tUserCourse);
    }

    /**
     * 修改学生选课管理
     * 
     * @param tUserCourse 学生选课管理
     * @return 结果
     */

    @Override
    public int updateTUserCourse(TUserCourse tUserCourse) {
        tUserCourse.setUpdateTime(new Date());
        return tUserCourseMapper.updateTUserCourse(tUserCourse);
    }

    /**
     * 批量删除学生选课管理
     * 
     * @param ids 需要删除的学生选课管理主键
     * @return 结果
     */

    @Override
    public int deleteTUserCourseByIds(Long[] ids) {
        return tUserCourseMapper.deleteTUserCourseByIds(ids);
    }

    /**
     * 删除学生选课管理信息
     * 
     * @param id 学生选课管理主键
     * @return 结果
     */

    @Override
    public int deleteTUserCourseById(Long id) {
        return tUserCourseMapper.deleteTUserCourseById(id);
    }
}
