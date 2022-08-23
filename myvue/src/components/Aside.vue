<template>
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%;overflow-x: hidden"
               background-color="rgb(48,65,86)" text-color="#fff" active-text-color="#74ea74"
               :collapse-transition="false"         :collapse="isCollapse"
               router
      >
        <div style="height: 60px;line-height: 60px;text-align: center">
          <img src="../assets/logo.png" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px">
          <b style="color: white" v-show="!isCollapse">后台管理系统</b>
        </div>
        <el-menu-item index="/manage/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">主页</span>
        </el-menu-item>
        <el-menu-item index="/manage/registration">
          <i class="el-icon-s-order"></i>
          <span slot="title">挂号管理</span>
        </el-menu-item>
        <el-menu-item index="/manage/prescription">
          <i class="el-icon-tickets"></i>
          <span slot="title">处方管理</span>
        </el-menu-item>
<!--        <el-menu-item index="/manage/medicine">
          <i class="el-icon-s-goods"></i>
          <span slot="title">药品管理</span>
        </el-menu-item>-->
<!--        <el-menu-item index="/manage/finance">
          <i class="el-icon-coin"></i>
          <span slot="title">财务管理</span>
        </el-menu-item>-->
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-user-solid"></i>
            <span slot="title">人员管理</span>
          </template>
          <el-menu-item-group>
            <template slot="title"></template>
            <el-menu-item index="/manage/user">
              <i class="el-icon-s-custom"></i>
              <span>用户管理</span>
            </el-menu-item>
            <el-menu-item index="/manage/staff">
              <i class="el-icon-s-custom"></i>
              <span>员工管理</span>
            </el-menu-item>
            <el-menu-item index="/manage/patientManage">
              <i class="el-icon-s-custom"></i>
              <span>就诊人管理</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
<!--    </el-aside>-->
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse: Boolean
  }
}
</script>

<style scoped>

</style>