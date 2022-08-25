<template>
  <div>
    <el-descriptions class="margin-top" title="诊疗界面" :column="3" :size="size" border>
    <template slot="extra">
      <el-button type="primary" size="small" @click="dialogFormVisibleAddP=true" :disabled="appear">开具处方</el-button>
    </template>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        姓名
      </template>
      {{context.patient.pname}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        性别
      </template>
      {{context.patient.sex}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        身份证
      </template>
      {{context.patient.identityCard}}
    </el-descriptions-item>
    <el-descriptions-item >
      <template slot="label">
        <i class="el-icon-user"></i>
        生日
      </template>
      {{context.patient.birthday | formatTime}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        年龄
      </template>
      {{context.patient.age}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
      {{context.patient.phone}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        挂号时间
      </template>
      {{context.visitTime | formatTime2}}
    </el-descriptions-item>
    </el-descriptions>
    <div v-if="appear">
      <el-table height="500px" :data="tableData">
        <template v-for="(item,index) in tableHead">
          <el-table-column align="center" :prop="item.column_name" :label="item.column_comment" :key="index"></el-table-column>
        </template>
        <el-table-column   width="200px"  fixed="right" >
          <template slot="header" slot-scope="scope">
            <el-button  icon="el-icon-circle-plus-outline" @click="dialogFormVisibleAdd = true" >添加</el-button>
          </template>
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="handleEdit(scope.$index, scope.row)"></el-button>
            <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.$index, scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <!-- 编辑Form -->
      <el-dialog title="编辑" :visible.sync="dialogFormVisible" width="500px" :close-on-click-modal="false":before-close="cancel">
          <el-form :model="form" ref='formAdd'>
            <el-form-item label="药品名" :label-width="formLabelWidth">
              <el-input v-model="form.medicine.drugname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="数量" :label-width="formLabelWidth">
              <el-input v-model="form.quantity" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="价格" :label-width="formLabelWidth">
              <el-input v-model="form.medicine.drugprice" autocomplete="off" disabled></el-input>
            </el-form-item>
            <el-form-item label="用法" :label-width="formLabelWidth">
              <el-input v-model="form.usage" autocomplete="off"></el-input>
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
          <el-form-item label="药品名" :label-width="formLabelWidth">
            <el-input v-model="formAdd.medicine.drugname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="数量" :label-width="formLabelWidth">
            <el-input v-model="formAdd.quantity" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用法" :label-width="formLabelWidth">
            <el-input v-model="formAdd.usage" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="clearDialog">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <!-- 添加Form chufang-->
      <el-dialog title="开具处方" :visible.sync="dialogFormVisibleAddP" width="500px"    :before-close="clearDialog2">
        <el-form :model="formAddP" >
          <el-form-item label="药品名" :label-width="formLabelWidth2">
            <el-input v-model="formAddP.drugname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="数量" :label-width="formLabelWidth2">
            <el-input v-model="formAddP.quantity" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用法" :label-width="formLabelWidth2">
            <el-input v-model="formAddP.usage" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="诊疗信息" :label-width="formLabelWidth2">
            <el-input type="textarea" :rows="3" v-model="formAddP.message" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="clearDialog2">取 消</el-button>
          <el-button type="primary" @click="addPrescription">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "PatientMain",
  data(){
    return{
      dialogFormVisible: false,
      dialogFormVisibleAdd: false,
      dialogFormVisibleAddP: false,
      form: {
        medicine:{
          drugname:''
        }
      },
      formAdd:{
        cfid:'',
        medicine:{
          drugname:''
        }
      },
      formAddP: {

      },
      formLabelWidth: '60px',
      formLabelWidth2: '80px',
      context: {
        patient:'',
        cfid:''
      },
      size: '',
      appear:false,
      tableHead:[
        {
          column_name: "medicine.drugname",column_comment:"药品名"
        },
        {
          column_name: "quantity",column_comment:"数量"
        },
        {
          column_name: "medicine.drugprice",column_comment:"价格"
        },
        {
          column_name: "usage",column_comment:"用法"
        }
      ],
      tableData: [],
    }
  },
  filters:{
    formatTime(value){
      let data = value
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
    formatTime2(value){
      let data = value
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
  },
  methods:{
    addPrescription(){
      let rid = this.context.rid
      let jobid = this.context.jobid
      let pid = this.context.pid;
      let message = this.formAddP.message
      let drugname = this.formAddP.drugname
      let usage = this.formAddP.usage
      let quantity = this.formAddP.quantity
      if (typeof pid === 'undefined' ||typeof rid === 'undefined'||typeof jobid === 'undefined'||typeof message === 'undefined'||typeof drugname === 'undefined'
            || typeof usage === 'undefined' ||typeof quantity === 'undefined'){
          this.$message({
            message:'请正确填写处方信息',
            type:"error"
          })

      }else {
        this.request.post('/prescription/'+pid+'/'+jobid+'/'+message+'/'+drugname+'/'+usage+'/'+quantity+'/'+rid).then((res)=>{
          this.dialogFormVisibleAddP = false
          let cfid = res.data
          if (res.code==='200'){
            this.$message({
              message:'添加成功',
              type:"success"
            })
            this.request.get('/detail/'+cfid).then((res)=>{
              this.tableData = res.data
            })
            this.appear = true
          }else {
            this.$message({
              message:res.msg,
              type:"error"
            })
          }
        })
      }

    },
    getPrescription(){
      let cfid = this.context.cfid
      if (cfid != null){
        this.request.get('/detail/'+cfid).then((res)=>{
          this.tableData = res.data
        })
        this.appear = true
      }
    },
    //删除
    handleDelete(index, row) {
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
            //this.load()
            this.getPrescription()
          }
        })
      }).catch(err=>{
          console.log(err)
      })
    },
    //编辑
    handleEdit(index, row) {
      this.dialogFormVisible = true
      this.form = row
    },
    edit(){
      this.request.put('/detail',this.form).then((res)=>{
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
        this.getPrescription();
      })
    },
    //取消编辑时，重新加载数据
    cancel(){
      this.dialogFormVisible = false
      this.getPrescription()
    },
    clearDialog(){
      this.dialogFormVisibleAdd = false
      this.formAdd = {
        cfid:'',
            medicine:{
          drugname:''
        }
      }
    },
    clearDialog2(){
      this.dialogFormVisibleAddP = false
      this.formAddP = {}
    },
    add(){
      this.formAdd.cfid = this.tableData[0].cfid
      this.request.post('/detail',this.formAdd).then((res)=>{
        if (res.code==='200'){
          this.$message({
            message:'添加成功',
            type:"success"
          })
          this.getPrescription()
          this.clearDialog()
        }else {
          this.$message({
            message:res.msg,
            type:"error"
          })
        }
      })
    }

  },
  mounted () {
    this.context = this.$route.params.context
    if(this.context!== null){
      this.getPrescription()
    }
  },
  beforeMount() {
    this.context = this.$route.params.context
    if(this.context.patient ===null){
      this.$alert('请选择患者', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          this.$router.push({path:'/doctor/seepatient/patientlist'})
        }
      });
    }
  }
}
</script>

<style scoped>

</style>