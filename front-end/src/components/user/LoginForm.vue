<template>
  <el-form ref="form" size="large" autocomplete="off" :model="loginData" :rules="rules">
    <el-form-item>
      <h1>登录</h1>
    </el-form-item>
    <el-form-item prop="username">
      <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="loginData.username"></el-input>
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

const emit = defineEmits(['login', 'toggle']);
const loginData = ref({
  username: '',
  password: ''
});

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ]
};

const handleLogin = () => {
  emit('login', loginData.value);
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
