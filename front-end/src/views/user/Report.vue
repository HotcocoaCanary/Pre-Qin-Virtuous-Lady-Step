<script setup>
import {ref, onMounted} from 'vue';
import {runReportService} from "@/api/run.js";

const report = ref([
  {
    runningDate: '',
    distance: '',
    duration: '',
    startPosition: '',
    endPosition: ''
  }
]);

const isLoading = ref(false);
const errorMessage = ref('');

onMounted(async () => {
  isLoading.value = true;
  try {
    const response = await runReportService();
    report.value = response.data; // 假设响应的数据结构是 [{...}, {...}, ...]
  } catch (error) {
    errorMessage.value = 'Error fetching run report: ' + error.message;
  } finally {
    isLoading.value = false;
  }
});
</script>

<template>
  <div>
    <el-table v-if="!isLoading && !errorMessage" :data="report" stripe style="width: 100%">
      <el-table-column prop="runningDate" label="日期" width="180"/>
      <el-table-column prop="distance" label="公里数" width="180"/>
      <el-table-column prop="duration" label="用时"/>
      <el-table-column prop="startPosition" label="起点" width="180"/>
      <el-table-column prop="endPosition" label="终点"/>
    </el-table>

    <div v-if="isLoading">Loading...</div>
    <div v-if="errorMessage" style="color: red">{{ errorMessage }}</div>
  </div>
</template>

<style scoped>

</style>
