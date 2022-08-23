<template>
<div style="margin-left: 100px" @click="test">
  <el-card class="box-card">
    <div class="text item">
        看诊
    </div>
  </el-card>
</div>
</template>

<script>
export default {
  name: "DoctorHome",
  methods:{
    test(){
      alert('111')
    }
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