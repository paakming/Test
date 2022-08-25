<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="8" :offset="8">
        <div >
          <el-card class="box-card" style="width: 400px;height: 500px">
            <div style="margin-left: 130px;margin-top: 50px">
              <el-avatar icon="el-icon-user-solid" :size="80"  >
              </el-avatar>
            </div>
            <div >
              <el-descriptions title="用户信息"  :content-style="contextStyle" :label-style="labelStyle" :column="1" >
                <el-descriptions-item label="用户名"  >{{user.doctor.name}}</el-descriptions-item>
                <el-descriptions-item label="职工号"  >{{user.jobid}}</el-descriptions-item>
                <el-descriptions-item label="uid"  >{{user.uid}}</el-descriptions-item>
              </el-descriptions>
            </div>
            <br><br><br><br>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "DoctorHome",
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      contextStyle:{
        "font-size":"20px",
        "padding-top":"10px"
      },
      labelStyle:{
        "font-size":"20px",
        "padding-top":"10px"
      }
    }
  },
  methods:{

  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 400px;
  height: 200px;
}
</style>