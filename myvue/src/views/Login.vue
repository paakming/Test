<template>
  <div class="div">
    <div style="margin: 200px auto;background-color: #ffffff;width: 350px;height: 300px;padding: 20px;border-radius: 10px">
      <div style="margin: 20px 0;text-align: center;font-size: 24px"><b>登 录</b></div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="40px" class="demo-ruleForm">
        <el-form-item label="账号"  prop="account">
          <el-input type="text"  v-model="ruleForm.account" prefix-icon="el-icon-user" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" prefix-icon="el-icon-lock" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button size="medium" type="primary" class="btn" @click="submitForm('ruleForm')">登录</el-button>
          <a href="javascript:;" style="font-size: 12px;" @click="register">注册账号</a>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      callback()
    };
    var validateCount = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'));
      }
      callback()
    };
    return {
      user: "",
      ruleForm: {
        pass: '',
        account:''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        account: [
          {validator: validateCount, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        var jobid = this.ruleForm.account
        var password = this.ruleForm.pass
        if (valid) {
          this.request.post("/login",{
                jobid: jobid,
                password: password
            }).then((res) => {
              localStorage.setItem("user",JSON.stringify(res.data))
              if (res.code=="200" && res.data.isManager === '管理员'){
                this.$router.push({path:"/manage/home"})
              }
              if (res.code=="200" && res.data.isManager === '普通用户'){
                this.$router.push({path:"/doctor/doctorhome"})
              }
              if (res.code=="500") {
                this.$message.error(res.msg)
              }
          }).catch((res)=>{
            console.log(res)
          })
        }
      });
    },
    register(){
      this.$router.push({path:"/register"})
    }
  }
}
</script>

<style scoped>
  a{
    text-decoration: none;
    color: #C0C4CC;
  }
  .div{
    height: 100vh;
    background-image: linear-gradient(to bottom right, #F2F6FC, #409EFF);
    overflow: hidden;
  }
  .btn{
    width: 100%;
  }
</style>