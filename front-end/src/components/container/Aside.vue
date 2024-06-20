<template>
  <div class="el-menu-wrapper" :class="{'collapsed': isCollapse}">
    <div class="el-menu-collapse" @click="toggleCollapse">
      <el-icon class="el-menu-collapse-icon" :class="{'rotate-90': !isCollapse}">
        <ArrowRightBold />
      </el-icon>
      <span v-if="!isCollapse" class="el-menu-collapse-font"><img src="@/assets/img/logo-min.png" alt=""></span>
    </div>
    <div class="el-menu-demo">
      <el-menu default-active="2" :collapse="isCollapse" @open="handleOpen" @close="handleClose" router>
        <el-menu-item index="/home">
          <el-icon>
            <Setting />
          </el-icon>
          <template #title>首页</template>
        </el-menu-item>
        <el-sub-menu index="run">
          <template #title>
            <el-icon>
              <Location />
            </el-icon>
            <span>开始跑步</span>
          </template>
          <el-menu-item index="/run/start">跑步打卡</el-menu-item>
          <el-menu-item index="/run/plan">跑步计划</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="user">
          <template #title>
            <el-icon>
              <IconMenu />
            </el-icon>
            <span>个人中心</span>
          </template>
          <el-menu-item index="/user/report">跑步报告</el-menu-item>
          <el-menu-item index="/user/info">个人信息</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

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

<style scoped>
.el-menu-wrapper {
  height: 100%;
  transition: width 0.3s ease-in-out;
}

.el-menu-collapse {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  background-color: #BEAEA1;
  color: #fff;
  height: 8vh;
  cursor: pointer;
}

.el-menu-collapse-icon {
  font-size: 24px;
  transition: transform 0.3s ease-in-out;
}

.rotate-90 {
  transform: rotate(90deg);
}

.el-menu-demo {
  height: calc(100vh - 8vh);
  background-color: #f0f2f5;
}

.el-menu-item {
  padding: 0 20px;
  transition: background-color 0.3s;
}

.el-menu-item:hover,
.el-menu-item.is-active {
  background-color: #e6e6e6;
}

.el-sub-menu__title {
  padding: 0 20px;
}

.collapsed {
  width: 64px;
}
</style>
