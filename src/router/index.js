import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/views/Login.vue";
import Home from "@/views/Home.vue";
import Container from "@/views/Container.vue";

//导入组件

//定义路由关系
const routes = [
    {
        path: '/login',
        component:Login,
        redirect: '/home',
    },
    {
        path: '/',
        component: Container,
        children: [
            {
                path: '/home',
                component: Home
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
