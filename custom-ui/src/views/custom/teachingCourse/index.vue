<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师名称" prop="teacherName">
        <el-input v-model="queryParams.teacherName" placeholder="请输入教师名称" clearable @keyup.enter.native="handleQuery"
          disabled />
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

    <el-table v-loading="loading" :data="teacherCourseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课堂编号" align="center" prop="id" />
      <el-table-column label="课程编号" align="center" prop="courseId" />
      <el-table-column label="课程名称" align="center" prop="courseName" />
      <el-table-column label="可容纳学生数" align="center" prop="studentNum" />
      <el-table-column label="已选择学生数" align="center" prop="selectedNum" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-s-custom"
            @click="handleStudentList(scope.row)">学生名单</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
    <!--———————————————————————————————————————————————————————————————————————————— 上面是表格、下面是添加编辑表单页面—————————————————————————————————————————————————————————————————————— -->

    <!-- 添加学生名单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <StudentList :teacherCourseId="selectedTeacherCourseId" />
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
import { getUserProfile } from "@/api/custom/user";
import StudentList from "./studentList.vue";
export default {
  name: "TeacherCourse",
  data() {
    return {
      //教师名称
      teacherNameList: [],
      //课程名称
      courseNameList: [],
      selectedTeacherCourseId: null,
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
    };
  },
  created() {
    this.getUserList();
    this.getCourseList();
    this.getUser();
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    handleStudentList(row) {
      this.isEdit = true;
      const id = row.id || this.ids;
      this.selectedTeacherCourseId = id;
      this.open = true;
      this.title = "学生列表：" + row.teacherName + "-" + row.courseName;
    },
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
        console.log(this.user);
        this.queryParams.teacherName = this.user.name;
        this.getList();
      });
    },
  },
  components: { StudentList }
};
</script>
