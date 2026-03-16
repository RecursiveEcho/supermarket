<script setup>
import { ref, reactive } from 'vue'
import { Search, Download } from '@element-plus/icons-vue'

const searchForm = reactive({
  employeeName: '',
  storeId: '',
  startDate: '',
  endDate: ''
})

const storeOptions = ref([
  { id: 1, name: '邕城百货总店' },
  { id: 2, name: '邕城百货朝阳店' }
])

const tableData = ref([
  { id: 1, employeeName: '张三', date: '2024-03-01', clockIn: '07:55', clockOut: '16:05', status: 'normal', workHours: 8, storeName: '邕城百货总店' },
  { id: 2, employeeName: '李四', date: '2024-03-01', clockIn: '08:10', clockOut: '16:15', status: 'late', workHours: 8, storeName: '邕城百货总店' },
  { id: 3, employeeName: '王五', date: '2024-03-01', clockIn: '15:50', clockOut: '23:55', status: 'normal', workHours: 8, storeName: '邕城百货朝阳店' }
])

const statusMap = {
  normal: { type: 'success', text: '正常' },
  late: { type: 'warning', text: '迟到' },
  early: { type: 'warning', text: '早退' },
  absent: { type: 'danger', text: '缺勤' },
  leave: { type: 'info', text: '请假' }
}

const handleSearch = () => {}
const handleExport = () => {}
</script>

<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm">
        <el-form-item label="员工姓名">
          <el-input v-model="searchForm.employeeName" placeholder="请输入姓名" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="门店">
          <el-select v-model="searchForm.storeId" placeholder="请选择" clearable style="width: 180px">
            <el-option v-for="item in storeOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="日期范围">
          <el-date-picker
            v-model="searchForm.startDate"
            type="date"
            placeholder="开始日期"
            style="width: 150px"
          />
          <span style="margin: 0 10px">-</span>
          <el-date-picker
            v-model="searchForm.endDate"
            type="date"
            placeholder="结束日期"
            style="width: 150px"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button :icon="Download" @click="handleExport">导出</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <el-table :data="tableData" style="width: 100%" border stripe>
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="employeeName" label="员工姓名" width="100" />
        <el-table-column prop="date" label="考勤日期" width="120" />
        <el-table-column prop="clockIn" label="上班时间" width="100" />
        <el-table-column prop="clockOut" label="下班时间" width="100" />
        <el-table-column label="状态" width="80" align="center">
          <template #default="{ row }">
            <el-tag :type="statusMap[row.status].type">{{ statusMap[row.status].text }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="workHours" label="工作时长 (小时)" width="120" align="right" />
        <el-table-column prop="storeName" label="门店" width="180" />
        <el-table-column prop="remark" label="备注" min-width="150" show-overflow-tooltip />
      </el-table>
    </el-card>
  </div>
</template>

<style scoped>
.container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.table-card { min-height: 500px; }
</style>