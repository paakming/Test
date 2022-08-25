<template>
  <div>
    <div style="width: 30%;height: 500px;float: left;">
      <el-card class="box-card" style="width: 400px">
        <div style="margin-left: 130px;margin-top: 50px">
          <el-avatar icon="el-icon-user-solid" :size="80"  >
          </el-avatar>
        </div>
        <div style="margin-top: 50px">
          <el-descriptions title="用户信息"  :content-style="contextStyle" :label-style="labelStyle" :column="1" >
            <el-descriptions-item label="用户名"  >{{user.doctor.name}}</el-descriptions-item>
            <el-descriptions-item label="职工号"  >{{user.jobid}}</el-descriptions-item>
            <el-descriptions-item label="uid"  >{{user.uid}}</el-descriptions-item>
          </el-descriptions>
        </div>
        <br><br><br><br>
      </el-card>
    </div>
    <div style="float: left;width: 70%">
      <el-row :gutter="24">
        <el-col :span="7" :offset="1"><div ><el-card class="box-card">
          <div>用户总数</div>
          <div style="text-align: center;padding-top: 20px;font-weight: bold">{{userNum}}</div>
        </el-card>
        </div></el-col>
        <el-col :span="7"><div ><el-card class="box-card">
          <div>处方总数</div>
          <div style="text-align: center;padding-top: 20px;font-weight: bold">{{prescriptionNum}}</div>
        </el-card>
        </div></el-col>
        <el-col :span="7"><div ><el-card class="box-card">
          <div>挂号总数</div>
          <div style="text-align: center;padding-top: 20px;font-weight: bold">{{registrationNum}}</div>
        </el-card>
        </div></el-col>
      </el-row>
    </div>
    <div style="float: left;width: 70%;margin-top: 100px;position: relative">
      <el-row :gutter="20">
        <el-col :span="5" ><div id="bar" style="width: 400px;height: 400px"></div></el-col>
        <el-col :span="5" :offset="5" ><div id="pie" style="width: 400px;height: 400px"></div></el-col>
      </el-row>
    </div>

  </div>
</template>

<script>

import * as echarts from 'echarts';

export default {
  name: "Home",
  data(){
    return{
      userNum:'',
      prescriptionNum:'',
      registrationNum:'',
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
  mounted() {
    this.request.get('/echarts/registrationNum').then(res =>{
      this.registrationNum = res.data.length;
    })

    var chartDom = document.getElementById('bar')
    var myChart = echarts.init(chartDom)
    var option = {
      title: {
        text: '各季度用户数量',
        /*subtext: 'Fake Data',*/
        left: 'center'
      },
      xAxis: {
        type: 'category',
            data: ['第一季度', '第二季度', '第三季度', '第四季度']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'bar'
        }
      ]
    }
    this.request.get('/echarts/user').then(res =>{
      this.userNum = res.data[0]+res.data[1]+res.data[2]+res.data[3]
      option.series[0].data = res.data
      myChart.setOption(option);
    })

    var pieChartDom = document.getElementById('pie');
    var pieChart = echarts.init(pieChartDom);
    var pieOption =  {
      title: {
        text: '各季度处方数量',
        /*subtext: 'Fake Data',*/
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: 'bottom',
        left: 'center'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: '40',
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
          data: [

          ]
        }
      ]
    };
    this.request.get('/echarts/prescription').then(res => {
      this.prescriptionNum = res.data[0]+res.data[1]+res.data[2]+res.data[3]
      pieOption.series[0].data = [
        {name:'第一季度', value:res.data[0]},
        {name:'第二季度', value:res.data[1]},
        {name:'第三季度', value:res.data[2]},
        {name:'第四季度', value:res.data[3]},
      ]
      pieChart.setOption(pieOption);
    })
  }

}
</script>
<style scoped>

</style>