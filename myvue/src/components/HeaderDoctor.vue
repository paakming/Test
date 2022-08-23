<template>
<div>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" style="padding-left: 200px" router>
    <el-menu-item index="/doctor/doctorhome">首页</el-menu-item>
    <el-menu-item index="/doctor/seepatient/patientlist" >看诊</el-menu-item>
    <div style="float:right;font-size: 12px;display: flex;line-height: 60px;padding-right: 200px">
      <el-dropdown style="width:70px; cursor: pointer">
        <span>{{user.doctor.name}}</span><i class="el-icon-arrow-down" style="margin-right: 5px"></i>
        <el-dropdown-menu slot="dropdown" style="width: 100px;margin-top: -10px">
          <el-dropdown-item style="text-align: center;">
            <span @click="info">个人信息</span></el-dropdown-item>
          <el-dropdown-item style="text-align: center">
            <span  @click="logout">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </el-menu>
</div>
</template>

<script>
export default {
  name: "HeaderDoctor",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      activeIndex: '1',
      activeIndex2: '1'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout(){
      this.$confirm('确认退出？',{
        type:"warning"
      }).then(()=>{
        this.$router.push("/")
        localStorage.removeItem("user")
      })
    },
    info(){
      this.$router.push("/doctor/myinfo")
    }
  }
}
</script>

<style scoped>

</style>