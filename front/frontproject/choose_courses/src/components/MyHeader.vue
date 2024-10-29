<template>
  <div>
       
      <el-dropdown>
        <i class="el-icon-user-solid" style="margin-right: 15px"></i>
        <span>{{username}}</span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="goToMyInformation">个人信息</el-dropdown-item>

          <el-dropdown-item>
              <router-link to="/">退出</router-link>
              
              </el-dropdown-item>
         
        </el-dropdown-menu>
      </el-dropdown>
  </div>
</template>

<script>
export default {
  name: 'MyHeader',
  data() {
    return {
      username:''
    }
  },
  methods: {
     goToMyInformation() {
      this.$router.push({ name: 'MyInformation' });
    },
  },
  
  mounted() {
    this.username=sessionStorage.getItem('username')
  },
 Create() {
    this.$bus.$on('getusername',data =>{
      console.log("拿到username"+data)
      this.username=data
    })
  },
  beforeDestroy() {
    // 移除事件监听
    this.$bus.$off('getusername');
  },
}
</script>

<style scoped>

</style>
