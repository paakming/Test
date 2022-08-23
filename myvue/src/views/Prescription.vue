<template>
  <el-main>
    <div style="padding: 10px 0">
      <el-input v-model="searchjobid" suffix-icon="el-icon-search"  placeholder="输入职工号查询" style="width: 200px"></el-input>
      <el-button type="primary" icon="el-icon-search" style="margin-left: 5px" @click="search">搜索</el-button>
      <el-button type="primary"  style="margin-left: 5px" @click="load">显示所有</el-button>
    </div>
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
      <el-table-column prop="cfid" label="处方编号" >
      </el-table-column>
      <el-table-column prop="patient.pname" label="患者姓名" >
      </el-table-column>
      <el-table-column prop="doctor.name" label="主治医师" >
      </el-table-column>
      <el-table-column prop="message" label="诊疗信息" :show-overflow-tooltip='true' >
      </el-table-column>
      <el-table-column prop="opentime" :formatter="formatTime" label="开具时间" >
      </el-table-column>
      <el-table-column prop="totalPrice" label="金额" >
      </el-table-column>
      <el-table-column  label="操作"  width="300" fixed="right" >
        <template slot-scope="scope">
          <el-button @click="detail(scope.$index, scope.row)" >查看<i class=""></i></el-button>
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
          <el-form-item label="处方号" :label-width="formLabelWidth">
            <el-input v-model="form.cfid" :disabled="true" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="患者姓名" :label-width="formLabelWidth">
            <el-input v-model="form.patient.pname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="主治医师" :label-width="formLabelWidth">
            <el-input v-model="form.doctor.name"  autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="诊疗信息" :label-width="formLabelWidth">
            <el-input v-model="form.message" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="金额" :label-width="formLabelWidth">
            <el-input v-model="form.totalPrice" :disabled="true" autocomplete="off"></el-input>
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
          <el-form-item label="处方号" :label-width="formLabelWidth">
            <el-input v-model="formAdd.cfid"  autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="患者姓名" :label-width="formLabelWidth">
            <el-input v-model="formAdd.pname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="主治医师" :label-width="formLabelWidth">
            <el-input v-model="formAdd.name"  autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="诊疗信息" :label-width="formLabelWidth">
            <el-input v-model="formAdd.message" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="clearDialog">取 消</el-button>
          <el-button type="primary" @click="insert">确 定</el-button>
        </div>
      </el-dialog>
      <!-- Table -->
      <el-dialog title="处方详细" :visible.sync="dialogTableVisible" center width="800px">
        <el-table :data="gridData">
          <el-table-column property="medicine.drugname" label="药品名" width="150"></el-table-column>
          <el-table-column property="mid" label="药品编号" width="150"></el-table-column>
          <el-table-column property="quantity" label="数量" width="100"></el-table-column>
          <el-table-column property="usage" label="用法"></el-table-column>
          <el-table-column  label="操作"  fixed="right" >
           <template slot-scope="scope">
              <el-button type="primary" @click="detialEdit(scope.$index, scope.row)">编辑<i class="el-icon-edit"></i></el-button>
              <el-button type="danger" @click="detailDelete(scope.$index, scope.row)" >删除<i class="el-icon-delete"></i></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>
      <!-- Table-Form -->
      <el-dialog title="修改" :visible.sync="dialogTableFormVisible" width="500px"  :close-on-click-modal="false" >
        <el-form :model="detailForm" >
          <el-form-item label="处方号" :label-width="formLabelWidth">
            <el-input v-model="detailForm.cfid"  autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="药品名" :label-width="formLabelWidth">
            <el-input v-model="detailForm.medicine.drugname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="数量" :label-width="formLabelWidth">
            <el-input v-model="detailForm.quantity"  autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用法" :label-width="formLabelWidth">
            <el-input v-model="detailForm.usage" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="updateDetail">确 定</el-button>
        </div>
      </el-dialog>

    </div>
  </el-main>
</template>

<script>
export default {
  name: "Prescription",
  data() {
    return {
      loading: true,
      tableData: [],
      pageNum:1,
      pageSize:10,
      total: 0,
      dialogFormVisible: false,
      dialogFormVisibleAdd: false,
      dialogTableVisible: false,
      dialogTableFormVisible: false,
      gridData:[],
      form: {
        doctor:'',
        patient:''
      },
      formAdd:{},
      detailForm:{
        medicine:''
      },
      doctor:'',
      patient:'',
      formLabelWidth: '70px',
      multipleSelection: [],
      searchjobid:''
    }
  },
  methods: {
    //获取doctor，分页
    load(){
      this.request.get("/prescription",{params:{
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
    //detail
    detail(index, row){
      this.dialogTableVisible = true
      this.request.get("/detail/"+row.cfid).then((res)=>{
        this.gridData = res.data
      })
    },
    //搜索
    search(){
      if (this.searchjobid !=''){
        this.request.get("/prescription",{params:{
            pageNum: this.pageNum,
            pageSize:this.pageSize,
            jobid:this.searchjobid
          }}).then((res)=>{
          this.tableData = res.list
          this.total = res.total
          this.loading = false;
        })
      }
    },
    //删除
    handleDelete(index, row) {
      this.$confirm('确认删除？',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        this.request.delete('/prescription/'+row.cfid).then((res)=>{
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
      let ids = this.multipleSelection.map(v => v.cfid)
      this.$confirm('确认删除'+this.multipleSelection.length+'条信息吗？',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        console.log(ids)
        this.request.post('/prescription/ids',ids).then((res)=>{
          if (res.code==='200'){
            this.$message({
              message:'删除成功',
              type:"success"
            })
            this.load()
          }
        })
      }).catch(err => {
        console.log(err)
      })
    },
    //添加
    insert(){
      this.dialogFormVisible = false
      let formData = this.formAdd
      if (formData !=null){
        console.log(formData)
        this.request.post('/prescription',formData).then((res)=>{
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
      this.request.put('/prescription',this.form).then((res)=>{
        this.dialogFormVisible = false
        if (res.code==='200'){
          this.$message({
            message:'编辑成功',
            type:"success"
          })
        }
        this.load();
      })
    },
    //取消编辑时，重新加载数据
    cancel(){
      this.dialogFormVisible = false
      this.dialogTableFormVisible = false
      this.load()
    },
    //清空对话框表单
    clearDialog(){
      this.dialogFormVisibleAdd = false
      this.formAdd = {}
    },
    detialEdit(index, row) {
      this.dialogTableFormVisible = true
      this.detailForm = row
    },
    updateDetail(){
      this.request.put('/detail',this.detailForm).then((res)=>{
        this.dialogTableFormVisible = false
        if (res.code==='200'){
          this.$message({
            message:'编辑成功',
            type:"success"
          })
        }
      })
    },
    detailDelete(index,row){
      this.$confirm('确认删除？',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        this.request.delete('/detail/'+row.did).then((res)=>{
          if (res.code==='200'){
            this.$message({
              message:'删除成功',
              type:"success"
            })
            this.dialogTableVisible = false;
          }
        })
      }).catch((err)=>{
        console.log(err)
      })
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