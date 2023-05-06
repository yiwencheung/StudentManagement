package com.ysir.custom.service.impl;

import java.util.Date;
import java.util.List;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysir.custom.mapper.TTeacherCourseMapper;
import com.ysir.custom.entity.TTeacherCourse;
import com.ysir.custom.service.ITTeacherCourseService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 教师课程管理Service业务层处理
 *
 */
@Service
@Transactional
public class TTeacherCourseServiceImpl implements ITTeacherCourseService {

    @Autowired
    private TTeacherCourseMapper tTeacherCourseMapper;

    /**
     * 查询教师课程管理
     * 
     * @param id 教师课程管理主键
     * @return 教师课程管理
     */
    @Override
    public TTeacherCourse selectTTeacherCourseById(Long id) {
        return tTeacherCourseMapper.selectTTeacherCourseById(id);
    }

    /**
     * 查询教师课程管理列表
     * 
     * @param tTeacherCourse 教师课程管理
     * @return 教师课程管理
     */
    @Override
    public List<TTeacherCourse> selectTTeacherCourseList(TTeacherCourse tTeacherCourse) {
        return tTeacherCourseMapper.selectTTeacherCourseList(tTeacherCourse);
    }

    /**
     * 新增教师课程管理
     * 
     * @param tTeacherCourse 教师课程管理
     * @return 结果
     */

    @Override
    public int insertTTeacherCourse(TTeacherCourse tTeacherCourse) {
        tTeacherCourse.setCreateTime(new Date());
        return tTeacherCourseMapper.insertTTeacherCourse(tTeacherCourse);
    }

    /**
     * 修改教师课程管理
     * 
     * @param tTeacherCourse 教师课程管理
     * @return 结果
     */

    @Override
    public int updateTTeacherCourse(TTeacherCourse tTeacherCourse) {
        tTeacherCourse.setUpdateTime(new Date());
        return tTeacherCourseMapper.updateTTeacherCourse(tTeacherCourse);
    }

    /**
     * 批量删除教师课程管理
     * 
     * @param ids 需要删除的教师课程管理主键
     * @return 结果
     */

    @Override
    public int deleteTTeacherCourseByIds(Long[] ids) {
        return tTeacherCourseMapper.deleteTTeacherCourseByIds(ids);
    }

    /**
     * 删除教师课程管理信息
     * 
     * @param id 教师课程管理主键
     * @return 结果
     */

    @Override
    public int deleteTTeacherCourseById(Long id) {
        return tTeacherCourseMapper.deleteTTeacherCourseById(id);
    }
}
