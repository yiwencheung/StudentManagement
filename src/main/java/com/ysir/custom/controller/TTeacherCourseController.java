package com.ysir.custom.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ysir.custom.controller.BaseController;
import com.ysir.custom.common.AjaxResult;
import com.ysir.custom.entity.TTeacherCourse;
import com.ysir.custom.service.ITTeacherCourseService;
import com.ysir.custom.common.PageDataInfo;

/**
 * 教师课程管理Controller
 *
 */
@RestController
@RequestMapping("/custom/teacherCourse")
public class TTeacherCourseController extends BaseController {

    @Autowired
    private ITTeacherCourseService tTeacherCourseService;

    /**
     * 查询教师课程管理列表
     */
    @GetMapping("/list")
    public PageDataInfo list(TTeacherCourse tTeacherCourse) {
        startPage();
        List<TTeacherCourse> list = tTeacherCourseService.selectTTeacherCourseList(tTeacherCourse);
        return getPageData(list);
    }

    /**
     * 获取教师课程管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tTeacherCourseService.selectTTeacherCourseById(id));
    }

    /**
     * 新增教师课程管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody TTeacherCourse tTeacherCourse) {
        return toAjax(tTeacherCourseService.insertTTeacherCourse(tTeacherCourse));
    }

    /**
     * 修改教师课程管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody TTeacherCourse tTeacherCourse) {
        return toAjax(tTeacherCourseService.updateTTeacherCourse(tTeacherCourse));
    }

    /**
     * 删除教师课程管理
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tTeacherCourseService.deleteTTeacherCourseByIds(ids));
    }
}
