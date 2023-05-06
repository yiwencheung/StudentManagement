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
import com.ysir.custom.entity.TUserCourse;
import com.ysir.custom.service.ITUserCourseService;
import com.ysir.custom.common.PageDataInfo;

/**
 * 学生选课管理Controller
 *
 */
@RestController
@RequestMapping("/custom/studentCourse")
public class TUserCourseController extends BaseController {

    @Autowired
    private ITUserCourseService tUserCourseService;

    /**
     * 查询学生选课管理列表
     */
    @GetMapping("/list")
    public PageDataInfo list(TUserCourse tUserCourse) {
        startPage();
        List<TUserCourse> list = tUserCourseService.selectTUserCourseList(tUserCourse);
        return getPageData(list);
    }

    /**
     * 获取学生选课管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tUserCourseService.selectTUserCourseById(id));
    }

    /**
     * 新增学生选课管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody TUserCourse tUserCourse) {
        return toAjax(tUserCourseService.insertTUserCourse(tUserCourse));
    }

    /**
     * 修改学生选课管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody TUserCourse tUserCourse) {
        return toAjax(tUserCourseService.updateTUserCourse(tUserCourse));
    }

    /**
     * 删除学生选课管理
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tUserCourseService.deleteTUserCourseByIds(ids));
    }
}
