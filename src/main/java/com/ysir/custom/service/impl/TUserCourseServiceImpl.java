package com.ysir.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ysir.custom.common.AjaxResult;
import com.ysir.custom.entity.TTeacherCourse;
import com.ysir.custom.entity.TUserCourse;
import com.ysir.custom.mapper.TTeacherCourseMapper;
import com.ysir.custom.mapper.TUserCourseMapper;
import com.ysir.custom.service.ITUserCourseService;
import com.ysir.custom.util.ServerConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生选课管理Service业务层处理
 */
@Service
@Transactional
public class TUserCourseServiceImpl implements ITUserCourseService {

    @Autowired
    private TUserCourseMapper tUserCourseMapper;

    @Autowired
    private TTeacherCourseMapper tTeacherCourseMapper;

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

        TTeacherCourse tTeacherCourse = tTeacherCourseMapper.selectTTeacherCourseById(tUserCourse.getTeacherCourseId());

        TUserCourse param = new TUserCourse();
        param.setUserId(tUserCourse.getUserId());
        param.setTeacherCourseId(tUserCourse.getTeacherCourseId());
        List<TUserCourse> tUserCourses = tUserCourseMapper.selectTUserCourseList(param);
        if (ObjectUtil.isNotEmpty(tUserCourses)) {
            return 0;
        }
        tTeacherCourse.setSelectedNum(tTeacherCourse.getSelectedNum() + 1);
        tTeacherCourseMapper.updateTTeacherCourse(tTeacherCourse);

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
    public AjaxResult deleteTUserCourseByIds(Long[] ids) {
        List<TUserCourse> list = tUserCourseMapper.selectTUserCourseByIds(ids);
        for (TUserCourse tUserCourse : list) {
            if (DateUtil.compare(tUserCourse.getStartTime(),new Date()) <= 0){
                return AjaxResult.error("包含已开始课程，无法删除，请刷新重试");
            }
        }
        List<TTeacherCourse> tTeacherCourses = tTeacherCourseMapper.selectTTeacherCourseByIds(list
                .stream()
                .map(TUserCourse::getTeacherCourseId)
                .distinct()
                .collect(Collectors.toList()));
        for (TTeacherCourse tTeacherCours : tTeacherCourses) {
            tTeacherCours.setSelectedNum(tTeacherCours.getSelectedNum() - 1);
            tTeacherCourseMapper.updateTTeacherCourse(tTeacherCours);
        }
        tUserCourseMapper.deleteTUserCourseByIds(ids);
        return AjaxResult.success();
    }

    /**
     * 删除学生选课管理信息
     *
     * @param id 学生选课管理主键
     * @return 结果
     */

    @Override
    public int deleteTUserCourseById(Long id) {
        TUserCourse tUserCourse = tUserCourseMapper.selectTUserCourseById(id);
        TTeacherCourse tTeacherCourse = tTeacherCourseMapper.selectTTeacherCourseById(tUserCourse.getTeacherCourseId());
        tTeacherCourse.setSelectedNum(tTeacherCourse.getSelectedNum() - 1);
        tTeacherCourseMapper.updateTTeacherCourse(tTeacherCourse);
        return tUserCourseMapper.deleteTUserCourseById(id);
    }

    @Override
    public AjaxResult addBatch(List<Long> ids) {

        List<TTeacherCourse> list = tTeacherCourseMapper.selectTTeacherCourseByIds(ids);

        for (TTeacherCourse e : list) {
            if (e.getSelectedNum() >= e.getStudentNum()) {
                return AjaxResult.error("选课人数已满，请刷新重新选择");
            }
            TUserCourse param = new TUserCourse();
            param.setUserId(ServerConfigUtil.getUserId());
            param.setTeacherCourseId(e.getId());
            List<TUserCourse> tUserCourses = tUserCourseMapper.selectTUserCourseList(param);
            if (CollUtil.isNotEmpty(tUserCourses)) {
                return AjaxResult.error("选中的课程存在已选择课程，请刷新重新选择");
            }
            e.setSelectedNum(e.getSelectedNum() + 1);
            tTeacherCourseMapper.updateTTeacherCourse(e);

            TUserCourse tUserCourse = new TUserCourse();
            BeanUtil.copyProperties(e, tUserCourse);
            tUserCourse.setUserId(ServerConfigUtil.getUserId());
            tUserCourse.setUserName(ServerConfigUtil.getLoginUser().getUser().getNickName());
            tUserCourse.setTeacherCourseId(e.getId());
            tUserCourseMapper.insertTUserCourse(tUserCourse);
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult paySuccess(Long[] ids) {
        if (ids.length <= 0){
            return AjaxResult.warn("请选择付款课程");
        }
        List<TUserCourse> tUserCourses = tUserCourseMapper.selectTUserCourseByIds(ids);
        for (TUserCourse tUserCours : tUserCourses) {
            tUserCours.setPayStatus(1L);
            tUserCourseMapper.updateTUserCourse(tUserCours);
        }
        return AjaxResult.success();
    }
}
