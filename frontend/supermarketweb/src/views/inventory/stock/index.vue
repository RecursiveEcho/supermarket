<script setup>
import { ref, reactive, onMounted } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { pageStock } from '@/api/inventory'

const tableData = ref([])
const loading = ref(false)
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)

const searchForm = reactive({
  productCode: '',
  productName: '',
  categoryId: '',
  storeId: ''
})

const categoryOptions = ref([
  { id: 1, name: '果蔬' },
  { id: 2, name: '零食' },
  { id: 3, name: '酒水' },
  { id: 4, name: '生鲜' }
])

const storeOptions = ref([
  { id: 1, name: '邕城百货总店' },
  { id: 2, name: '邕城百货朝阳店' }
])

const loadData = async () => {
  loading.value = true
  try {
    const res = await pageStock({
      pageNum: pageNum.value,
      pageSize: pageSize.value,
      ...searchForm
    })
    tableData.value = res?.list || []
    total.value = Number(res?.total || 0)
  } finally {
    loading.value = false
  }
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.productCode = ''
  searchForm.productName = ''
  searchForm.categoryId = ''
  searchForm.storeId = ''
  loadData()
}

const handleSizeChange = (val) => {
  pageSize.value = val
  pageNum.value = 1
  loadData()
}

const handleCurrentChange = (val) => {
  pageNum.value = val
  loadData()
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="商品编码">
          <el-input v-model="searchForm.productCode" placeholder="请输入编码" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input v-model="searchForm.productName" placeholder="请输入名称" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="商品分类">
          <el-select v-model="searchForm.categoryId" placeholder="请选择分类" clearable style="width: 120px">
            <el-option v-for="item in categoryOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="门店">
          <el-select v-model="searchForm.storeId" placeholder="请选择门店" clearable style="width: 150px">
            <el-option v-for="item in storeOptions" :key="item.id" :label="item.name" :value="item.id" />
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
        <el-table-column prop="productCode" label="商品编码" width="120" />
        <el-table-column prop="productName" label="商品名称" min-width="180" show-overflow-tooltip />
        <el-table-column prop="categoryName" label="分类" width="100" />
        <el-table-column prop="storeName" label="门店" width="150" />
        <el-table-column prop="stockQuantity" label="库存数量" width="100" align="right">
          <template #default="{ row }">
            <span :class="{ 'text-warning': row.stockQuantity <= row.warningStock }">{{ row.stockQuantity }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="warningStock" label="预警库存" width="100" align="right" />
        <el-table-column prop="unit" label="单位" width="80" />
        <el-table-column prop="lastUpdateTime" label="最后更新时间" width="160" />
      </el-table>

      <div class="pagination">
        <el-pagination
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.text-warning { color: #E6A23C; font-weight: 600; }
.pagination { display: flex; justify-content: flex-end; padding-top: 16px; }
</style>