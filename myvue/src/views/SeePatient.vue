<template>
  <el-tabs @tab-click="test">
    <el-tab-pane label="代诊人员" ></el-tab-pane>
    <el-tab-pane label="诊疗界面" ></el-tab-pane>
    <router-view/>
  </el-tabs>
</template>

<script>
export default {
  name: "SeePatient",
  methods:{
    test(tab){
      switch (tab.label){
        case '代诊人员' :this.$router.push({path:'/doctor/seepatient/patientlist'})
              break
        case '诊疗界面' :this.$router.push({path:'/doctor/seepatient/patientmain'})
              break
      }
    },
  }
}
</script>

<style scoped>

</style>