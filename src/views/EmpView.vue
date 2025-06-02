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
        <el-button type="primary" @click="handleAdd">添加</el-button>
      </el-form-item>
    </el-form>


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
        @current-change="handleCurrentChange">
    </el-pagination>
  </div>


</template>

<script>
export default {
  data() {
    return {
      total: 0,
      pageSize: 5,
      tableData: [],
      formInline: {
        emp_name: '',
        emp_sex: ''
      }
    }
  },
  methods: {
    // 添加员工
    handleAdd(){

    },
    // 条件查询;提交表单后执行的方法
    handleSelect() {
      console.log('submit!');
    },
    handleUpdate(row) {
      console.log(row);
    },
    // 删除操作
    handleDelete(id) {
      console.log(id);
      // 向后端发送请求删除数据
      this.axios.delete("http://localhost:8081/emp/delete/" + id)
          .then(res => {
            if (res.data.code === 100) {
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.pageSize  = res.data.data.pageSize;  // 将当前页赋值给pageSize
              // 刷新页面
              this.queryPage();
            }else  {
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
      this.axios.post("http://localhost:8081/emp/queryPage", {pageNum: this.pageNum, pageSize: this.pageSize})
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
    }
  },
  // 钩子函数;在页面加载的时候执行
  mounted() {
    // 页面加载时,发送请求分页查询
    this.queryPage();
  }
}
</script>

<style scoped>

</style>