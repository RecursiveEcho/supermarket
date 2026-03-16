<script setup>
import { ref } from 'vue'
import { User, TrendCharts, DataBoard, Money } from '@element-plus/icons-vue'

const statisticsData = ref({
  totalMembers: 1268,
  todayNew: 23,
  activeMembers: 856,
  totalPoints: 2456789
})

const levelDistribution = ref([
  { level: '普通会员', count: 520, percentage: 41 },
  { level: '银卡会员', count: 380, percentage: 30 },
  { level: '金卡会员', count: 248, percentage: 19.5 },
  { level: '钻石会员', count: 120, percentage: 9.5 }
])

const trendData = ref([
  { month: '1 月', newMembers: 85, activeMembers: 520 },
  { month: '2 月', newMembers: 92, activeMembers: 548 },
  { month: '3 月', newMembers: 78, activeMembers: 562 },
  { month: '4 月', newMembers: 105, activeMembers: 612 },
  { month: '5 月', newMembers: 118, activeMembers: 685 },
  { month: '6 月', newMembers: 135, activeMembers: 742 }
])
</script>

<template>
  <div class="statistics-container">
    <!-- 统计卡片 -->
    <el-row :gutter="20" class="statistics-cards">
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%)">
              <el-icon :size="30"><User /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ statisticsData.totalMembers.toLocaleString() }}</div>
              <div class="stat-label">会员总数</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon" style="background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%)">
              <el-icon :size="30"><TrendCharts /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">+{{ statisticsData.todayNew }}</div>
              <div class="stat-label">今日新增</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon" style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)">
              <el-icon :size="30"><DataBoard /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ statisticsData.activeMembers.toLocaleString() }}</div>
              <div class="stat-label">活跃会员</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-icon" style="background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)">
              <el-icon :size="30"><Money /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ (statisticsData.totalPoints / 10000).toFixed(2) }}万</div>
              <div class="stat-label">积分池总量</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 数据表格和趋势图 -->
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>会员等级分布</span>
            </div>
          </template>
          <el-table :data="levelDistribution" style="width: 100%" :show-header="true">
            <el-table-column prop="level" label="等级" width="120" />
            <el-table-column prop="count" label="人数" width="100" align="right" />
            <el-table-column prop="percentage" label="占比" align="right">
              <template #default="{ row }">
                <el-progress :percentage="row.percentage" :stroke-width="18" />
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>会员增长趋势</span>
            </div>
          </template>
          <div class="trend-placeholder">
            <div v-for="item in trendData" :key="item.month" class="trend-item">
              <div class="trend-month">{{ item.month }}</div>
              <div class="trend-bar">
                <div class="bar-new" :style="{ height: (item.newMembers / 2) + 'px', width: '25px' }" title="新增会员"></div>
                <div class="bar-active" :style="{ height: (item.activeMembers / 10) + 'px', width: '25px' }" title="活跃会员"></div>
              </div>
            </div>
          </div>
          <div class="trend-legend">
            <div class="legend-item">
              <span class="legend-dot" style="background: linear-gradient(180deg, #667eea 0%, #764ba2 100%)"></span>
              <span>新增会员</span>
            </div>
            <div class="legend-item">
              <span class="legend-dot" style="background: linear-gradient(180deg, #f093fb 0%, #f5576c 100%)"></span>
              <span>活跃会员</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.statistics-container {
  padding: 20px;
  min-height: calc(100vh - 100px);
}

.statistics-cards {
  display: flex;
  flex-wrap: wrap;
}

.stat-card {
  margin-bottom: 20px;
  transition: transform 0.3s;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-content {
  display: flex;
  align-items: center;
  gap: 20px;
}

.stat-icon {
  width: 70px;
  height: 70px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 28px;
  font-weight: bold;
  color: #2c3e50;
  margin-bottom: 5px;
}

.stat-label {
  font-size: 14px;
  color: #7f8c8d;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: 500;
  font-size: 16px;
}

.trend-placeholder {
  display: flex;
  justify-content: space-around;
  align-items: flex-end;
  height: 220px;
  padding: 20px 10px;
  background: linear-gradient(180deg, #f8f9fa 0%, #e9ecef 100%);
  border-radius: 8px;
  margin-bottom: 15px;
}

.trend-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.trend-month {
  font-size: 14px;
  color: #5d6d7e;
  font-weight: 500;
}

.trend-bar {
  display: flex;
  gap: 6px;
  align-items: flex-end;
  height: 150px;
}

.bar-new, .bar-active {
  border-radius: 4px 4px 0 0;
  transition: all 0.3s;
  min-height: 10px;
}

.bar-new:hover, .bar-active:hover {
  opacity: 0.8;
}

.bar-new {
  background: linear-gradient(180deg, #667eea 0%, #764ba2 100%);
}

.bar-active {
  background: linear-gradient(180deg, #f093fb 0%, #f5576c 100%);
}

.trend-legend {
  display: flex;
  justify-content: center;
  gap: 30px;
  padding: 10px;
}

.legend-item {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 13px;
  color: #5d6d7e;
}

.legend-dot {
  width: 16px;
  height: 16px;
  border-radius: 3px;
}
</style>