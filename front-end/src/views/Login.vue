
<script setup>
import { ref } from 'vue';
import RegisterForm from "@/components/user/RegisterForm.vue";
import LoginForm from "@/components/user/LoginForm.vue";

const isRegister = ref(false);

const handleToggle = () => {
  isRegister.value = !isRegister.value;
};
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <RegisterForm v-if="isRegister" @toggle="handleToggle" />
      <LoginForm v-else @toggle="handleToggle" />
    </el-col>
  </el-row>
</template>

<style scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;
}

.bg {
  background: url('@/assets/img/logo.png') no-repeat 60% center / 240px auto,
  url('@/assets/img/login_bg.jpg') no-repeat center / cover;
  border-radius: 0 20px 20px 0;
}

.form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  user-select: none;
}
</style>
