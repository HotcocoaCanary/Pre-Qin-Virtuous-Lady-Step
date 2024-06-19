<template>
  <div class="user">
    <el-tabs type="border-card">
      <el-tab-pane label="用户信息">
        <span slot="label"><i class="el-icon-date"></i>{{ userInfo.name }}</span>
        <el-row class="demo-avatar demo-basic">
          <el-col :span="12" style="padding: 10px 10px">
            <div class="demo-basic--circle">
              <div class="block">
                <el-avatar shape="square" :src="squareUrl"></el-avatar>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-descriptions class="margin-top" :column="3" border>
          <el-descriptions-item label="性别">{{ userInfo.gender }}</el-descriptions-item>
          <el-descriptions-item label="年龄">{{ userInfo.age }}</el-descriptions-item>
          <el-descriptions-item label="手机号">{{ userInfo.phoneNumber }}</el-descriptions-item>
          <el-descriptions-item label="邮箱">{{ userInfo.email }}</el-descriptions-item>
          <el-descriptions-item label="备注">
            <el-tag size="small">学校</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="联系地址">内蒙古自治区呼和浩特市赛罕区内蒙古农业大学</el-descriptions-item>
        </el-descriptions>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { ElTabs, ElTabPane, ElRow, ElCol, ElAvatar, ElDescriptions, ElDescriptionsItem, ElTag } from 'element-plus';
import { userInfoService } from "@/api/user.js";

export default {
  components: {
    ElTabs, ElTabPane, ElRow, ElCol, ElAvatar, ElDescriptions, ElDescriptionsItem, ElTag
  },
  setup() {
    const squareUrl = "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png";
    const userInfo = ref({
      name: '',
      gender: '',
      password: '',
      age: '',
      email: '',
      phoneNumber: '',
    });

    onMounted(async () => {
      try {
        const response = await userInfoService();
        userInfo.value = response.data; // 假设响应的数据结构是 { name: '用户名', gender: '男' 或 '女', ... }
      } catch (error) {
        console.error('Error fetching user info:', error);
      }
    });

    return {
      squareUrl,
      userInfo,
    };
  },
};
</script>
