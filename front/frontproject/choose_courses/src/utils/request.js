import axios from "axios";
import ElementUI from 'element-ui';
const request = axios.create({
	baseURL:"http://localhost:9004",  
	timeout:20000, 
})

// 请求拦截器
request.interceptors.request.use(
	(config)=>{
		if(config.url !== "/"){   // 判断请求是否是登录接口
			config.headers.token = sessionStorage.getItem("token"); // 如果不是登录接口，就给请求头里面设置token
           
		}
		return config; // 返回这个配置对象，如果没有返回，这个请求就不会发送出去
	},
	(error)=>{
		return Promise.reject(error);
	}
)


// 响应拦截器
request.interceptors.response.use(
	(res)=>{
	
		if (res.data.code === 500) {
			
		ElementUI.Message({
			message:res.data.msg,
			type:'error'
		});
		}

		return res 
	},
	(error)=>{
		return Promise.reject(error);
	}
)

  
  







export default request;
