<template>
  <div style="width: 800px;margin-left: 400px;margin-top: 50px">
    <div style="float: right" @click="changPwd"><el-button>修改密码</el-button></div>
    <el-descriptions class="margin-top" title="个人信息"  :column="1" border>
      <el-descriptions-item label="职工号">{{user.jobid}}</el-descriptions-item>
      <el-descriptions-item label="姓名">{{user.doctor.name}}</el-descriptions-item>
      <el-descriptions-item label="性别">{{user.doctor.sex}}</el-descriptions-item>
      <el-descriptions-item label="职称">{{user.doctor.position}}</el-descriptions-item>
      <el-descriptions-item label="部门">{{user.doctor.department}}</el-descriptions-item>
      <el-descriptions-item label="手机号">{{user.phone}}</el-descriptions-item>
      <el-descriptions-item label="邮箱">{{user.email}}</el-descriptions-item>
      <el-descriptions-item label="身份证" :span="2">{{user.doctor.identityCard}}</el-descriptions-item>
      <el-descriptions-item label="居住地" :span="2">address</el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  name: "MyInfo",
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods:{
    changPwd(){
      this.$router.push('/manage/changepwd')
    }
  }
}
</script>

<style scoped>

</style>