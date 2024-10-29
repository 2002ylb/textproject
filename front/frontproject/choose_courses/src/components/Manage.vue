<template>
  <div>
    <el-container style="height:100%;border:1px solid #eee">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: 100%" >
     <MyAside/>
  </el-aside>
   
  
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <MyHeader/>
     
    </el-header>

    
  
    <el-main class="main-container">
      <router-view/>
    </el-main>
   
  </el-container>
</el-container>



  </div>
</template>

<script>


import MyAside from './MyAside.vue'
import MyHeader from './MyHeader.vue'

export default {
  name: 'MyHome',
  components: {
   MyAside,
   MyHeader
  },
  
  
}
</script>

<style scoped>



</style>
