<template>
  <div class="login">
    <h2>登入</h2>
    <input v-model="phoneNumber" placeholder="電話號碼" />
    <input v-model="password" type="password" placeholder="密碼" />
    <button @click="handleLogin">登入</button>
    <p>{{ message }}</p>

    <p>還沒有帳號嗎？</p>
    <button @click="goToRegister">前往註冊</button>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const phoneNumber = ref('')
const password = ref('')
const message = ref('')
const router = useRouter()

const handleLogin = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/login', {
      phoneNumber: phoneNumber.value,
      password: password.value
    })
    localStorage.setItem('token', response.data)
    message.value = '登入成功'
    router.push('/posts')
  } catch (error) {
    message.value = '登入失敗，請檢查帳號與密碼'
    console.error(error)
  }
}

const goToRegister = () => {
  router.push('/register')
}
</script>