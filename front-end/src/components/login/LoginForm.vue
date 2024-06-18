<template>
  <el-form ref="form" size="large" autocomplete="off" :model="loginData" :rules="rules">
    <el-form-item>
      <h1>登录</h1>
    </el-form-item>
    <el-form-item prop="username">
      <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="loginData.EmailOrPhoneNumber"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="loginData.password"></el-input>
    </el-form-item>
    <el-form-item class="flex">
      <div class="flex">
        <el-checkbox>记住我</el-checkbox>
        <el-link type="primary" :underline="false">忘记密码？</el-link>
      </div>
    </el-form-item>
    <el-form-item>
      <el-button class="button" type="primary" auto-insert-space @click="handleLogin">
        登录
      </el-button>
    </el-form-item>
    <el-form-item class="flex">
      <el-link type="info" :underline="false" @click="handleToggle">
        注册 →
      </el-link>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref } from 'vue';
import { User, Lock } from '@element-plus/icons-vue';
import { userLoginService } from '@/api/user.js';
import {useTokenStore} from '@/stores/token.js'
import {useRouter} from 'vue-router'
import { ElMessage } from 'element-plus'
const router = useRouter()
const tokenStore = useTokenStore();

const emit = defineEmits(['toggle']);
const loginData = ref({
  EmailOrPhoneNumber: '',
  password: ''
});

const rules = {
  EmailOrPhoneNumber: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ]
};

const handleLogin = async () => {
  let result =  await userLoginService(loginData.value);
  ElMessage.success(result.msg ? result.msg : '登录成功')
  //把得到的token存储到pinia中
  tokenStore.setToken(result.data)
  //跳转到首页 路由完成跳转
  await router.push('/')
};

const handleToggle = () => {
  emit('toggle');
};
</script>

<style scoped>
.flex {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.button {
  width: 100%;
}
</style>
