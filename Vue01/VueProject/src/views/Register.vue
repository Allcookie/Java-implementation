<template>
  <div class="register">
    <h2>註冊</h2>
    <input v-model="userName" placeholder="使用者名稱" />
    <input v-model="phoneNumber" placeholder="手機號碼" />
    <input v-model="password" type="password" placeholder="密碼" />
    <button @click="handleRegister">註冊</button>
    <p>{{ message }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const userName = ref('')
const phoneNumber = ref('')
const password = ref('')
const message = ref('')
const router = useRouter()

const handleRegister = async () => {
  try {
    await axios.post('http://localhost:8080/api/register', {
      userName: userName.value,
      phoneNumber: phoneNumber.value,
      password: password.value,
      coverImage: '', // 可選欄位
      biography: ''   // 可選欄位
    })
    message.value = '註冊成功，請登入'
    router.push('/login')
  } catch (error) {
    message.value = '註冊還在修改中！！很抱歉，帳號是0912345678，密碼是password123'
    console.error(error)
  }
}
</script>