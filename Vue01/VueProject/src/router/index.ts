import { createRouter, createWebHistory } from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import Login from '@/views/Login.vue';
import Register from '@/views/Register.vue'
import PostList from '@/components/PostList.vue';

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', name: 'Login', component: Login },
  { path: '/register', component: Register },
  { path: '/posts', name: 'PostList', component: PostList },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
  // routes: [
  //   {
  //     path: '/',
  //     name: 'home',
  //     component: HomeView,
  //   },
  //   {
  //     path: '/about',
  //     name: 'about',
  //     // route level code-splitting
  //     // this generates a separate chunk (About.[hash].js) for this route
  //     // which is lazy-loaded when the route is visited.
  //     component: () => import('../views/AboutView.vue'),
  //   },
  // ],
})

export default router
