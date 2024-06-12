<template>
  <el-form ref="form" size="large" autocomplete="off" :model="registerData" :rules="rules">
    <el-form-item>
      <h1>注册</h1>
    </el-form-item>
    <el-form-item prop="username">
      <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
    </el-form-item>
    <el-form-item prop="rePassword">
      <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码" v-model="registerData.rePassword"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button class="button" type="primary" auto-insert-space @click="handleRegister">
        注册
      </el-button>
    </el-form-item>
    <el-form-item class="flex">
      <el-link type="info" :underline="false" @click="handleToggle">
        已有账号？登录 →
      </el-link>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref } from 'vue';
import { User, Lock } from '@element-plus/icons-vue';

const emit = defineEmits(['register', 'toggle']);
const registerData = ref({
  username: '',
  password: '',
  rePassword: ''
});

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  rePassword: [
    { validator: (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次确认密码'));
        } else if (value !== registerData.value.password) {
          callback(new Error('请确保两次输入的密码一样'));
        } else {
          callback();
        }
      }, trigger: 'blur'
    }
  ]
};

const handleRegister = () => {
  emit('register', registerData.value);
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
