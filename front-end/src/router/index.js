import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/views/Login.vue";
import Home from "@/views/home/Home.vue";
import Container from "@/views/Container.vue";
import Start from "@/views/run/Start.vue";
import Plan from "@/views/run/Plan.vue";
import Report from "@/views/user/Report.vue";
import Info from "@/views/user/Info.vue";

//导入组件

//定义路由关系
const routes = [
    {
        path: '/login',
        component:Login,
    },
    {
        path: '/',
        component: Container,
        redirect: '/kk',
        children: [
            {
                path: '/kk',
                component: Home
            },
            {
                path: '/run/start',
                component: Start
            },
            {
                path: '/run/plan',
                component: Plan
            },
            {
                path: '/user/report',
                component: Report
            },
            {
                path: '/user/info',
                component: Info
            }
        ]
    }
]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

//导出路由
export default router
