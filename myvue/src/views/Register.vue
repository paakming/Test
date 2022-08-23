<template>
  <div class="div">
    <div style="margin: 200px auto;background-color: #ffffff;width: 450px;height: 400px;padding: 20px;border-radius: 10px">
      <div ><i class="el-icon-back" @click="back"></i></div>
      <div style="margin: 30px 0;text-align: center;font-size: 24px"><b>注 册 账 号</b></div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="55px" class="demo-ruleForm">
        <el-form-item label="手机号"  prop="phone">
          <el-input type="text"  v-model="ruleForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input type="text" v-model="ruleForm.email"  autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button size="medium" type="primary" class="btn" @click="submitForm('ruleForm')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Resgister",
  data() {
    var validateCount = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号'));
      }
      callback()
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手密码'));
      }
      callback()
    };
    var validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'));
      }
      callback()
    };
    return {
      user: "",
      ruleForm: {
        pass: '',
        phone:'',
        email:''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        phone: [
          {validator: validateCount, trigger: 'blur'}
        ],
        email: [
          {validator: validateEmail, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        console.log(this.ruleForm)
        var phone = this.ruleForm.phone
        var password = this.ruleForm.pass
        var email = this.ruleForm.email
        if (valid) {
          this.request.post("/register",{
            phone: phone,
            password: password,
            email: email
          }).then((res) => {
            console.log(res)
            if (res.code==='200'){
              this.$message({
                message:'注册成功',
                type:"success"
              })
              this.$router.push({path:"/"})
            }
          }).catch((res)=>{
            console.log(res)
          })
        }
      });
    },
    back(){
      this.$router.push({path:"/"})
    }
  }
}
</script>

<style scoped>
.div{
  height: 100vh;
  background-image: linear-gradient(to bottom right, #F2F6FC, #409EFF);
  overflow: hidden;
}
.btn{
  width: 100%;
}
</style>