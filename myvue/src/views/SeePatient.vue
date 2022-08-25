<template>
  <el-tabs v-model="activeName" @tab-click="test">
    <el-tab-pane label="代诊人员" name="first" ></el-tab-pane>
    <el-tab-pane label="诊疗界面" name="second"></el-tab-pane>
    <router-view/>
  </el-tabs>
</template>

<script>
export default {
  name: "SeePatient",
  data(){
    return{
      activeName:'first'
    }
  },
  methods:{
    test(tab){
      if (tab.label ==='代诊人员'){
        this.$router.push({path:'/doctor/seepatient/patientlist'})
      }
      if (tab.label ==='诊疗界面'){
        this.$router.push({path:'/doctor/seepatient/patientmain'})
      }
/*      switch (tab.label){
        case '代诊人员' :this.$router.push({path:'/doctor/seepatient/patientlist'})
              break
        case '诊疗界面' :this.$router.push({path:'/doctor/seepatient/patientmain'})
              break
      }*/
    },
  }
}
</script>

<style scoped>

</style>