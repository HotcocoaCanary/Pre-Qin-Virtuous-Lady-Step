
<template>
<div class="home">
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in 6" :key="item">
        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  <el-calendar v-model="value"> </el-calendar>
</div>
</template>
<script>

import {ref} from "vue";

export default {
  setup() {
    const activeNames = ref(['1']);
    const dataList = ref([
      {
        RecordID: 1,
        Array: [{UserID: 1, RunningDate: 202400101, Distance: 10}]
      }
    ]);

    function handleChange(val) {
      console.log(val);
    }

    return {
      activeNames,
      dataList,
      handleChange,
      value: new Date()
    };
  }
};
</script>
<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

</style>
