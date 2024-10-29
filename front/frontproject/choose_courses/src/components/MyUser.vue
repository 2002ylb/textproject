<template>
  <div>
      
      <div style="height: 50px; display: flex; align-items: center;">
     <el-input style="width:50%"  placeholder="请输入搜索内容" v-model="searchname"></el-input>
    <el-button type="primary" icon="el-icon-search" @click="searchbyname">搜索</el-button>
 
      
   
</div>

  <el-button type="primary" @click="dialogFormVisible = true">新增<i class="el-icon-circle-plus-outline"></i></el-button>
     <el-button type="danger" @click="batchdelete">批量删除</el-button>
     <el-button type="primary" @click="exportfile">导出<i class="el-icon-upload el-icon--right"></i></el-button>  

     

     
      <el-table :data="tableData" style="border: 1px solid #ddd;" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>   
        <el-table-column prop="id" label="id" width="120">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="120">
        </el-table-column>
        <el-table-column prop="age" label="年龄" width="140">
        </el-table-column>
         <el-table-column prop="number" label="电话" width="140">
        </el-table-column>
        <el-table-column prop="address" label="地址">
        </el-table-column>
        <el-table-column prop="operation" label="操作">
          <template slot-scope="scope">  
          <el-button type="warning" @click="updatestudent(scope.row)">编辑</el-button>
          <el-button type="danger" @click="deletebyid(scope.row)">删除 <i class="el-icon-remove-outline"></i></el-button>
          </template>
        </el-table-column>
        
      </el-table>
      <div class="block">
  
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagenum"
      :page-sizes="[10, 20]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
     
   

<el-dialog title="用户信息" :visible.sync="dialogFormVisible">
  <el-form :model="form" label-width="120px">
    <el-form-item label="名字" >
      <el-input v-model="form.name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="年龄" >
      <el-input v-model="form.age" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="电话" >
      <el-input v-model="form.number" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="地址" >
      <el-input v-model="form.address" autocomplete="off"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="changeinsert">确 定</el-button>
  </div>
</el-dialog>

  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'MyUser',
  data() {
      return {
          searchname:'',
          tableData:[],
          multipleSelection:{},
          pagenum:1,
          pagesize:10,
          total:0,
          dialogFormVisible:false,
          form:{
           
          }
      }
  },
  mounted() {
      this.load()
  },
  methods: {
    //文件上传成功的提示
    tipimprote(){
      this.$message.success("成功")
    },
    //文件导出
    exportfile() {
  window.open("http://localhost:9004/export")
},
    deletebyid(row){
      axios({
          url:"http://localhost:9004/student/deletebyid",
          method:'post',   
          data:row
        }).then(res=>{
          
          if(res.data==1){
            this.$message.success("成功")
            this.load()
          }else{
            this.$message.error("失败")
          }
        })
    },
    load(){
      // axios({
      //     url:"http://localhost:9004/student/page?pagenum="+this.pagenum+"&pagesize="+this.pagesize,
      //     method:'GET',   
             
      //   }).then(res=>{
      //     console.log(res.data);
      //       this.tableData=res.data.records
      //      this.total=res.data.total
      //   })

this.request.get('/student/page?pagenum=' + this.pagenum + '&pagesize=' + this.pagesize)
        .then(res => {
          console.log(res.data);
            this.tableData=res.data.records
           this.total=res.data.total
        })
        .catch(error => {
          console.error(error);
        });

  
    },
    handleSizeChange(pagesize){
      this.pagesize=pagesize
      this.load()
    },
    handleCurrentChange(pagenum){
      this.pagenum=pagenum
      this.load()
    },
    updatestudent(row){
      this.dialogFormVisible=true
      this.form=row
      
    },
    changeinsert(){
      // axios({
      //     url:'http://localhost:9004/student/insertstudent',
      //     method:'POST',   
      //     data:this.form       
      //   }).then(res=>{
      //     if(res.data==true){
      //     this.$message.success("成功")
      //     this.dialogFormVisible=false
      //     this.load()
      //   }else{
      //     this.$message.error("失败")
      //   }
      //   })


        this.request.post('/student/insertstudent',this.form)
        .then(res => {
          if(res.data==true){
          this.$message.success("成功")
          this.dialogFormVisible=false
          this.load()
        }else{
          this.$message.error("失败")
        }
        })
        .catch(error => {
          console.error(error);
        });



    },
      handleSelectionChange(val){
          this.multipleSelection=val
      },
      searchbyname(){
        
           axios({
            headers: {
      'Content-Type': 'text/plain'
    },
          url:'http://localhost:9004/student/searchbyname',
          method:'POST',   
          data:this.searchname       
        }).then(res=>{
            this.tableData=res.data
        })
      },
      batchdelete(){
        let ids=this.multipleSelection.map(v=>v.id)
         axios({
          url:'http://localhost:9004/student/batchdelete',
          method:'delete',   
          data:ids      
        }).then(res=>{
          if(res.data==true){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
        })
      }
  },
}
</script>

<style scoped>

</style>
