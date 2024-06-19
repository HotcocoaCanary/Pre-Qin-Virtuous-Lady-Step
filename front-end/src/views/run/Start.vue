<script setup>
import MapComponent from "@/components/run/Start/MapComponent.vue";
import { ref, onMounted } from "vue";

const coordinate = ref({
  point: [116.397428, 39.90923], // 默认坐标 (北京天安门)
});
const isPositionReady = ref(false); // 控制是否渲染地图组件

onMounted(() => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
        (position) => {
          // 获取到用户位置后更新坐标并允许渲染地图
          coordinate.value.point = [
            position.coords.longitude,
            position.coords.latitude,
          ];
          isPositionReady.value = true;
        },
        (error) => {
          console.error("获取位置失败:", error);
          // 如果获取位置失败，也可以选择允许渲染地图使用默认坐标
          isPositionReady.value = true;
        },
        {
          enableHighAccuracy: true,
          timeout: 5000,
          maximumAge: 0,
        }
    );
  } else {
    console.warn("浏览器不支持地理位置功能");
    // 如果浏览器不支持地理位置功能，允许渲染地图使用默认坐标
    isPositionReady.value = true;
  }
});
</script>

<template>
  <div id="map">
    <MapComponent v-if="isPositionReady" :center="coordinate.point"></MapComponent>
  </div>
</template>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}
</style>
