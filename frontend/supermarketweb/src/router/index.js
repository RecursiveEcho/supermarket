import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', redirect: '/index' },
  { path: '/index', name: 'Index', component: () => import('@/views/index/index.vue') },
  { path: '/login', name: 'Login', component: () => import('@/views/login/index.vue') },
  { path: '/product/fruit', name: 'Fruit', component: () => import('@/views/product/fruit/index.vue') },
  { path: '/product/snack', name: 'Snack', component: () => import('@/views/product/snack/index.vue') },
  { path: '/product/drink', name: 'Drink', component: () => import('@/views/product/drink/index.vue') },
  { path: '/product/fresh', name: 'Fresh', component: () => import('@/views/product/fresh/index.vue') },
  { path: '/product/appliance', name: 'Appliance', component: () => import('@/views/product/appliance/index.vue') },
  { path: '/product/other', name: 'Other', component: () => import('@/views/product/other/index.vue') },
  { path: '/member/list', name: 'MemberList', component: () => import('@/views/member/list/index.vue') },
  { path: '/member/level', name: 'MemberLevel', component: () => import('@/views/member/level/index.vue') },
  { path: '/member/statistics', name: 'MemberStatistics', component: () => import('@/views/member/statistics/index.vue') },
  { path: '/clerk/info', name: 'ClerkInfo', component: () => import('@/views/clerk/info/index.vue') },
  { path: '/clerk/schedule', name: 'ClerkSchedule', component: () => import('@/views/clerk/schedule/index.vue') },
  { path: '/clerk/attendance', name: 'ClerkAttendance', component: () => import('@/views/clerk/attendance/index.vue') },
  { path: '/customer/list', name: 'CustomerList', component: () => import('@/views/customer/list/index.vue') },
  { path: '/customer/permission', name: 'CustomerPermission', component: () => import('@/views/customer/permission/index.vue') },
  { path: '/shop/info', name: 'ShopInfo', component: () => import('@/views/shop/info/index.vue') },
  { path: '/shop/data', name: 'ShopData', component: () => import('@/views/shop/data/index.vue') },
  { path: '/shop/setting', name: 'ShopSetting', component: () => import('@/views/shop/setting/index.vue') },
  { path: '/system/role', name: 'SystemRole', component: () => import('@/views/systen/role/index.vue') },
  { path: '/system/config', name: 'SystemConfig', component: () => import('@/views/systen/config/index.vue') }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
