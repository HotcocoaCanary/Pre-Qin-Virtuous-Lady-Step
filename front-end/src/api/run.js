//导入request.js请求工具
import request from '@/utils/request.js'

//提供调用打卡方法的接口
export const runClockService = (LocationData)=>{
    //借助于UrlSearchParams完成传递
    const params = new URLSearchParams()
    for(let key in LocationData){
        params.append(key,LocationData[key]);
    }
    return request.post('/run/clock',params);
}
export const runReportService = ()=>{
    return request.post('/run/report')
}
