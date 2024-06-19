<script setup>
import MapComponent from "@/components/run/Start/MapComponent.vue";
import { ref, onMounted, watch } from "vue";

const coordinate = ref({
  my: [116.397428, 39.90923], // 默认坐标 (北京天安门)
  goal: [116.397428, 39.90923],
});
const isPositionReady = ref(false); // 控制是否渲染地图组件

onMounted(async () => {
  if (navigator.geolocation) {
    try {
      const position = await new Promise((resolve, reject) => {
        navigator.geolocation.getCurrentPosition(resolve, reject, {
          enableHighAccuracy: true,
          timeout: 5000,
          maximumAge: 0,
        });
      });
      // 随机选择一个方向（0°到360°）
      const randomDirection = Math.random() * 360;
      // 根据随机方向和距离计算经纬度的偏移
      const latitudeOffset = 2 / 111 * Math.sin(randomDirection * Math.PI / 180);
      const longitudeOffset = 2 / 111 * Math.cos(randomDirection * Math.PI / 180);
      // 获取到用户位置后更新坐标
      coordinate.value.my = [position.coords.longitude, position.coords.latitude];
      coordinate.value.goal = [
        position.coords.longitude + longitudeOffset,
        position.coords.latitude + latitudeOffset,
      ];
      isPositionReady.value = true;
    } catch (error) {
      console.error("获取位置失败:", error);
      // 如果获取位置失败，也可以选择允许渲染地图使用默认坐标
      isPositionReady.value = true;
    }
  } else {
    console.warn("浏览器不支持地理位置功能");
    // 如果浏览器不支持地理位置功能，允许渲染地图使用默认坐标
    isPositionReady.value = true;
  }
});

// 监听coordinate的变化，更新地图组件
watch(coordinate, () => {
  if (coordinate.value.my && coordinate.value.goal) {
    isPositionReady.value = true;
  }
}, {
  deep: true
});
</script>

<template>
  <div id="map">
    <MapComponent v-if="isPositionReady" :location="coordinate"></MapComponent>
  </div>
</template>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}
</style>
