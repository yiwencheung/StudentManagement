<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="studentCourseList">
      <el-table-column label="学生编号" align="center" prop="userId" />
      <el-table-column label="学生姓名" align="center" prop="userName" />
      <el-table-column label="考试状态" align="center" prop="examStatus">
        <template slot-scope="scope">
          <el-select v-model="scope.row.examStatus" placeholder="请选择" @change="handleExamStatusChange(scope.row)">
            <el-option v-for="item in examStatusList" :key="item.value" :label="item.label" :value="parseInt(item.value)" />
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="课程成绩" align="center" prop="score" >
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.score" :min="0" :max="100" :step="1" controls-position="right" @change="handleScoreChange(scope.row)" v-if="scope.row.examStatus == 1"></el-input-number>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- Button for saving -->
    <el-button type="primary" @click="save" :disabled="!changed">保存</el-button>
  </div>
</template>

<script>
import {
  listStudentCourse,
  addStudentCourse,
  updateStudentCourse,
} from "@/api/custom/studentCourse";
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
      changed: false,
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
    /** 查询学生选课管理列表 */
    getList() {
      this.loading = true;
      listStudentCourse(this.queryParams).then(response => {
        this.studentCourseList = response.rows;
        this.total = response.total;
        this.loading = false;
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
    handleExamStatusChange(row) {
      this.changed = true;
      if (row.examStatus == "0") {
        row.score = null;
      }
    },
    handleScoreChange(row) {
      this.changed = true;
    },
    save(){
      let successCount = 0;
      this.studentCourseList.forEach(element => {
        updateStudentCourse(element).then(response => {
          successCount++;
          if (successCount === this.studentCourseList.length) {
            this.$modal.msgSuccess("修改成功");
            this.getList();
          }
        });
      });
    }
  }
};
</script>
