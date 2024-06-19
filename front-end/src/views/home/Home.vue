<template>
  <div class="home">
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="(image, index) in images" :key="index">
        <img :src="image.src" :alt="image.alt" style="width: 100%; height: 100%; object-fit: cover;">
      </el-carousel-item>
    </el-carousel>
    <el-calendar v-model="value"> </el-calendar>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const value = ref(new Date());
    const images = ref([
      {src: 'assets/img/kk/Image1.jpg', alt: 'Image 1'},
      {src: 'kk/Image2.jpg', alt: 'Image 2'},
      {src: 'kk/Image3.jpg', alt: 'Image 3'},
      // 添加更多图片对象
    ]);

    return {
      value,
      images
    };
  }
};
</script>

<style scoped>
.el-carousel__item {
  overflow: hidden; /* 确保图片在轮播项内正确显示 */
}

.el-carousel__item img {
  display: block; /* 使图片块级显示，以填充整个轮播项 */
}
</style>
