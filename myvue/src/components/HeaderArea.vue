<template>
  <div style="font-size: 12px;display: flex;line-height: 60px">
    <div style="flex: 1;font-size: 20px;">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="Collapse"></span>
    </div>
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
</template>

<script>
export default {
  name: "HeaderArea",
  props:{
    collapseBtnClass: String,
    Collapse: Function,
  },
  data(){
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods:{
    logout(){
      this.$confirm('确认退出？',{
        type:"warning"
      }).then(()=>{
        this.$router.push("/")
        localStorage.removeItem("user")
      })
    },
    info(){
      this.$router.push("/manage/myinfo")
    }
  }
}
</script>

<style scoped>

</style>