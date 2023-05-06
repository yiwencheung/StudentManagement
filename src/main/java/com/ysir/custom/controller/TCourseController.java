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
import com.ysir.custom.entity.TCourse;
import com.ysir.custom.service.ITCourseService;
import com.ysir.custom.common.PageDataInfo;

/**
 * 课程管理Controller
 *
 */
@RestController
@RequestMapping("/custom/course")
public class TCourseController extends BaseController {

    @Autowired
    private ITCourseService tCourseService;

    /**
     * 查询课程管理列表
     */
    @GetMapping("/list")
    public PageDataInfo list(TCourse tCourse) {
        startPage();
        List<TCourse> list = tCourseService.selectTCourseList(tCourse);
        return getPageData(list);
    }

    /**
     * 获取课程管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tCourseService.selectTCourseById(id));
    }

    /**
     * 新增课程管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody TCourse tCourse) {
        return toAjax(tCourseService.insertTCourse(tCourse));
    }

    /**
     * 修改课程管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody TCourse tCourse) {
        return toAjax(tCourseService.updateTCourse(tCourse));
    }

    /**
     * 删除课程管理
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tCourseService.deleteTCourseByIds(ids));
    }
}
