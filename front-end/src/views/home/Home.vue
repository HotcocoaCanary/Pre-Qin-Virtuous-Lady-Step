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
import Image1 from "@/assets/img/slideshow/Image1.jpg";
import Image2 from "@/assets/img/slideshow/Image2.jpg";
import Image3 from "@/assets/img/slideshow/Image3.jpg";
import Image4 from "@/assets/img/slideshow/Image4.jpg";
import Image5 from "@/assets/img/slideshow/Image5.jpg";

export default {
  setup() {
    const value = ref(new Date());
    const images = ref([
      {src: Image1, alt: 'Image1'},
      {src: Image2, alt: 'Image2'},
      {src: Image3, alt: 'Image3'},
      {src: Image4, alt: 'Image4'},
      {src: Image5, alt: 'Image5'},
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
