<template>
  <div class="user">
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date">用户信息</i> </span>
       <el-row class="demo-avatar demo-basic">
        <el-col :span="12" style="padding: 10px 10px">
          <div class="demo-basic--circle" >
            <div class="block" >
              <el-avatar shape="square" :src="squareUrl"></el-avatar>
            </div>
          </div>
        </el-col>
      </el-row>
      </el-tab-pane>
      <el-tab-pane label="消息中心">消息中心</el-tab-pane>
      <el-tab-pane label="角色管理">角色管理</el-tab-pane>
      <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
    </el-tabs>

    </div>

</template>
<script>
export default {
  data() {
    return {
      squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
    }
  },
}
</script>
<style scoped>
.user{
  background-color: aliceblue;
}
</style>
