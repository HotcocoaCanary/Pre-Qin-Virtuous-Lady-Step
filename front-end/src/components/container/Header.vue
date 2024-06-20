<script>
import { userInfoService } from "@/api/user.js";
import man from "@/assets/img/avatar/man.jpg";
import woman from "@/assets/img/avatar/woman.jpg"

export default {
  data() {
    return {
      avatar: {
        man: man,
        woman: woman
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
<template>
  <el-page-header :icon="null" class="custom-page-header">
    <template #content>
      <div class="flex items-center">
        <el-avatar :size="40" :src="getUserAvatar" class="mr-3"></el-avatar>
        <span class="username">{{ userInfo.name }}</span>
        <el-tag class="tag-style">{{ userInfo.password }}</el-tag>
      </div>
    </template>
  </el-page-header>
</template>

<style scoped>
.custom-page-header {
  background-color: #BEAEA1; /* 页头背景色 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 页头阴影 */
  padding: 10px 20px; /* 页头内边距 */
}

.flex.items-center {
  display: flex;
  align-items: center; /* 垂直居中 */
  gap: 8px; /* 头像、用户名和标签之间的间距 */
}

.username {
  font-size: 1.2rem; /* 用户名字体大小 */
  font-weight: 600; /* 用户名字体权重 */
  line-height: 50px; /* 与头像高度一致 */
}

.tag-style {
  background-color: #f0f2f5; /* 标签背景色 */
  color: #515a6e; /* 标签文字颜色 */
  border: none; /* 移除标签边框 */
  font-size: 0.9rem; /* 标签字体大小 */
}
</style>
