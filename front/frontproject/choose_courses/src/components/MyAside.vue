<template>
  <div>
        <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
    background-color="rgb(48,65,86)"
    text-color=#fff
    active-text-color="#ffd04b"
  router
    >
    <div style="line-height:60px;height:60px;text-align:center;"><b style="color:white">mmmmm</b> </div>
     <el-menu-item index="/Manage/Myhome">
           <template slot="title"><i class="el-icon-message"></i>主页</template>
     </el-menu-item>
       
        
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>系统管理</template>
        <el-menu-item-group>
          
          <el-menu-item index="/Manage/MyUser">用户管理</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
        </el-menu-item-group>
       
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-setting"></i>导航三</template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="3-1">选项1</el-menu-item>
          <el-menu-item index="3-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="3-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="3-4">
          <template slot="title">选项4</template>
          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'MyAside',
  
}
</script>

<style scoped>

</style>
