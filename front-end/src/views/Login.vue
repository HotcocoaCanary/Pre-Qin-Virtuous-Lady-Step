<script setup>
import {ref} from 'vue';
import {ElMessage} from 'element-plus';
import {userLoginService, userRegisterService} from '@/api/user.js';
import {useTokenStore} from '@/stores/token.js';
import {useRouter} from 'vue-router';
import RegisterForm from "@/components/user/RegisterForm.vue";
import LoginForm from "@/components/user/LoginForm.vue";

const router = useRouter();
const tokenStore = useTokenStore();
const isRegister = ref(false);
const registerData = ref({
  username: '',
  password: '',
  rePassword: ''
});

const clearRegisterData = () => {
  registerData.value = {
    username: '',
    password: '',
    rePassword: ''
  };
};

const register = async () => {
  let result = await userRegisterService(registerData.value);
  ElMessage.success(result.msg || '注册成功');
  clearRegisterData();
};

const login = async () => {
  let result = await userLoginService(registerData.value);
  ElMessage.success(result.msg || '登录成功');
  tokenStore.setToken(result.data);
  await router.push('/');
};
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <RegisterForm v-if="isRegister" @register="register" @toggle="isRegister = false" />
      <LoginForm v-else @login="login" @toggle="isRegister = true" />
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
