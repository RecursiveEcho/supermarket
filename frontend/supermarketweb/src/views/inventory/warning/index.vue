<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Search, Warning, RefreshRight } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  productCode: '',
  productName: '',
  storeId: '',
  warningType: ''
})

const storeOptions = ref([
  { id: 1, name: '邕城百货总店' },
  { id: 2, name: '邕城百货朝阳店' }
])

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        productCode: 'SPU002',
        productName: '香蕉',
        storeName: '邕城百货总店',
        currentQuantity: 25.8,
        safeQuantity: 50,
        maxQuantity: 200,
        unit: 'kg',
        warningType: 'LOW_STOCK',
        warningDate: '2024-01-16',
        remark: '库存低于安全库存'
      },
      {
        id: 2,
        productCode: 'SPU005',
        productName: '可口可乐',
        storeName: '邕城百货总店',
        currentQuantity: 350,
        safeQuantity: 100,
        maxQuantity: 300,
        unit: '瓶',
        warningType: 'OVER_STOCK',
        warningDate: '2024-01-16',
        remark: '库存超过最大库存'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.productCode = ''
  searchForm.productName = ''
  searchForm.storeId = ''
  searchForm.warningType = ''
  loadData()
}

const handleRefresh = () => {
  ElMessage.success('刷新预警数据成功')
  loadData()
}

const warningTypeMap = {
  LOW_STOCK: { type: 'warning', text: '库存不足' },
  OVER_STOCK: { type: 'danger', text: '库存积压' },
  EXPIRED_SOON: { type: 'info', text: '即将过期' }
}
</script>

<template>
  <div class="warning-container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="商品编码">
          <el-input v-model="searchForm.productCode" placeholder="请输入编码" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input v-model="searchForm.productName" placeholder="请输入名称" clearable style="width: 180px" />
        </el-form-item>
        <el-form-item label="门店">
          <el-select v-model="searchForm.storeId" placeholder="请选择门店" clearable style="width: 150px">
            <el-option v-for="item in storeOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="预警类型">
          <el-select v-model="searchForm.warningType" placeholder="请选择类型" clearable style="width: 120px">
            <el-option label="库存不足" value="LOW_STOCK" />
            <el-option label="库存积压" value="OVER_STOCK" />
            <el-option label="即将过期" value="EXPIRED_SOON" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button :icon="RefreshRight" @click="handleRefresh">刷新</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span><el-icon :size="18" color="#f56c6c"><Warning /></el-icon> 库存预警</span>
          <el-tag type="danger">预警商品：{{ tableData.length }}</el-tag>
        </div>
      </template>
      <el-table v-loading="loading" :data="tableData" style="width: 100%" border stripe highlight-current-row>
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="productCode" label="商品编码" width="120" />
        <el-table-column prop="productName" label="商品名称" min-width="180" show-overflow-tooltip />
        <el-table-column prop="storeName" label="门店" width="150" />
        <el-table-column prop="currentQuantity" label="当前库存" width="100" align="right" />
        <el-table-column prop="safeQuantity" label="安全库存" width="100" align="right" />
        <el-table-column prop="maxQuantity" label="最大库存" width="100" align="right" />
        <el-table-column prop="unit" label="单位" width="80" align="center" />
        <el-table-column label="预警类型" width="120" align="center">
          <template #default="{ row }">
            <el-tag :type="warningTypeMap[row.warningType].type">
              {{ warningTypeMap[row.warningType].text }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="warningDate" label="预警日期" width="120" />
        <el-table-column prop="remark" label="预警说明" min-width="180" show-overflow-tooltip />
        <el-table-column label="操作" width="120" fixed="right" align="center">
          <template #default="{ row }">
            <el-button type="primary" size="small">处理</el-button>
            <el-button type="success" size="small">忽略</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<style scoped>
.warning-container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.card-header { display: flex; justify-content: space-between; align-items: center; }
</style>