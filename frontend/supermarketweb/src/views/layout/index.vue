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
  Setting,
  Wallet,
  Box,
  Van,
  Ticket,
  Present,
  Plus,
  Minus,
  Warning,
  Calendar,
  RefreshLeft
} from '@element-plus/icons-vue'
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        邕城百货
      </el-header>
      <el-container>
        <el-aside width="220px">
          <el-menu router :default-openeds="['product']">
            <!-- 首页 -->
            <el-menu-item index="/index">
              <el-icon><Shop /></el-icon>
              <span>首页</span>
            </el-menu-item>

            <!-- 商品管理 -->
            <el-sub-menu index="product">
              <template #title>
                <el-icon><ShoppingCart /></el-icon>
                <span>商品管理</span>
              </template>
              <el-menu-item index="/product/fruit">
                <el-icon><Apple /></el-icon>
                <span>果蔬</span>
              </el-menu-item>
              <el-menu-item index="/product/snack">
                <el-icon><Food /></el-icon>
                <span>零食</span>
              </el-menu-item>
              <el-menu-item index="/product/drink">
                <el-icon><GobletSquareFull /></el-icon>
                <span>酒水</span>
              </el-menu-item>
              <el-menu-item index="/product/fresh">
                <el-icon><IceTea /></el-icon>
                <span>生鲜</span>
              </el-menu-item>
              <el-menu-item index="/product/appliance">
                <el-icon><Monitor /></el-icon>
                <span>家电</span>
              </el-menu-item>
              <el-menu-item index="/product/other">
                <el-icon><Folder /></el-icon>
                <span>其他</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 会员管理 -->
            <el-sub-menu index="member">
              <template #title>
                <el-icon><User /></el-icon>
                <span>会员管理</span>
              </template>
              <el-menu-item index="/member/list">
                <el-icon><User /></el-icon>
                <span>会员列表</span>
              </el-menu-item>
              <el-menu-item index="/member/level">
                <el-icon><UserFilled /></el-icon>
                <span>会员等级</span>
              </el-menu-item>
              <el-menu-item index="/member/statistics">
                <el-icon><DataBoard /></el-icon>
                <span>会员统计</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 积分商城 -->
            <el-sub-menu index="pointmall">
              <template #title>
                <el-icon><Present /></el-icon>
                <span>积分商城</span>
              </template>
              <el-menu-item index="/pointmall/goods">
                <el-icon><Present /></el-icon>
                <span>积分商品</span>
              </el-menu-item>
              <el-menu-item index="/pointmall/order">
                <el-icon><ShoppingCart /></el-icon>
                <span>兑换订单</span>
              </el-menu-item>
              <el-menu-item index="/pointmall/rule">
                <el-icon><Ticket /></el-icon>
                <span>积分规则</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 库存管理 -->
            <el-sub-menu index="inventory">
              <template #title>
                <el-icon><Box /></el-icon>
                <span>库存管理</span>
              </template>
              <el-menu-item index="/inventory/stock">
                <el-icon><Box /></el-icon>
                <span>库存查询</span>
              </el-menu-item>
              <el-menu-item index="/inventory/in">
                <el-icon><Plus /></el-icon>
                <span>入库管理</span>
              </el-menu-item>
              <el-menu-item index="/inventory/out">
                <el-icon><Minus /></el-icon>
                <span>出库管理</span>
              </el-menu-item>
              <el-menu-item index="/inventory/check">
                <el-icon><Document /></el-icon>
                <span>库存盘点</span>
              </el-menu-item>
              <el-menu-item index="/inventory/warning">
                <el-icon><Warning /></el-icon>
                <span>库存预警</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 供应链管理 -->
            <el-sub-menu index="supply">
              <template #title>
                <el-icon><Van /></el-icon>
                <span>供应链管理</span>
              </template>
              <el-menu-item index="/supply/supplier">
                <el-icon><User /></el-icon>
                <span>供应商管理</span>
              </el-menu-item>
              <el-menu-item index="/supply/purchase">
                <el-icon><Document /></el-icon>
                <span>采购订单</span>
              </el-menu-item>
              <el-menu-item index="/supply/receive">
                <el-icon><Box /></el-icon>
                <span>采购入库</span>
              </el-menu-item>
              <el-menu-item index="/supply/return">
                <el-icon><RefreshLeft /></el-icon>
                <span>采购退货</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 员工管理 -->
            <el-sub-menu index="clerk">
              <template #title>
                <el-icon><UserFilled /></el-icon>
                <span>员工管理</span>
              </template>
              <el-menu-item index="/clerk/info">
                <el-icon><User /></el-icon>
                <span>员工信息</span>
              </el-menu-item>
              <el-menu-item index="/clerk/schedule">
                <el-icon><Calendar /></el-icon>
                <span>员工排班</span>
              </el-menu-item>
              <el-menu-item index="/clerk/attendance">
                <el-icon><Document /></el-icon>
                <span>考勤记录</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 客户管理 -->
            <el-sub-menu index="customer">
              <template #title>
                <el-icon><User /></el-icon>
                <span>客户管理</span>
              </template>
              <el-menu-item index="/customer/list">
                <el-icon><User /></el-icon>
                <span>客户列表</span>
              </el-menu-item>
              <el-menu-item index="/customer/permission">
                <el-icon><Setting /></el-icon>
                <span>权限设置</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 店铺管理 -->
            <el-sub-menu index="shop">
              <template #title>
                <el-icon><Shop /></el-icon>
                <span>店铺管理</span>
              </template>
              <el-menu-item index="/shop/info">
                <el-icon><Shop /></el-icon>
                <span>店铺信息</span>
              </el-menu-item>
              <el-menu-item index="/shop/data">
                <el-icon><DataBoard /></el-icon>
                <span>经营数据</span>
              </el-menu-item>
              <el-menu-item index="/shop/setting">
                <el-icon><Setting /></el-icon>
                <span>店铺设置</span>
              </el-menu-item>
            </el-sub-menu>

            <!-- 系统设置 -->
            <el-sub-menu index="system">
              <template #title>
                <el-icon><Setting /></el-icon>
                <span>系统设置</span>
              </template>
              <el-menu-item index="/system/role">
                <el-icon><User /></el-icon>
                <span>角色管理</span>
              </el-menu-item>
              <el-menu-item index="/system/config">
                <el-icon><Setting /></el-icon>
                <span>系统配置</span>
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
  z-index: 100;
}

.el-aside {
  background: linear-gradient(180deg, #f8f9fa 0%, #e9ecef 100%);
  color: #2c3e50;
  height: calc(100vh - 60px);
  border-right: 1px solid #dee2e6;
  overflow-y: auto;
}

.el-aside::-webkit-scrollbar {
  width: 6px;
}

.el-aside::-webkit-scrollbar-thumb {
  background: #c5a028;
  border-radius: 3px;
}

.el-aside::-webkit-scrollbar-track {
  background: #f1f1f1;
}

.el-menu {
  border-right: none;
  background: transparent;
}

.el-sub-menu__title {
  color: #34495e;
  font-weight: 500;
  transition: all 0.3s;
  font-size: 14px;
}

.el-sub-menu__title:hover {
  background-color: rgba(212, 175, 55, 0.1);
  color: #D4AF37;
}

.el-menu-item {
  color: #5d6d7e;
  transition: all 0.3s;
  font-size: 14px;
}

.el-menu-item:hover {
  background-color: rgba(212, 175, 55, 0.15);
  color: #D4AF37;
}

.el-menu-item.is-active {
  background-color: rgba(212, 175, 55, 0.2);
  color: #D4AF37;
}

.el-menu-item .el-icon {
  margin-right: 8px;
  width: 16px;
  text-align: center;
}

.el-sub-menu__title .el-icon {
  margin-right: 8px;
  width: 16px;
  text-align: center;
}

.el-main {
  background: linear-gradient(135deg, #ffffff 0%, #f8f9fa 100%);
  padding: 20px;
  flex: 1;
  overflow-y: auto;
}
</style>