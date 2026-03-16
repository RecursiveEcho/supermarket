<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  orderNo: '',
  memberName: '',
  status: '',
  startDate: '',
  endDate: ''
})

const dialogVisible = ref(false)

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        orderNo: 'PO202401001',
        memberName: '张三',
        goodsName: '小米手环',
        points: 5000,
        status: 'COMPLETED',
        createTime: '2024-01-15 10:30:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.orderNo = ''
  searchForm.memberName = ''
  searchForm.status = ''
  searchForm.startDate = ''
  searchForm.endDate = ''
  loadData()
}

const statusTagMap = {
  PENDING: { type: 'warning', text: '待发货' },
  SHIPPED: { type: 'primary', text: '已发货' },
  COMPLETED: { type: 'success', text: '已完成' },
  CANCELLED: { type: 'info', text: '已取消' }
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="订单号">
          <el-input v-model="searchForm.orderNo" placeholder="请输入订单号" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="会员姓名">
          <el-input v-model="searchForm.memberName" placeholder="请输入姓名" clearable style="width: 120px" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 120px">
            <el-option label="待发货" value="PENDING" />
            <el-option label="已发货" value="SHIPPED" />
            <el-option label="已完成" value="COMPLETED" />
            <el-option label="已取消" value="CANCELLED" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <el-table v-loading="loading" :data="tableData" style="width: 100%" border stripe>
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="orderNo" label="订单号" width="140" />
        <el-table-column prop="memberName" label="会员姓名" width="100" />
        <el-table-column prop="goodsName" label="商品名称" min-width="150" />
        <el-table-column prop="points" label="消耗积分" width="100" align="right">
          <template #default="{ row }">{{ row.points }}积分</template>
        </el-table-column>
        <el-table-column label="状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="statusTagMap[row.status].type">{{ statusTagMap[row.status].text }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="兑换时间" width="160" />
      </el-table>
    </el-card>
  </div>
</template>

<style scoped>
.container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
</style>