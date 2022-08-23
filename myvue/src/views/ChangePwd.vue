<template>
  <div>
    <div ><el-button icon="el-icon-back" @click="back" >返回</el-button></div>
    <div style="margin: 200px auto;width: 550px;height: 300px;padding: 20px;border-radius: 10px">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="旧密码" prop="oldPass">
          <el-input type="password" v-model="ruleForm.oldPass" :show-password="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPass">
          <el-input type="password" v-model="ruleForm.newPass"  :show-password="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" :show-password="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
export default {
  name: "ChangePwd",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      callback()
    };
    var validatePass1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }else if (value === this.ruleForm.oldPass){
        callback(new Error('新密码不能与旧密码相同'));
      }
      callback()
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.newPass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      ruleForm: {
        oldPass: '',
        checkPass: '',
        newPass: '',
      },
      rules: {
        oldPass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        newPass: [
          { validator: validatePass1, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        var uid = this.user.uid
        var oldPass = this.ruleForm.oldPass
        var newPass = this.ruleForm.newPass
        var checkPass = this.ruleForm.checkPass
        if (valid) {
          this.$confirm('确认修改密码？',{
            type:"warning"
          }).then(()=>{
            this.request.post('/user/changePwd/'+uid+'/'+oldPass+'/'+newPass+'/'+checkPass).then((res)=>{
              if (res.code==='200'){
                this.$message({
                  message:'修改密码成功,请重新登录',
                  type:"success"
                })
                localStorage.removeItem("user")
                this.$router.push("/")
              }else{
                this.$message({
                  message:res.msg,
                  type:"error"
                })
              }
            })
          })
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    back(){
      this.$router.push({path:"/manage/myinfo"})
    }
  }
}
</script>

<style scoped>

</style>