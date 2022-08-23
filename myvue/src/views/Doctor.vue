<template>
  <div style="width: 100%;overflow-x: hidden">
    <el-row>
      <el-col :span="24"><div><HeaderDoctor/></div></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4"><div class="grid-content bg-purple"><router-view/></div></el-col>
    </el-row>
  </div>
</template>

<script>

import HeaderDoctor from "@/components/HeaderDoctor";
export default {
  name: "Doctor",
  components: {HeaderDoctor},
}
</script>

<style scoped>

</style>