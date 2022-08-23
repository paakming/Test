<template>
  <el-main>
<!--    <div>
      <el-button type="primary" icon="el-icon-circle-plus-outline" style="margin-left: 5px" @click="dialogFormVisibleAdd = true">添加</el-button>
      <el-button type="danger" icon="el-icon-remove-outline"  :disabled="multipleSelection.length<=0" style="margin-left: 5px" @click="DeleteIds">批量删除</el-button>
    </div>-->
    <el-table :data="tableData" border stripe v-loading="loading"
              tooltip-effect="dark"
              @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="rid" label="编号" >
      </el-table-column>
      <el-table-column prop="jobid" label="职工号" >
      </el-table-column>
      <el-table-column prop="patient.pname" label="姓名" >
      </el-table-column>
      <el-table-column prop="patient.sex" label="性别" >
      </el-table-column>
      <el-table-column prop="patient.phone" label="手机号" >
      </el-table-column>
      <el-table-column prop="patient.age" label="年龄" >
      </el-table-column>
      <el-table-column prop="patient.identityCard" label="身份证" >
      </el-table-column>
      <el-table-column prop="price" label="价钱" >
      </el-table-column>
      <el-table-column prop="visitTime"  :formatter="formatTime" label="时间" >
      </el-table-column>
      <el-table-column  label="操作"  width="200" fixed="right" >
        <template slot-scope="scope">
<!--          <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" :disabled="multipleSelection.length>1" @click="handleDelete(scope.$index, scope.row)">删除<i class="el-icon-delete"></i></el-button>-->
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

    <div>
      <!-- 编辑Form -->
      <el-dialog title="编辑" :visible.sync="dialogFormVisible" width="500px" :close-on-click-modal="false" :before-close="cancel">
        <el-form :model="form">
          <el-form-item label="uid" :label-width="formLabelWidth">
            <el-input v-model="form.uid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="职工号" :label-width="formLabelWidth">
            <el-input v-model="form.jobid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="formLabelWidth">
            <el-input v-model="form.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色" :label-width="formLabelWidth">
            <el-select v-model="form.isManager" placeholder="请选择活动区域">
              <el-option label="管理员" value="管理员"></el-option>
              <el-option label="普通用户" value="普通用户"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="edit">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div>
      <!-- 添加Form -->
      <el-dialog title="添加" :visible.sync="dialogFormVisibleAdd"  width="500px" :close-on-click-modal="false" :before-close="clearDialog">
        <el-form :model="formAdd" ref='formAdd'>
          <el-form-item label="职工号" :label-width="formLabelWidth">
            <el-input v-model="formAdd.jobid" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-input v-model="formAdd.phone" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="formLabelWidth">
            <el-input v-model="formAdd.email" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="角色" :label-width="formLabelWidth">
            <el-select v-model="formAdd.isManager" placeholder="请选择活动区域">
              <el-option label="管理员" value="管理员"></el-option>
              <el-option label="普通用户" value="普通用户"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="clearDialog">取 消</el-button>
          <el-button type="primary" @click="insert">确 定</el-button>
        </div>
      </el-dialog>
    </div>

  </el-main>
</template>

<script>
export default {
  name: "Registration",
  data() {
    return {
      loading: true,
      tableData: [],
      pageNum:1,
      pageSize:10,
      total: 0,
      dialogFormVisible: false,
      dialogFormVisibleAdd: false,
      form: {},
      formAdd:{},
      formLabelWidth: '60px',
      multipleSelection: [],
    }
  },
  methods: {
    formatTime(row, column){
      let data = row[column.property]
      let dtime = new Date(data)
      const year = dtime.getFullYear()
      let month = dtime.getMonth() + 1
      if (month < 10) {
        month = '0' + month
      }
      let day = dtime.getDate()
      if (day < 10) {
        day = '0' + day
      }
      let hour = dtime.getHours()
      if (hour < 10) {
        hour = '0' + hour
      }
      let minute = dtime.getMinutes()
      if (minute < 10) {
        minute = '0' + minute
      }
      let second = dtime.getSeconds()
      if (second < 10) {
        second = '0' + second
      }
      return year+ '-' + month+ '-' + day + ' ' + hour + ':' + minute + ':' + second
    },
    //获取user，分页
    load(){
      this.request.get("/registration",{params:{
          pageNum: this.pageNum,
          pageSize:this.pageSize
        }}).then((res)=>{
        console.log(res)
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
    //删除
    handleDelete(index, row) {
      this.$confirm('确认删除？',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        this.request.delete('/user/'+row.uid).then((res)=>{
          if (res.code==='200'){
            this.$message({
              message:'删除成功',
              type:"success"
            })
            this.load()
          }
        })
      }).catch((err)=>{
        console.log(err)
      })
    },
    //批量删除
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    DeleteIds(){
      let ids = this.multipleSelection.map(v => v.uid)
      this.$confirm('确认删除'+this.multipleSelection.length+'条信息吗？',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        this.request.post('/user/ids',ids).then((res)=>{
          if (res.code==='200'){
            this.$message({
              message:'删除成功',
              type:"success"
            })
            this.load()
          }
        })
      }).catch(()=>{

      })
    },
    //添加
    insert(){
      this.dialogFormVisible = false
      console.log(this.formAdd)
      let formData = this.formAdd
      if (formData !=null){
        this.request.post('/user',formData).then((res)=>{
          if (res.code==='200'){
            this.$message({
              message:'添加成功',
              type:"success"
            })
            this.load()
            this.clearDialog()
          }
        }).catch((res)=>{
          console.log(res)
          this.$message({
            message:'添加失败',
            type:"error"
          })
        })
      }
    },
    //编辑
    handleEdit(index, row) {
      this.dialogFormVisible = true
      this.form = row
    },
    edit(){
      this.request.put('/user',this.form).then((res)=>{
        this.dialogFormVisible = false
        if (res.code==='200'){
          this.$message({
            message:'编辑成功',
            type:"success"
          })
        }
      })
    },
    //取消编辑时，重新加载数据
    cancel(){
      this.dialogFormVisible = false
      this.load()
    },
    //清空对话框表单
    clearDialog(){
      this.dialogFormVisibleAdd = false
      this.formAdd = {}
    },
  },
  created() {
    this.load()
  }
}
</script>

<style scoped>

</style>