import Vue from 'vue'
import App from './App.vue'

import './assets/gloable.css'

import VueRouter from 'vue-router'
import router from './router'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

import request from './utils/request'


Vue.use(ElementUI,{size:"small"});

Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.prototype.request=request
new Vue({
  render: h => h(App),
  router:router,
  beforeCreate() {
    Vue.prototype.$bus=this//安装全局事件总线
  },
  mounted() {
    console.log(this.$bus); // 确保$bus 在此已被安装
  },
}).$mount('#app')


