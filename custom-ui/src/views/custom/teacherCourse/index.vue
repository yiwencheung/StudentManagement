<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师名称" prop="teacherName">
        <el-input v-model="queryParams.teacherName" placeholder="请输入教师名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="课程名称" prop="courseName">
        <el-input v-model="queryParams.courseName" placeholder="请输入课程名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="已选择学生数" prop="selectedNum">
        <el-input type="number" v-model="queryParams.selectedNum" placeholder="请输入已选择学生数" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!--———————————————————————————————————————————————————————————————————————————— 上面是搜索表单、下面是表格—————————————————————————————————————————————————————————————————————————— -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['custom:teacherCourse:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['custom:teacherCourse:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['custom:teacherCourse:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherCourseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="选课编号" align="center" prop="id" />
      <el-table-column label="教师名称" align="center" prop="teacherName">
      </el-table-column>
      <el-table-column label="课程编号" align="center" prop="courseId" />
      <el-table-column label="课程名称" align="center" prop="courseName">
      </el-table-column>
      <el-table-column label="可容纳学生数" align="center" prop="studentNum" />
      <el-table-column label="已选择学生数" align="center" prop="selectedNum" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['custom:teacherCourse:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['custom:teacherCourse:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
    <!--———————————————————————————————————————————————————————————————————————————— 上面是表格、下面是添加编辑表单页面—————————————————————————————————————————————————————————————————————— -->

    <!-- 添加或修改教师课程管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="教师名称" prop="teacherName">
          <el-select @change="selectUserChange" v-model="form.teacherName" placeholder="请选择教师名称">
            <el-option v-for="(item,index) in teacherNameList" :label="item.name" :value="index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程名称" prop="courseName">
          <el-select @change="selectCourseChange" v-model="form.courseName" placeholder="请选择课程名称">
            <el-option v-for="(item,index) in courseNameList" :label="item.name" :value="index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="可容纳学生数" prop="studentNum">
          <el-input type="number" v-model="form.studentNum" placeholder="请输入可容纳学生数" />
        </el-form-item>
        <!--————————————————————————————————————————————————————————————————————————— 上面是子表页面、下面是结束———————————————————————————————————————————————————————————————————— -->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listTeacherCourse,
    getTeacherCourse,
    delTeacherCourse,
    addTeacherCourse,
    updateTeacherCourse
  } from "@/api/custom/teacherCourse";
  import {
    listUser
  } from "@/api/custom/user";
  import {
    listCourse
  } from "@/api/custom/course";
  export default {
    name: "TeacherCourse",
    data() {
      return {
        //教师名称
        teacherNameList: [],
        //课程名称
        courseNameList: [],
        // 遮罩层
        loading: true,
        isEdit: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 教师课程管理表格数据
        teacherCourseList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          teacherName: null,
          courseName: null,
          selectedNum: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          teacherId: [{
            required: true,
            message: "教师编号不能为空",
            trigger: "blur"
         } ],
          teacherName: [{
            required: true,
            message: "教师名称不能为空",
            trigger: "blur"
          }],
          courseId: [{
            required: true,
            message: "课程编号不能为空",
            trigger: "blur"
          }],
          courseName: [{
            required: true,
            message: "课程名称不能为空",
            trigger: "blur"
          }],
          studentNum: [{
            required: true,
            message: "可容纳学生数不能为空",
            trigger: "blur"
          }],
          selectedNum: [{
            required: true,
            message: "已选择学生数不能为空",
            trigger: "blur"
         } ],
          createTime: [{
            required: true,
            message: "创建时间不能为空",
            trigger: "blur"
         } ],
          updateTime: [{
            required: true,
            message: "更新时间不能为空",
            trigger: "blur"
         } ]
        }
      };
    },
    created() {
      this.getList();
      this.getUserList();
      this.getCourseList();
    },
    methods: {
      /**获取教师名称下拉列表 **/
      getUserList() {
        listUser({
          roleId: 2
        }).then(response => {
          this.teacherNameList = response.rows;
        });
      },
      selectUserChange(index) {
        this.form.teacherName = this.teacherNameList[index].name;
        this.form.teacherId = this.teacherNameList[index].id;
      },
      /**获取课程名称下拉列表 **/
      getCourseList() {
        listCourse({}).then(response => {
          this.courseNameList = response.rows;
        });
      },
      selectCourseChange(index) {
        this.form.courseName = this.courseNameList[index].name;
        this.form.courseId = this.courseNameList[index].id;
      },
      /** 查询教师课程管理列表 */
      getList() {
        this.loading = true;
        listTeacherCourse(this.queryParams).then(response => {
          this.teacherCourseList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          teacherId: null,
          teacherName: null,
          courseId: null,
          courseName: null,
          studentNum: null,
          selectedNum: null,
          createTime: null,
          updateTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加教师课程管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.isEdit = true;
        this.reset();
        const id = row.id || this.ids
        getTeacherCourse(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改教师课程管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateTeacherCourse(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addTeacherCourse(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除教师课程管理编号为"' + ids + '"的数据项？').then(function() {
          return delTeacherCourse(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
    }
  };
</script>
