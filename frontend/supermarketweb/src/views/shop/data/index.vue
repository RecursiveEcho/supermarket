<script setup>
import { ref, reactive } from 'vue'
import { User, ShoppingCart, Money, TrendCharts } from '@element-plus/icons-vue'

const loading = ref(false)
const dateRange = ref([])

const statisticsData = reactive({
  todaySales: 125680.00,
  todayOrders: 456,
  todayCustomers: 328,
  todayProfit: 45230.00,
  monthSales: 3568900.00,
  monthOrders: 12568,
  monthCustomers: 8956,
  monthProfit: 1256800.00
})

const salesTrendData = ref([
  { date: '01-01', sales: 120000 },
  { date: '01-02', sales: 135000 },
  { date: '01-03', sales: 128000 },
  { date: '01-04', sales: 142000 },
  { date: '01-05', sales: 156000 },
  { date: '01-06', sales: 148000 },
  { date: '01-07', sales: 125680 }
])

const categoryData = ref([
  { name: '果蔬', value: 35000, percentage: 28 },
  { name: '零食', value: 28000, percentage: 22 },
  { name: '酒水', value: 25000, percentage: 20 },
  { name: '生鲜', value: 18000, percentage: 14 },
  { name: '其他', value: 19680, percentage: 16 }
])

const topProducts = ref([
  { rank: 1, name: '红富士苹果', sales: 15680, quantity: 520 },
  { rank: 2, name: '香蕉', sales: 12350, quantity: 680 },
  { rank: 3, name: '可口可乐', sales: 9800, quantity: 1200 },
  { rank: 4, name: '薯片', sales: 8500, quantity: 450 },
  { rank: 5, name: '牛奶', sales: 7200, quantity: 380 }
])
</script>

<template>
  <div class="data-container">
    <el-card shadow="never" class="filter-card">
      <el-form :inline="true">
        <el-form-item label="日期范围">
          <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" style="width: 240px" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-row :gutter="20" class="stat-row">
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%)">
            <el-icon :size="30"><ShoppingCart /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">¥{{ (statisticsData.todaySales / 10000).toFixed(2) }}万</div>
            <div class="stat-label">今日销售额</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%)">
            <el-icon :size="30"><Money /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">¥{{ (statisticsData.todayProfit / 10000).toFixed(2) }}万</div>
            <div class="stat-label">今日利润</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)">
            <el-icon :size="30"><User /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ statisticsData.todayCustomers }}</div>
            <div class="stat-label">今日客户数</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)">
            <el-icon :size="30"><TrendCharts /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ statisticsData.todayOrders }}</div>
            <div class="stat-label">今日订单数</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="16">
        <el-card shadow="never">
          <template #header>
            <span>销售趋势</span>
          </template>
          <div class="chart-container">
            <div v-for="item in salesTrendData" :key="item.date" class="bar-item">
              <div class="bar-label">{{ item.date }}</div>
              <div class="bar-wrapper">
                <div class="bar" :style="{ height: (item.sales / 2000) + 'px' }"></div>
              </div>
              <div class="bar-value">¥{{ (item.sales / 10000).toFixed(1) }}万</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never">
          <template #header>
            <span>品类销售占比</span>
          </template>
          <div class="category-list">
            <div v-for="item in categoryData" :key="item.name" class="category-item">
              <div class="category-info">
                <span class="category-name">{{ item.name }}</span>
                <span class="category-value">¥{{ (item.value / 1000).toFixed(0) }}元</span>
              </div>
              <el-progress :percentage="item.percentage" :stroke-width="8" />
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="24">
        <el-card shadow="never">
          <template #header>
            <span>热销商品 TOP5</span>
          </template>
          <el-table :data="topProducts" style="width: 100%" stripe>
            <el-table-column type="index" label="排名" width="80" align="center">
              <template #default="{ row }">
                <el-tag :type="row.rank <= 3 ? 'danger' : 'primary'" size="small" effect="dark">{{ row.rank }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="商品名称" min-width="200" />
            <el-table-column prop="sales" label="销售额" width="120" align="right">
              <template #default="{ row }">¥{{ row.sales.toFixed(0) }}</template>
            </el-table-column>
            <el-table-column prop="quantity" label="销量" width="100" align="right" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.data-container { padding: 20px; }
.filter-card { margin-bottom: 20px; }
.stat-row { display: flex; gap: 20px; }
.stat-card { display: flex; align-items: center; padding: 20px; height: 100px; }
.stat-icon { width: 60px; height: 60px; border-radius: 50%; display: flex; align-items: center; justify-content: center; color: white; margin-right: 20px; }
.stat-info { flex: 1; }
.stat-value { font-size: 24px; font-weight: bold; color: #303133; }
.stat-label { font-size: 14px; color: #909399; margin-top: 4px; }
.chart-container { display: flex; justify-content: space-around; align-items: flex-end; height: 250px; padding: 20px 0; }
.bar-item { display: flex; flex-direction: column; align-items: center; }
.bar-label { font-size: 12px; color: #909399; margin-bottom: 8px; }
.bar-wrapper { width: 40px; background: #e4e7ed; border-radius: 4px; overflow: hidden; }
.bar { width: 100%; background: linear-gradient(180deg, #667eea 0%, #764ba2 100%); transition: height 0.3s; }
.bar-value { font-size: 12px; color: #606266; margin-top: 8px; }
.category-list { padding: 10px 0; }
.category-item { margin-bottom: 20px; }
.category-info { display: flex; justify-content: space-between; margin-bottom: 8px; }
.category-name { font-weight: 500; }
.category-value { color: #f56c6c; font-weight: 600; }
</style>