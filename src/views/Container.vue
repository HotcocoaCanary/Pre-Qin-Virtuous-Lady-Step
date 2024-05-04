<script setup>
import Aside from "@/components/container/Aside.vue";
import Header from "@/components/container/Header.vue";
</script>

<template>
  <div class="common-layout">
    <el-container id="container">
      <el-aside id="aside">
        <Aside></Aside>
      </el-aside>
      <el-container>
        <el-header id="header">
          <Header></Header>
        </el-header>
        <el-main id="main">
          <router-view></router-view>
        </el-main>
        <el-footer id="footer">Footer</el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.common-layout {
  height: 100vh;
  margin: 0;
  padding: 0;
}
#container {
  height: 100%;
  width: 100%;
  display: flex;
}
#aside {
  width: auto; /* 设置宽度为auto */
  flex: 0 0 auto; /* 关闭flex grow和shrink，基于内容宽度 */
}
#header {
  background-color: darksalmon;
}
#main {
  background-color: darkgreen;
}
#footer {
  background-color: darkgoldenrod;
}
</style>
