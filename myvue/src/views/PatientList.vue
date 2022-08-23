<template>
  <el-main>
    <el-table :data="tableData"  stripe v-loading="loading" border
              tooltip-effect="dark">
      <el-table-column prop="rid" label="rid" >
      </el-table-column>
      <el-table-column prop="patient.pname" label="姓名" >
      </el-table-column>
      <el-table-column prop="patient.sex" label="性别" >
      </el-table-column>
      <el-table-column prop="patient.phone" label="手机号" >
      </el-table-column>
      <el-table-column prop="patient.identityCard" label="身份证" >
      </el-table-column>
      <el-table-column  label="操作" width="200px"  fixed="right" >
        <template slot-scope="scope">
          <el-button type="primary" @click="work(scope.$index, scope.row)">诊断</el-button>
          <el-button type="success" @click="complete(scope.$index, scope.row)">完成诊断</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <div class="block">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-size="pageSize"
            layout="total, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </div>

  </el-main>
</template>

<script>
export default {
  name: "PatientList",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      loading: true,
      tableData: [],
      pageNum:1,
      pageSize:10,
      total: 0,
    }
  },
  methods: {
    load(){
      this.request.get("/registration",{params:{
          jobid: this.user.jobid,
          pageNum: this.pageNum,
          pageSize:this.pageSize
        }}).then((res)=>{
        this.tableData = res.data.list
        this.total = res.data.total
        this.loading = false;
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },
    work(index,row){
      console.log(row)
      let info = row
      this.$router.push({
        name:'patientmain',
        params:{context: info}})
    },
    complete(index,row){
      this.$confirm("确认完成？").then(()=>{
        this.request.put('/registration/'+row.rid).then(()=>{
          this.$message('完成')
          this.load()
        })
      })
    }
  },
  created() {
    this.load()
  }
}
</script>

<style scoped>

</style>