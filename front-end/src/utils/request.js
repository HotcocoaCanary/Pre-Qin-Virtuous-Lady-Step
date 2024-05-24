//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';

//定义一个变量,记录公共的前缀  ,  baseURL
//const baseURL = 'http://localhost:8080';
const baseURL = '/api';
const instance = axios.create({ baseURL })

//添加请求拦截器
instance.interceptors.request.use(
    result=>{
        return result.data;
    },
    err=>{
        alert("服务异常");
        return Promise.reject(err);
    }
)



export default instance;
