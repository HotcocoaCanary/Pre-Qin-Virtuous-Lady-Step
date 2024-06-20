<script setup>
// 组件逻辑可以放置在这里，例如接收作者信息作为prop
</script>

<template>
  <footer class="footer">
    <div class="container">
      <p class="copyright">
        &copy; 2024 张文博, 李禄凯, 彭玺赫. All rights reserved.
      </p>
      <nav class="footer-links">
        <a href="#">关于我们</a>
        <a href="#">联系方式</a>
        <a href="#">隐私政策</a>
      </nav>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  width: 100%;
  height: 100%;
  color: #fff; /* 白色文字 */
  text-align: center; /* 如果需要居中显示 */
}

.container {
  margin: 0 auto; /* 水平居中 */
  padding: 0 20px; /* 内边距 */
}

.copyright {
  margin: 0 0 10px 0; /* 顶部外边距 */
  font-size: 0.9rem; /* 更小一点的字体大小 */
}

.footer-links a {
  color: #fff; /* 链接颜色 */
  text-decoration: none; /* 去除下划线 */
  margin: 0 10px; /* 链接之间的间距 */
  transition: color 0.3s; /* 鼠标悬停颜色变化的过渡效果 */
}

.footer-links a:hover {
  color: #ddd; /* 鼠标悬停时的颜色 */
}
</style>
