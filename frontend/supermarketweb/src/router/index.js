import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: () => import('@/views/layout/index.vue'),
    redirect: '/index',
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/index/index.vue'),
        meta: { title: '首页', icon: 'HomeFilled' }
      },
      // 商品管理
      {
        path: 'product/fruit',
        name: 'Fruit',
        component: () => import('@/views/product/fruit/index.vue'),
        meta: { title: '果蔬', icon: 'ShoppingBag' }
      },
      {
        path: 'product/snack',
        name: 'Snack',
        component: () => import('@/views/product/snack/index.vue'),
        meta: { title: '零食', icon: 'ShoppingBag' }
      },
      {
        path: 'product/drink',
        name: 'Drink',
        component: () => import('@/views/product/drink/index.vue'),
        meta: { title: '酒水', icon: 'ShoppingBag' }
      },
      {
        path: 'product/fresh',
        name: 'Fresh',
        component: () => import('@/views/product/fresh/index.vue'),
        meta: { title: '生鲜', icon: 'ShoppingBag' }
      },
      {
        path: 'product/appliance',
        name: 'Appliance',
        component: () => import('@/views/product/appliance/index.vue'),
        meta: { title: '家电', icon: 'ShoppingBag' }
      },
      {
        path: 'product/other',
        name: 'Other',
        component: () => import('@/views/product/other/index.vue'),
        meta: { title: '其他', icon: 'ShoppingBag' }
      },
      // 会员管理
      {
        path: 'member/list',
        name: 'MemberList',
        component: () => import('@/views/member/list/index.vue'),
        meta: { title: '会员列表', icon: 'User' }
      },
      {
        path: 'member/level',
        name: 'MemberLevel',
        component: () => import('@/views/member/level/index.vue'),
        meta: { title: '会员等级', icon: 'Trophy' }
      },
      {
        path: 'member/statistics',
        name: 'MemberStatistics',
        component: () => import('@/views/member/statistics/index.vue'),
        meta: { title: '会员统计', icon: 'DataAnalysis' }
      },
      // 积分商城
      {
        path: 'pointmall/goods',
        name: 'PointMallGoods',
        component: () => import('@/views/pointmall/goods/index.vue'),
        meta: { title: '商品管理', icon: 'ShoppingCart' }
      },
      {
        path: 'pointmall/order',
        name: 'PointMallOrder',
        component: () => import('@/views/pointmall/order/index.vue'),
        meta: { title: '订单管理', icon: 'Document' }
      },
      {
        path: 'pointmall/rule',
        name: 'PointMallRule',
        component: () => import('@/views/pointmall/rule/index.vue'),
        meta: { title: '规则管理', icon: 'Setting' }
      },
      // 库存管理
      {
        path: 'inventory/stock',
        name: 'InventoryStock',
        component: () => import('@/views/inventory/stock/index.vue'),
        meta: { title: '库存查询', icon: 'Box' }
      },
      {
        path: 'inventory/in',
        name: 'InventoryIn',
        component: () => import('@/views/inventory/in/index.vue'),
        meta: { title: '入库管理', icon: 'Bottom' }
      },
      {
        path: 'inventory/out',
        name: 'InventoryOut',
        component: () => import('@/views/inventory/out/index.vue'),
        meta: { title: '出库管理', icon: 'Top' }
      },
      {
        path: 'inventory/check',
        name: 'InventoryCheck',
        component: () => import('@/views/inventory/check/index.vue'),
        meta: { title: '盘点管理', icon: 'Document' }
      },
      {
        path: 'inventory/warning',
        name: 'InventoryWarning',
        component: () => import('@/views/inventory/warning/index.vue'),
        meta: { title: '库存预警', icon: 'Warning' }
      },
      // 供应链管理
      {
        path: 'supply/supplier',
        name: 'SupplySupplier',
        component: () => import('@/views/supply/supplier/index.vue'),
        meta: { title: '供应商管理', icon: 'Van' }
      },
      {
        path: 'supply/purchase',
        name: 'SupplyPurchase',
        component: () => import('@/views/supply/purchase/index.vue'),
        meta: { title: '采购管理', icon: 'ShoppingCart' }
      },
      {
        path: 'supply/receive',
        name: 'SupplyReceive',
        component: () => import('@/views/supply/receive/index.vue'),
        meta: { title: '入库管理', icon: 'Download' }
      },
      {
        path: 'supply/return',
        name: 'SupplyReturn',
        component: () => import('@/views/supply/return/index.vue'),
        meta: { title: '退货管理', icon: 'RefreshLeft' }
      },
      // 员工管理
      {
        path: 'clerk/info',
        name: 'ClerkInfo',
        component: () => import('@/views/clerk/info/index.vue'),
        meta: { title: '员工信息', icon: 'User' }
      },
      {
        path: 'clerk/schedule',
        name: 'ClerkSchedule',
        component: () => import('@/views/clerk/schedule/index.vue'),
        meta: { title: '排班管理', icon: 'Calendar' }
      },
      {
        path: 'clerk/attendance',
        name: 'ClerkAttendance',
        component: () => import('@/views/clerk/attendance/index.vue'),
        meta: { title: '考勤管理', icon: 'Document' }
      },
      // 客户管理
      {
        path: 'customer/list',
        name: 'CustomerList',
        component: () => import('@/views/customer/list/index.vue'),
        meta: { title: '客户列表', icon: 'UserFilled' }
      },
      {
        path: 'customer/permission',
        name: 'CustomerPermission',
        component: () => import('@/views/customer/permission/index.vue'),
        meta: { title: '权限管理', icon: 'Lock' }
      },
      // 店铺管理
      {
        path: 'shop/info',
        name: 'ShopInfo',
        component: () => import('@/views/shop/info/index.vue'),
        meta: { title: '店铺信息', icon: 'Shop' }
      },
      {
        path: 'shop/data',
        name: 'ShopData',
        component: () => import('@/views/shop/data/index.vue'),
        meta: { title: '店铺数据', icon: 'DataBoard' }
      },
      {
        path: 'shop/setting',
        name: 'ShopSetting',
        component: () => import('@/views/shop/setting/index.vue'),
        meta: { title: '店铺设置', icon: 'Setting' }
      },
      // 系统设置
      {
        path: 'system/role',
        name: 'SystemRole',
        component: () => import('@/views/systen/role/index.vue'),
        meta: { title: '角色管理', icon: 'Avatar' }
      },
      {
        path: 'system/config',
        name: 'SystemConfig',
        component: () => import('@/views/systen/config/index.vue'),
        meta: { title: '系统配置', icon: 'Setting' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')

  if (to.path === '/login') {
    next()
  } else {
    if (!token) {
      next('/login')
    } else {
      next()
    }
  }
})
