<template>

  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="员工姓名">
        <el-input v-model="formInline.emp_name" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="formInline.emp_sex" placeholder="性别">
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSelect">查询</el-button>
      </el-form-item>
      <el-form-item>
        <!--点击添加按钮.跳出添加员工表单-->
        <el-button type="primary" @click="handleAdd">添加</el-button>
      </el-form-item>
    </el-form>
    <!-- 表单 -->
    <el-dialog :title="formTitle" :visible.sync="dialogFormVisible">
      <el-form :model="emp">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="emp.emp_name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-input v-model="emp.emp_age" autocomplete="off"></el-input>
        </el-form-item>


        <el-form-item label="性别" :label-width="formLabelWidth">
          <!--          <el-input v-model="emp.emp_sex" autocomplete="off"></el-input>-->
          <el-radio-group v-model="emp.emp_sex">
            <el-radio :label="1" value="1">男</el-radio>
            <el-radio :label="0" value="0">女</el-radio>
          </el-radio-group>
        </el-form-item>


        <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="emp.emp_address" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="生日" :label-width="formLabelWidth">
<!--          <el-input v-model="emp.emp_birth" autocomplete="off"></el-input>-->
          <el-date-picker
              v-model="emp.emp_birth"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="薪资" :label-width="formLabelWidth">
          <el-input v-model="emp.emp_salary" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="所属部门" :label-width="formLabelWidth">
<!--          <el-input v-model="emp.dept_id" autocomplete="off"></el-input>-->
          <el-select v-model="emp.dept_id" placeholder="请选择">
            <el-option
                v-for="dept in depts"
                :key="dept.dept_id"
                :label="dept.dept_name"
                :value="dept.dept_id">
            </el-option>
          </el-select>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveButton">确 定</el-button>
      </div>
    </el-dialog>


    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="emp_name"
          label="员工姓名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="emp_age"
          label="年龄"
          width="180">
      </el-table-column>
      <el-table-column
          prop="emp_sex"
          label="性别">
        <template slot-scope="scope">
          <span v-if="scope.row.emp_sex==1">男</span>
          <span v-else>女</span>
        </template>
      </el-table-column>
      <el-table-column
          prop="emp_address"
          label="地址">
      </el-table-column>
      <el-table-column
          prop="emp_birth"
          label="生日">
      </el-table-column>
      <el-table-column
          prop="emp_salary"
          label="员工薪资">
      </el-table-column>
      <el-table-column
          prop="dept.dept_name"
          label="所属部门">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作">
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
          <el-button @click="handleDelete(scope.row.emp_id)" type="danger" icon="el-icon-delete" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        :current-page="pageNum"
        @current-change="handleCurrentChange">
    </el-pagination>
  </div>


</template>

<script>
export default {
  data() {
    return {
      formTitle: '',
      dialogFormVisible: false,
      emp: {
        emp_name: '',
        emp_age: null,
        emp_sex: null,
        emp_address: '',
        emp_birth: null,
        emp_salary: null,
        dept_id: null
      },
      depts: [{
        dept_id: null,
        dept_name: ''
      },{
        dept_id: null,
        dept_name: ''
      }], // 部门列表
      formLabelWidth: '120px',
      total: 0,
      pageSize: 5,
      pageNum: 1,
      tableData: [],
      formInline: {
        emp_name: '',
        emp_sex: ''
      }
    }
  },
  methods: {
    /**
     *  添加 or 更新员工区别
     *  添加:
     *  1. emp_id 为 null
     *  2. 不用回显数据
     *  更新:
     *  1. emp_id 不为 null
     *  2. 回显数据
     */
    saveButton() {
      // 发送请求添加 or 修改 员工;参数传递: this.emp这个对象
      this.axios.post("http://localhost:9000/emp/save", this.emp)
          .then(res => {
            if (res.data.code == 100) {
              // 提示成功
              this.$message.success("操作成功");
              // 关闭表单
              this.dialogFormVisible = false;
              // 刷新员工列表
              this.handleSelect();
            } else {
              // 提示失败
              this.$message.error("操作失败");
            }
          })
          .catch(res => {
            // 提示系统错误
            this.$message.error('系统异常,请稍后重试');
          })
    },

    // 条件查询;提交表单后执行的方法;应在当前页展示
    handleSelect() {
      console.log("条件查询");
      // 根据员工姓名查询;根据性别查询;姓名和性别查询
      this.axios.post("http://localhost:9000/emp/queryCondition", {
        pageNum: this.pageNum,  // 当前页码
        params: {
          emp_name: this.formInline.emp_name,
          emp_sex: this.formInline.emp_sex
        }
      })
          .then(res => {
            console.log(res);
            this.tableData = res.data.rows;
            this.total = res.data.total;
          })
          .catch(res => {
            alert("查询失败")
          })

    },
    handleUpdate(row) {
      console.log(row);
      // 更改fromTitle 并 弹出表单
      this.formTitle =  "更新员工信息";
      /**
       * 展示表单前;回显数据;因为这是双向绑定;所以用拷贝的方式进行赋值
       * 参数一: 拷贝对象
       * 参数二: 源对象
       * 这样页面回显后,修改表单数据不会影响表格中的数据
       */

      this.emp = Object.assign({}, row)
      this.dialogFormVisible  = true;

    },
    handleAdd() {
      // 更改formTitle 并 弹出表单
      this.formTitle =  "添加员工信息";
      // 展示表单前清除缓存
      this.emp = {
        emp_name: '',
        emp_sex:  null,
        emp_age:  null,
        emp_address: '',
        emp_birth:  null,
        emp_salary:  null,
        dept_id:  null
      }
      this.dialogFormVisible  = true;
    },

    // 根据id删除员工
    handleDelete(id) {
      console.log(id);
      // 向后端发送请求删除数据
      this.axios.delete("http://localhost:9000/emp/delete/" + id)
          .then(res => {
            if (res.data.code === 100) {
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              // 刷新页面
              this.handleSelect();
            } else {
              this.$message.error('删除失败,请稍后重试');
            }
          }).catch(res => {
        console.log("网络异常,请稍后重试");
      })

    },
    // 分页查询
    queryPage() {
      // 在页面加载时;向后端发送请求;获取数据
      // // get请求
      // this.axios.get("http://localhost:8080/emp/page")
      //     .then(res=>{  // then 请求成功的回调函数;res中存请求成功后的数据
      //
      //     })
      //     .catch(res=>{ // catch 请求失败的回调函数;res中存请求失败后的信息
      //
      //     })
      // post请求,携带data传递给后端
      this.axios.post("http://localhost:9000/emp/queryPage", {pageNum: this.pageNum, pageSize: this.pageSize})
          .then(res => {
            console.log(res);
            this.tableData = res.data.rows;
            this.total = res.data.total;    // 总条数
            this.pageNum = res.data.pageNum;  // 当前页码
          }).catch(res => {

      })
    },
    // 在当前页发生变化是触发的方法;参数为改变后的当前页码
    handleCurrentChange(page) {
      console.log(page)
      this.pageNum = page;  // 更新当前页
      this.queryPage()
    },
    getDepts() {
      // 向后端发送请求获取部门列表
      this.axios.get("http://localhost:9000/dept/list")
          .then(res => {
            console.log(res);
            // 将res.data中的数据保存到depts中
            this.depts = res.data;
            console.log(this.depts)
          }).catch(res => {
        console.log("网络异常,稍后重试");
      })
    }
  },

  // 钩子函数;在页面加载的时候执行
  mounted() {
    // 页面加载时,发送请求分页查询
    this.queryPage();

    // 页面加载时.查询部门列表;方便后续添加和修改
    this.getDepts();

  }
}
</script>

<style scoped>

</style>