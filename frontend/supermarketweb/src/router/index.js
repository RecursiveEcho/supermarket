import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', redirect: '/index' },
  { path: '/index', name: 'Index', component: () => import('@/views/index/index.vue') },
  { path: '/login', name: 'Login', component: () => import('@/views/login/index.vue') },
  
  // 商品管理
  { path: '/product/fruit', name: 'Fruit', component: () => import('@/views/product/fruit/index.vue') },
  { path: '/product/snack', name: 'Snack', component: () => import('@/views/product/snack/index.vue') },
  { path: '/product/drink', name: 'Drink', component: () => import('@/views/product/drink/index.vue') },
  { path: '/product/fresh', name: 'Fresh', component: () => import('@/views/product/fresh/index.vue') },
  { path: '/product/appliance', name: 'Appliance', component: () => import('@/views/product/appliance/index.vue') },
  { path: '/product/other', name: 'Other', component: () => import('@/views/product/other/index.vue') },
  
  // 会员管理
  { path: '/member/list', name: 'MemberList', component: () => import('@/views/member/list/index.vue') },
  { path: '/member/level', name: 'MemberLevel', component: () => import('@/views/member/level/index.vue') },
  { path: '/member/statistics', name: 'MemberStatistics', component: () => import('@/views/member/statistics/index.vue') },
  
  // 积分商城
  { path: '/pointmall/goods', name: 'PointMallGoods', component: () => import('@/views/pointmall/goods/index.vue') },
  { path: '/pointmall/order', name: 'PointMallOrder', component: () => import('@/views/pointmall/order/index.vue') },
  { path: '/pointmall/rule', name: 'PointMallRule', component: () => import('@/views/pointmall/rule/index.vue') },
  
  // 库存管理
  { path: '/inventory/stock', name: 'InventoryStock', component: () => import('@/views/inventory/stock/index.vue') },
  { path: '/inventory/in', name: 'InventoryIn', component: () => import('@/views/inventory/in/index.vue') },
  { path: '/inventory/out', name: 'InventoryOut', component: () => import('@/views/inventory/out/index.vue') },
  { path: '/inventory/check', name: 'InventoryCheck', component: () => import('@/views/inventory/check/index.vue') },
  { path: '/inventory/warning', name: 'InventoryWarning', component: () => import('@/views/inventory/warning/index.vue') },
  
  // 供应链管理
  { path: '/supply/supplier', name: 'SupplySupplier', component: () => import('@/views/supply/supplier/index.vue') },
  { path: '/supply/purchase', name: 'SupplyPurchase', component: () => import('@/views/supply/purchase/index.vue') },
  { path: '/supply/receive', name: 'SupplyReceive', component: () => import('@/views/supply/receive/index.vue') },
  { path: '/supply/return', name: 'SupplyReturn', component: () => import('@/views/supply/return/index.vue') },
  
  // 员工管理
  { path: '/clerk/info', name: 'ClerkInfo', component: () => import('@/views/clerk/info/index.vue') },
  { path: '/clerk/schedule', name: 'ClerkSchedule', component: () => import('@/views/clerk/schedule/index.vue') },
  { path: '/clerk/attendance', name: 'ClerkAttendance', component: () => import('@/views/clerk/attendance/index.vue') },
  
  // 客户管理
  { path: '/customer/list', name: 'CustomerList', component: () => import('@/views/customer/list/index.vue') },
  { path: '/customer/permission', name: 'CustomerPermission', component: () => import('@/views/customer/permission/index.vue') },
  
  // 店铺管理
  { path: '/shop/info', name: 'ShopInfo', component: () => import('@/views/shop/info/index.vue') },
  { path: '/shop/data', name: 'ShopData', component: () => import('@/views/shop/data/index.vue') },
  { path: '/shop/setting', name: 'ShopSetting', component: () => import('@/views/shop/setting/index.vue') },
  
  // 系统设置
  { path: '/system/role', name: 'SystemRole', component: () => import('@/views/systen/role/index.vue') },
  { path: '/system/config', name: 'SystemConfig', component: () => import('@/views/systen/config/index.vue') }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
