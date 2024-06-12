<template>
  <div id="start">
    <div class="start">
      <div class="start-left">
        <el-button type="primary" icon="el-icon-search" circle class="custom-button">开始跑步</el-button>
      </div>
      <div class="start-right">地图</div>
    </div>
    <el-collapse v-model="activeNames" @change="handleChange" v-for="(item, index) in dataList.slice(0, 5)" :key="index">
      <el-collapse-item title="跑步记录" name="1" style="padding-left: 10px">
        <el-collapse-item :title="item.RecordID" name="3" style="padding-left: 10px">
          <ul>
            <li v-for="(innerItem, innerIndex) in item.Array" :key="innerIndex">
              {{ innerItem.UserID }}
              {{ innerItem.RunningDate }}
              {{ innerItem.Distance }}
            </li>
          </ul>
        </el-collapse-item>
      </el-collapse-item>

    </el-collapse>
  </div>
</template>

<script>
import { ref } from 'vue';

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
      handleChange
    };
  }
};
</script>

<style scoped>
#start {
  width: 100%;
  background-color: white;
}
.start {
  display: flex;
  justify-content: space-between;
}
.start-left {
  display: flex;
  align-items: center;
}
.start-right {
  display: flex;
  align-items: center;
}
.custom-button {
  width: 600px;
  height: 600px;
}
</style>
