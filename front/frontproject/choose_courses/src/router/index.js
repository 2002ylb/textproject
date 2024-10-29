import Vue from 'vue';
import VueRouter from 'vue-router'
// import MyAbout from '../components/MyAbout.vue'
// 导入 vue-router 依赖
Vue.use(VueRouter)
export default new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/Manage',   // 跳转路径
            name: 'Manage',//起名字
            redirect:"/Manage/MyHome",
            component: () => import('../components/Manage.vue'), // 组件导入
            children: [
                {
                    path: 'MyHome',   // 跳转路径
                    name: 'MyHome',//起名字
                    component: () => import('../components/MyHome.vue')
                },
                {
                    path: 'MyUser',   // 跳转路径
                    name: 'MyUser',//起名字
                    component: () => import('../components/MyUser.vue')
                },
               
               
            ]
        },

        {
            path: '/',   // 跳转路径
            name: 'MyLogin',//起名字
            
            component: () => import('../components/MyLogin.vue'), // 组件导入
           
        },

        {
            path: 'MyInformation', // 新增的路由路径
            name: 'MyInformation', // 新增的路由名称
            component: () => import('../components/MyInformation.vue') // 新增的组件
          }
    ]
});
