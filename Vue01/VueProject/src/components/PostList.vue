<template>
  <div class="p-4">
    <h1 class="text-2xl font-bold mb-4">貼文列表</h1>
    <div v-if="posts.length === 0">目前沒有貼文。</div>
    <div v-for="post in posts" :key="post.postId" class="border p-4 mb-2 rounded shadow">
      <p><strong>使用者 ID：</strong>{{ post.userId }}</p>
      <p><strong>內容：</strong>{{ post.content }}</p>
      <p><strong>時間：</strong>{{ formatDate(post.createdAt) }}</p>
      <img v-if="post.image" :src="post.image" alt="圖片" class="mt-2 max-w-xs" />
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'PostList',
  data() {
    return {
      posts: []
    }
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get('http://localhost:8080/api/posts')
        console.log('後端回傳資料：', response.data)
        this.posts = response.data
      } catch (error) {
        console.error('取得貼文失敗', error)
      }
    },
    formatDate(datetimeStr) {
      const date = new Date(datetimeStr)
      return date.toLocaleString()
    }
  },
  mounted() {
    this.fetchPosts()
  }
}
</script>

<style scoped>
img {
  border: 1px solid #ccc;
  padding: 4px;
  border-radius: 4px;
}
</style>