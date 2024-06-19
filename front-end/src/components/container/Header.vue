<template>
  <el-page-header :icon="null">
    <template #content>
      <div class="flex items-center">
        <el-avatar :size="32" class="mr-3" :src="getUserAvatar"></el-avatar>
        <span class="text-large font-600 mr-3">{{ userInfo.name }}</span>
        <el-tag>{{ userInfo.password }}</el-tag>
      </div>
    </template>
  </el-page-header>
</template>

<script>
import { userInfoService } from "@/api/user.js";

export default {
  data() {
    return {
      avatar: {
        man: 'man.jpg',
        woman: 'woman.jpg'
      },
      userInfo: {
        name: 'Canary',
        gender: '男',
        password:'你好',
        age: '20',
        email: 'sksk@sksk',
        phoneNumber: '15602032933',
      }
    };
  },
  computed: {
    getUserAvatar() {
      return this.userInfo.gender === '男' ? this.avatar.man : this.avatar.woman;
    }
  },
  created() {
    this.fetchUserInfo();
  },
  methods: {
    async fetchUserInfo() {
      try {
        const response = await userInfoService();
        this.userInfo = response.data; // 假设响应的数据结构是 { name: '用户名', gender: '男' 或 '女', tag: '标签', ... }
      } catch (error) {
        console.error('Error fetching user info:', error);
      }
    }
  }
};
</script>

<style>
.flex.items-center {
  gap: 8px; /* 或者使用 margin-right 来添加边距 */
}
</style>
