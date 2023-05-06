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
import com.ysir.custom.entity.TCourseType;
import com.ysir.custom.service.ITCourseTypeService;
import com.ysir.custom.common.PageDataInfo;

/**
 * 课程性质管理Controller
 *
 */
@RestController
@RequestMapping("/custom/courseType")
public class TCourseTypeController extends BaseController {

    @Autowired
    private ITCourseTypeService tCourseTypeService;

    /**
     * 查询课程性质管理列表
     */
    @GetMapping("/list")
    public PageDataInfo list(TCourseType tCourseType) {
        startPage();
        List<TCourseType> list = tCourseTypeService.selectTCourseTypeList(tCourseType);
        return getPageData(list);
    }

    /**
     * 获取课程性质管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tCourseTypeService.selectTCourseTypeById(id));
    }

    /**
     * 新增课程性质管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody TCourseType tCourseType) {
        return toAjax(tCourseTypeService.insertTCourseType(tCourseType));
    }

    /**
     * 修改课程性质管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody TCourseType tCourseType) {
        return toAjax(tCourseTypeService.updateTCourseType(tCourseType));
    }

    /**
     * 删除课程性质管理
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tCourseTypeService.deleteTCourseTypeByIds(ids));
    }
}
