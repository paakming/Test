<template>
  <el-main>
<!--    <div style="padding: 10px 0">
      <el-input v-model="searchjobid" suffix-icon="el-icon-search"  placeholder="输入职工号查询" style="width: 200px"></el-input>
      <el-button type="primary" icon="el-icon-search" style="margin-left: 5px" @click="search">搜索</el-button>
      <el-button type="primary"  style="margin-left: 5px" @click="load">显示所有</el-button>
    </div>-->
    <div>
      <el-button type="primary" icon="el-icon-circle-plus-outline" style="margin-left: 5px" @click="dialogFormVisibleAdd = true">添加</el-button>
      <el-button type="danger" icon="el-icon-remove-outline"  :disabled="multipleSelection.length<=0" style="margin-left: 5px" @click="DeleteIds">批量删除</el-button>
    </div>
    <el-table :data="tableData" border stripe v-loading="loading"
              tooltip-effect="dark"
              @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="pid" label="患者编号" >
      </el-table-column>
      <el-table-column prop="pname" label="姓名" >
      </el-table-column>
      <el-table-column prop="sex" label="性别" >
      </el-table-column>
      <el-table-column prop="age" label="年龄" >
      </el-table-column>
      <el-table-column prop="birthday" :formatter="formatTime" label="生日" >
      </el-table-column>
      <el-table-column prop="phone" label="手机号" >
      </el-table-column>
      <el-table-column prop="identityCard" label="身份证" >
      </el-table-column>
      <el-table-column  label="操作"  width="200" fixed="right" >
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" :disabled="multipleSelection.length>1" @click="handleDelete(scope.$index, scope.row)">删除<i class="el-icon-delete"></i></el-button>
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
      <el-dialog title="编辑" :visible.sync="dialogFormVisible"  width="500px" :close-on-click-modal="false"  :before-close="cancel">
        <el-form :model="form">
          <el-form-item label="患者编号" :label-width="formLabelWidth">
            <el-input v-model="form.pid" :disabled="true" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="form.pname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-select v-model="form.sex" placeholder="性别" style="width: 100%;">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年龄" :label-width="formLabelWidth">
            <el-input v-model="form.age" :disabled="true" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="生日" :label-width="formLabelWidth">
              <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="form.birthday" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="身份证" :label-width="formLabelWidth">
            <el-input v-model="form.identityCard" autocomplete="off"></el-input>
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
      <el-dialog title="添加" :visible.sync="dialogFormVisibleAdd" width="500px"  :close-on-click-modal="false" :before-close="clearDialog">
        <el-form :model="formAdd" ref='formAdd'>
          <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="formAdd.pname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-select v-model="formAdd.sex" placeholder="性别" style="width: 100%;">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="生日" :label-width="formLabelWidth">
            <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="formAdd.birthday" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-input v-model="formAdd.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="身份证" :label-width="formLabelWidth">
            <el-input v-model="formAdd.identityCard" autocomplete="off"></el-input>
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
  name: "PatientManage",
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
      formLabelWidth: '70px',
      multipleSelection: [],
      searchjobid:''
    }
  },
  methods: {
    //获取doctor，分页
    load(){
      this.request.get("/patient",{params:{
          pageNum: this.pageNum,
          pageSize:this.pageSize
        }}).then((res)=>{
        this.tableData = res.data.list
        this.total = res.data.total
        this.loading = false;
        this.searchjobid = ''
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
    //搜索
    search(){
      if (this.searchjobid !=''){
        this.request.get("/patient",{params:{
            pageNum: this.pageNum,
            pageSize:this.pageSize,
            jobid:this.searchjobid
          }}).then((res)=>{
          this.tableData = res.list
          this.total = res.total
          this.loading = false;
        })
      }else{
      }
    },
    //删除
    handleDelete(index, row) {
      this.$confirm('确认删除？',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        this.request.delete('/patient/'+row.pid).then((res)=>{
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
    //批量删除
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    DeleteIds(){
      let ids = this.multipleSelection.map(v => v.pid)
      this.$confirm('确认删除'+this.multipleSelection.length+'条信息吗？',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        console.log(ids)
        this.request.post('/patient/ids',ids).then((res)=>{
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
      let formData = this.formAdd
      if (formData !=null){
        this.request.post('/patient',formData).then((res)=>{
          if (res.code==='200'){
            this.$message({
              message:'添加成功',
              type:"success"
            })
            this.load()
            this.clearDialog()
          }else {
            this.$message({
              message:res.msg,
              type:"error"
            })
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
      this.request.put('/patient',this.form).then((res)=>{
        this.dialogFormVisible = false
        if (res.code==='200'){
          this.$message({
            message:'编辑成功',
            type:"success"
          })
        }else {
          this.$message({
            message:res.msg,
            type:"error"
          })
        }
        this.load();
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
    //时间显示格式
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
      return year+ '-' + month+ '-' + day
    },
  },
  created() {
    this.load()
  }
}
</script>

<style scoped>

</style>