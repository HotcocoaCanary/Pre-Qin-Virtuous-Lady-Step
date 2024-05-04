<script lang="ts" setup>
import { ref } from 'vue'
import {
  Menu as IconMenu,
  Location,
  Setting,
  ArrowRightBold
} from '@element-plus/icons-vue'

const isCollapse = ref(true)

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>

<template>
  <div class="el-menu" :class="{'collapsed': isCollapse}">
    <div class="el-menu-collapse" @click="toggleCollapse">
      <el-icon class="el-menu-collapse-icon" :class="{'rotate-90': !isCollapse}">
        <ArrowRightBold />
      </el-icon>
      <span v-if="!isCollapse" class="el-menu-collapse-font">菜单栏</span>
    </div>
    <div class="el-menu-demo">
      <el-menu default-active="2" :collapse="isCollapse" @open="handleOpen" @close="handleClose">
        <el-menu-item index="1">
          <el-icon>
            <setting />
          </el-icon>
          <template #title>首页</template>
        </el-menu-item>
        <el-sub-menu index="2">
          <template #title>
            <el-icon>
              <location />
            </el-icon>
            <span>开始跑步</span>
          </template>
          <el-menu-item index="2-1">跑步打卡</el-menu-item>
          <el-menu-item index="2-2">跑步计划</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>
            <el-icon>
              <icon-menu />
            </el-icon>
            <span>个人中心</span>
          </template>
          <el-menu-item index="3-1">跑步报告</el-menu-item>
          <el-menu-item index="3-2">个人信息</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<style scoped>
.collapsed {
  width: 64px;
  transition: width 0.3s;
}
.el-menu {
  transition: width 0.3s;
  height: 100%;
  .el-menu-collapse {
    height: 8vh; /* Fixed height for the collapse section */
    display: flex;
    align-items: center;
    justify-content: space-between;
    cursor: pointer; /* Add cursor pointer for better UX */
    .el-menu-collapse-icon {
      margin-left: 20px;
      font-size: 24px; /* Adjust the font size as needed */
      color: #909399; /* Set the icon color */
      transform-origin: center center; /* Set the rotation center point */
      transition: transform 0.3s ease-in-out;
    }
    .el-menu-collapse-font {
      font-size: 36px;
      margin-right: 70px;
    }
    .rotate-90 {
      transform: rotate(90deg);
    }
  }
  .el-menu-demo {
    height: calc(100vh - 8vh); /* Calculate height for the demo section */
  }
}
</style>

