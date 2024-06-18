<template>
  <el-page-header :icon="null">
    <template #content>
      <div class="flex items-center">
        <el-avatar :size="32" class="mr-3" :src="userInfo.avatar"></el-avatar>
        <span class="text-large font-600 mr-3"> {{ userInfo.name }} </span>
        <span class="text-sm mr-2" style="color: var(--el-text-color-regular)">
          {{ userInfo.subTitle }}
        </span>
        <el-tag type="info" size="small" class="mr-3">{{ userInfo.tag }}</el-tag>
      </div>
    </template>
    <template #extra>
      <div class="flex items-center">
        <el-button size="small" @click="print">Print</el-button>
        <el-button type="primary" size="small" class="ml-2" @click="edit">Edit</el-button>
      </div>
    </template>
  </el-page-header>
</template>

<script>

import {userInfoService} from "@/api/user.js";

export default {
  data() {
    return {
      userInfo: {
        Name: '',
        Age: '',
        Gender: ''
      }
    };
  },
  created() {
    this.fetchUserInfo();
  },
  methods: {
    async fetchUserInfo() {
      try {
        const response = await userInfoService();
        this.userInfo = response.data; // 假设响应的数据结构是 { avatar: 'url', name: 'Title', subTitle: 'Sub title', tag: 'Default' }
      } catch (error) {
        console.error('Error fetching login info:', error);
      }
    },
    print() {
      console.log('Print action');
      // Add your print logic here
    },
    edit() {
      console.log('Edit action');
      // Add your edit logic here
    }
  }
};
</script>

<style>
/* Add your custom styles here if needed */
</style>
