<template>
  <div id="app">
    <div class="login-register-container">
      <div v-if="isLogin" class="form-container">
        <h2>登录</h2>
        <form @submit.prevent="handleLogin">
          <input type="text" placeholder="用户名" v-model="userdata.username" required>
          <input type="password" placeholder="密码" show-password v-model="userdata.password" required>
          <button type="submit">登录</button>
        </form>
        <p>没有账号？<a href="#" @click="isLogin = false">注册</a></p>
      </div>
      <div v-else class="form-container">
        <h2>注册</h2>
        <form @submit.prevent="handleRegister">
          <input type="text" placeholder="用户名" v-model="userdata.username" required>
          <input type="password" placeholder="密码" v-model="userdata.password" required>
          <button type="submit">注册</button>
        </form>
        <p>已有账号？<a href="#" @click="isLogin = true">登录</a></p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
// import axios from 'axios';
export default {
  name: 'App',
  data() {
    return {
      isLogin: true,
      userdata:{
          username:"",
          password:""
      }
    };
  },
  
  methods: {
   
    handleLogin() {
     // 处理登录逻辑
      axios({
          url:'http://localhost:9004/user/login',
          method:'POST',   
          data:this.userdata 
             
        }).then(res=>{
            if(res.data.data){
               this.$bus.$emit('getusername',res.data.data.username)
                sessionStorage.setItem('username',res.data.data.username)
              sessionStorage.setItem('token',res.data.data.token)
             
              this.$router.push("/Manage/Myhome")
            }
            else{
            
              this.$message.error('用户名或密码错误');

            }
        })
      
  // this.request.post('/user/login',this.userdata)
  //       .then(response => {
  //         console.log(response.data);
  //       })
  //       .catch(error => {
  //         console.error(error);
  //       });

    },
    handleRegister() {
      // 处理注册逻辑
        axios({
          url:'http://localhost:9004/user/register',
          method:'POST',   
          data:this.userdata       
        }).then(res=>{
            if(res.data==true){
               this.$message.success('注册成功');

            }
            else{
            
              this.$message.error('注册失败');

            }
        })
      
      console.log('注册成功', this.username, this.password);
    }
  }
};
</script>

<style>
body, html {

 
}

#app {
     height: 100vh;

    padding: 70px 0;
  
    text-align: center;

  display: flex;
  justify-content: center;
  align-items: center;
 
  background: linear-gradient(to right, #97d1dd, #286270);
}

.login-register-container {
  width: 100%;
  max-width: 400px;
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}

.form-container {
  text-align: center;
}

h2 {
  margin-bottom: 20px;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #5cb85c;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #4cae4c;
}

a {
  color: #5cb85c;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
