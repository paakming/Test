import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './assets/global.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueRouter from "vue-router";
import request from "@/utils/request";


Vue.config.productionTip = false
Vue.prototype.request=request

Vue.use(ElementUI,{size:"small"})
Vue.use(VueRouter)
Vue.use(VueAxios, axios)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
