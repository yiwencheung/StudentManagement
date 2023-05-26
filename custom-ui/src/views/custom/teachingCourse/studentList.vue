<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="studentCourseList" @selection-change="handleSelectionChange">
      <el-table-column label="学生编号" align="center" prop="userId" />
      <el-table-column label="学生姓名" align="center" prop="userName" />
      <el-table-column label="课程成绩" align="center" prop="score" />
      <el-table-column label="考试状态" align="center" prop="examStatus">
        <template slot-scope="scope">
          <span>{{ examStatusList[scope.row.examStatus].label }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
  </div>
</template>

<script>
import {
  listStudentCourse,
  getStudentCourse,
  delStudentCourse,
  addStudentCourse,
  updateStudentCourse,
  addBatch
} from "@/api/custom/studentCourse";
import {
  listTeacherCourse
} from "@/api/custom/teacherCourse";
import {
  listUser
} from "@/api/custom/user";
export default {
  name: "StudentList",
  props: {
    teacherCourseId: {
      type: Number,
      default: -1,
    },
  },
  watch: {
    teacherCourseId: function (val) {
      this.queryParams.teacherCourseId = val;
      this.getList();
    }
  },
  data() {
    return {
      CS: {
        'text-align': 'center', //文本居中
        'width': '150px', //最小宽度
        'word-break': 'break-all' //过长时自动换行
      },
      CS2: {
        'text-align': 'center', //文本居中
        'width': '100px', //最小宽度
        'word-break': 'break-all', //过长时自动换行
        'margin-top': '15px',
      },
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
      queryParamsDialog: {
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
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
    this.queryParams.teacherCourseId = this.teacherCourseId;
    this.getList();
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
        console.log(this.queryParams);
        console.log(this.studentCourseList);
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
  }
};
</script>
