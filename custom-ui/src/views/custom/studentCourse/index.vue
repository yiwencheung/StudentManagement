<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生姓名" prop="userName">
        <el-input v-model="queryParams.userName" placeholder="请输入学生姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="课程名称" prop="courseName">
        <el-input v-model="queryParams.courseName" placeholder="请输入课程名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="课程成绩" prop="score">
        <el-input type="number" v-model="queryParams.score" placeholder="请输入课程成绩" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="考试状态" prop="examStatus">
        <el-select v-model="queryParams.examStatus" placeholder="请选择考试状态" clearable>
          <el-option v-for="dict in examStatusList" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="支付状态" prop="payStatus">
        <el-select v-model="queryParams.payStatus" placeholder="请选择支付状态" clearable>
          <el-option v-for="dict in payStatusList" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
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
          v-hasPermi="['custom:studentCourse:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['custom:studentCourse:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['custom:studentCourse:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentCourseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="学生编号" align="center" prop="userId" />
      <el-table-column label="学生姓名" align="center" prop="userName" />
      <el-table-column label="课程编号" align="center" prop="courseId" />
      <el-table-column label="课程名称" align="center" prop="courseName">
      </el-table-column>
      <el-table-column label="教师编号" align="center" prop="teacherId" />
      <el-table-column label="教师名称" align="center" prop="teacherName" />
      <el-table-column label="课程学分" align="center" prop="credit" />
      <el-table-column label="课程成绩" align="center" prop="score" />
      <el-table-column label="考试状态" align="center" prop="examStatus" width="180">
        <template slot-scope="scope">
          <span>{{ examStatusList[scope.row.examStatus].label }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="payStatus" width="180">
        <template slot-scope="scope">
          <span>{{ payStatusList[scope.row.payStatus].label }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['custom:studentCourse:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['custom:studentCourse:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
    <!--———————————————————————————————————————————————————————————————————————————— 上面是表格、下面是添加编辑表单页面—————————————————————————————————————————————————————————————————————— -->

    <!-- 添加或修改学生选课管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生" prop="userName">
          <el-select :disabled="isEdit" @change="selectUserNameChange" v-model="form.userName" placeholder="请选择学生名称">
            <el-option v-for="(item, index) in userNameList" :label="item.name" :value="index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程名称" prop="courseName">
          <el-select :disabled="isEdit" @change="selectTeacherCourseChange" v-model="form.courseName"
            placeholder="请选择课程名称">
            <el-option v-for="(item, index) in courseNameList" :label="item.courseName + ' - ' + item.teacherName"
              :value="index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="教师名称" prop="teacherName">
          <el-input v-model="form.teacherName" disabled />
        </el-form-item>
        <el-form-item label="学分" prop="credit">
          <el-input v-model="form.credit" disabled />
        </el-form-item>
        <el-form-item label="考试状态" prop="examStatus">
          <el-select v-model="form.examStatus" placeholder="请选择考试状态">
            <el-option v-for="dict in examStatusList" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付状态" prop="payStatus">
          <el-select disabled v-model="form.payStatus" placeholder="请选择支付状态">
            <el-option v-for="dict in payStatusList" :key="dict.value" :label="dict.label" :value="parseInt(dict.value)">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="成绩" prop="score">
          <el-input type="number" v-model="form.score" />
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
  listStudentCourse,
  getStudentCourse,
  delStudentCourse,
  addStudentCourse,
  updateStudentCourse
} from "@/api/custom/studentCourse";
import {
  listTeacherCourse
} from "@/api/custom/teacherCourse";
import {
  listUser
} from "@/api/custom/user";
export default {
  name: "StudentCourse",
  data() {
    return {
      userNameList: [],
      //课程名称
      courseNameList: [],
      //考试状态
      examStatusList: [{
        "label": "未开始",
        "value": "0"
      }, {
        "label": "已考试",
        "value": "1"
      }],
      //支付状态
      payStatusList: [{
        "label": "未支付",
        "value": "0"
      }, {
        "label": "已支付",
        "value": "1"
      }],
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
      // 学生选课管理表格数据
      studentCourseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        courseName: null,
        score: null,
        examStatus: null,
        payStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [{
          required: true,
          message: "学生编号不能为空",
          trigger: "blur"
        }],
        userName: [{
          required: true,
          message: "学生姓名不能为空",
          trigger: "blur"
        }],
        teacherCourseId: [{
          required: true,
          message: "选课编号不能为空",
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
        credit: [{
          required: true,
          message: "课程学分不能为空",
          trigger: "blur"
        }],
        examStatus: [{
          required: true,
          message: "考试状态不能为空",
          trigger: "change"
        }],
        payStatus: [{
          required: true,
          message: "支付状态不能为空",
          trigger: "change"
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
    this.getTeacherCourseList();
    this.getUserNameList();
  },
  methods: {
    getUserNameList() {
      listUser({}).then(response => {
        this.userNameList = response.rows;
      });
    },
    selectUserNameChange(index) {
      this.form.userName = this.userNameList[index].name;
      this.form.userId = this.userNameList[index].id;
    },
    /**获取课程名称下拉列表 **/
    getTeacherCourseList() {
      listTeacherCourse({}).then(response => {
        this.courseNameList = response.rows;
      });
    },
    selectTeacherCourseChange(index) {
      this.form.courseName = this.courseNameList[index].courseName;
      this.form.courseId = this.courseNameList[index].courseId;
      this.form.teacherId = this.courseNameList[index].teacherId;
      this.form.teacherName = this.courseNameList[index].teacherName;
      this.form.credit = this.courseNameList[index].credit;
      this.form.teacherCourseId = this.courseNameList[index].id;
      this.$set(this.form, 'teacherName', this.courseNameList[index].teacherName)
      this.$set(this.form, 'courseName', this.courseNameList[index].courseName)
      this.$set(this.form, 'credit', this.courseNameList[index].credit)
    },
    /** 查询学生选课管理列表 */
    getList() {
      this.loading = true;
      listStudentCourse(this.queryParams).then(response => {
        this.studentCourseList = response.rows;
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
        userId: null,
        userName: null,
        teacherCourseId: null,
        courseId: null,
        courseName: null,
        teacherId: null,
        teacherName: null,
        credit: null,
        score: null,
        examStatus: null,
        payStatus: null,
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
      this.title = "添加学生选课管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.isEdit = true;
      this.reset();
      const id = row.id || this.ids
      getStudentCourse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生选课管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStudentCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.userId = this.$store.state.user.id;
            this.form.userName = this.$store.state.user.nickName;
            addStudentCourse(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除学生选课管理编号为"' + ids + '"的数据项？').then(function () {
        return delStudentCourse(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
  }
};
</script>
