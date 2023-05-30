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

    <el-table v-loading="loading" :data="teacherCourseList">
      <el-table-column label="课堂编号" align="center" prop="id" />
      <el-table-column label="教师名称" align="center" prop="teacherName">
        <template slot-scope="scope">
          <el-tooltip :content="'编号：' + scope.row.teacherId" placement="top" effect="dark">
            <span>{{ scope.row.teacherName }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="课程名称" align="center" prop="courseName">
        <template slot-scope="scope">
          <el-tooltip :content="'编号：' + scope.row.courseId" placement="top" effect="dark">
            <span>{{ scope.row.courseName }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="课程学分" align="center" prop="credit">
      </el-table-column>
      <el-table-column label="课程价格" align="center" prop="price">
      </el-table-column>
      <el-table-column label="可容纳学生数" align="center" prop="studentNum" />
      <el-table-column label="已选择学生数" align="center" prop="selectedNum" />
      <el-table-column label="开始时间" align="center" width="180">
        <template slot-scope="scope">
          <el-tooltip :content="scope.row.startTime" placement="top" effect="dark">
            <span>{{ new Date(scope.row.startTime).toISOString().slice(0, 10) }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" width="180">
        <template slot-scope="scope">
          <el-tooltip :content="scope.row.endTime" placement="top" effect="dark">
            <span>{{ new Date(scope.row.endTime).toISOString().slice(0, 10) }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="180">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="handleChoose(scope.row)">选课</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
  </div>
</template>

<script>
import {
  listTeacherCourse,
} from "@/api/custom/teacherCourse";
import {
  addBatch
} from "@/api/custom/studentCourse";
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
        }],
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
        }],
        createTime: [{
          required: true,
          message: "创建时间不能为空",
          trigger: "blur"
        }],
        updateTime: [{
          required: true,
          message: "更新时间不能为空",
          trigger: "blur"
        }]
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
    /** 查询课堂列表 */
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
    handleChoose(row) {
      console.log(row);
      addBatch([row.id]).then(response => {
        this.$modal.msgSuccess("选课成功");
        this.getList();
      }).catch(() => { });
    },
  }
};
</script>
