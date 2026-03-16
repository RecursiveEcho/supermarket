<script setup>
import {
  ShoppingCart,
  Apple,
  Food,
  GobletSquareFull,
  IceTea,
  Monitor,
  Folder,
  User,
  UserFilled,
  Shop,
  DataBoard,
  Document,
  Setting
} from '@element-plus/icons-vue'
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        邕城百货
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu router>
              <!--首页-->
              <el-menu-item index="/index">
                <el-icon></el-icon>首页
              </el-menu-item>
            <!-- 商品管理 -->
            <el-sub-menu index="product">
              <template #title>
                <el-icon><ShoppingCart /></el-icon>商品管理
              </template>
              <el-menu-item index="/product/fruit">
                <el-icon><Apple /></el-icon>果蔬
              </el-menu-item>
              <el-menu-item index="/product/snack">
                <el-icon><Food /></el-icon>零食
              </el-menu-item>
              <el-menu-item index="/product/drink">
                <el-icon><GobletSquareFull /></el-icon>酒水
              </el-menu-item>
              <el-menu-item index="/product/fresh">
                <el-icon><IceTea /></el-icon>生鲜
              </el-menu-item>
              <el-menu-item index="/product/appliance">
                <el-icon><Monitor /></el-icon>家电
              </el-menu-item>
              <el-menu-item index="/product/other">
                <el-icon><Folder /></el-icon>其他
              </el-menu-item>
            </el-sub-menu>

            <!-- 会员管理 -->
            <el-sub-menu index="member">
              <template #title>
                <el-icon><User /></el-icon>会员管理
              </template>
              <el-menu-item index="/member/list">
                <el-icon><User /></el-icon>会员列表
              </el-menu-item>
              <el-menu-item index="/member/level">
                <el-icon><UserFilled /></el-icon>会员等级
              </el-menu-item>
              <el-menu-item index="/member/statistics">
                <el-icon><DataBoard /></el-icon>会员统计
              </el-menu-item>
            </el-sub-menu>

            <!-- 员工管理 -->
            <el-sub-menu index="clerk">
              <template #title>
                <el-icon><UserFilled /></el-icon>员工管理
              </template>
              <el-menu-item index="/clerk/info">
                <el-icon><User /></el-icon>员工信息
              </el-menu-item>
              <el-menu-item index="/clerk/schedule">
                <el-icon><UserFilled /></el-icon>员工排班
              </el-menu-item>
              <el-menu-item index="/clerk/attendance">
                <el-icon><Document /></el-icon>考勤记录
              </el-menu-item>
            </el-sub-menu>

            <!-- 客户管理 -->
            <el-sub-menu index="customer">
              <template #title>
                <el-icon><User /></el-icon>客户管理
              </template>
              <el-menu-item index="/customer/list">
                <el-icon><User /></el-icon>客户列表
              </el-menu-item>
              <el-menu-item index="/customer/permission">
                <el-icon><Setting /></el-icon>权限设置
              </el-menu-item>
            </el-sub-menu>

            <!-- 店铺管理 -->
            <el-sub-menu index="shop">
              <template #title>
                <el-icon><Shop /></el-icon>店铺管理
              </template>
              <el-menu-item index="/shop/info">
                <el-icon><Shop /></el-icon>店铺信息
              </el-menu-item>
              <el-menu-item index="/shop/data">
                <el-icon><DataBoard /></el-icon>经营数据
              </el-menu-item>
              <el-menu-item index="/shop/setting">
                <el-icon><Setting /></el-icon>店铺设置
              </el-menu-item>
            </el-sub-menu>

            <!-- 系统设置 -->
            <el-sub-menu index="system">
              <template #title>
                <el-icon><Setting /></el-icon>系统设置
              </template>
              <el-menu-item index="/system/role">
                <el-icon><User /></el-icon>角色管理
              </el-menu-item>
              <el-menu-item index="/system/config">
                <el-icon><Setting /></el-icon>系统配置
              </el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.common-layout {
  height: 100vh;
  display: flex;
}

.el-header {
  background: linear-gradient(135deg, #D4AF37 0%, #C5A028 100%);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: bold;
  height: 60px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

.el-aside {
  background: linear-gradient(180deg, #f8f9fa 0%, #e9ecef 100%);
  color: #2c3e50;
  height: calc(100vh - 60px);
  border-right: 1px solid #dee2e6;
  overflow-y: auto;
}

.el-menu {
  border-right: none;
  background: transparent;
}

.el-sub-menu__title {
  color: #34495e;
  font-weight: 500;
  transition: all 0.3s;
}

.el-sub-menu__title:hover {
  background-color: rgba(212, 175, 55, 0.1);
  color: #D4AF37;
}

.el-menu-item {
  color: #5d6d7e;
  transition: all 0.3s;
}

.el-menu-item:hover {
  background-color: rgba(212, 175, 55, 0.15);
  color: #D4AF37;
}

.el-menu-item.is-active {
  background-color: rgba(212, 175, 55, 0.2);
  color: #D4AF37;
}

.el-main {
  background: linear-gradient(135deg, #ffffff 0%, #f8f9fa 100%);
  padding: 20px;
  flex: 1;
  overflow-y: auto;
}
</style>