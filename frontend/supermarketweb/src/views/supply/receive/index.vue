<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Search, Plus } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  ioNo: '',
  poNo: '',
  status: '',
  startDate: '',
  endDate: ''
})

const dialogVisible = ref(false)
const formData = reactive({
  id: null,
  ioNo: '',
  poNo: '',
  storeId: null,
  ioType: 'IN',
  bizType: 'PURCHASE_IN',
  totalQuantity: 0,
  totalAmount: 0,
  status: 'CREATED',
  remark: ''
})

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        ioNo: 'IN202401001',
        poNo: 'PO202401001',
        storeName: '邕城百货总店',
        ioType: 'IN',
        bizType: 'PURCHASE_IN',
        totalQuantity: 100,
        totalAmount: 15000.00,
        status: 'CONFIRMED',
        createTime: '2024-01-16 09:00:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.ioNo = ''
  searchForm.poNo = ''
  searchForm.status = ''
  searchForm.startDate = ''
  searchForm.endDate = ''
  loadData()
}

const handleAdd = () => {
  Object.assign(formData, {
    id: null,
    ioNo: '',
    poNo: '',
    storeId: null,
    ioType: 'IN',
    bizType: 'PURCHASE_IN',
    totalQuantity: 0,
    totalAmount: 0,
    status: 'CREATED',
    remark: ''
  })
  dialogVisible.value = true
}

const handleSubmit = () => {
  ElMessage.success('添加入库单成功')
  dialogVisible.value = false
  loadData()
}

const statusTagMap = {
  CREATED: { type: 'primary', text: '已创建' },
  CONFIRMED: { type: 'success', text: '已确认' },
  CANCELLED: { type: 'danger', text: '已取消' }
}

const ioTypeMap = {
  IN: '入库',
  OUT: '出库'
}
</script>

<template>
  <div class="receive-container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="入库单号">
          <el-input v-model="searchForm.ioNo" placeholder="请输入单号" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="采购单号">
          <el-input v-model="searchForm.poNo" placeholder="请输入采购单号" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 120px">
            <el-option label="已创建" value="CREATED" />
            <el-option label="已确认" value="CONFIRMED" />
            <el-option label="已取消" value="CANCELLED" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button :icon="Plus" @click="handleAdd">新增入库</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <el-table v-loading="loading" :data="tableData" style="width: 100%" border stripe>
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="ioNo" label="入库单号" width="140" />
        <el-table-column prop="poNo" label="关联采购单" width="140" />
        <el-table-column prop="storeName" label="门店" width="150" />
        <el-table-column label="类型" width="80" align="center">
          <template #default="{ row }">{{ ioTypeMap[row.ioType] }}</template>
        </el-table-column>
        <el-table-column prop="bizType" label="业务类型" width="120" />
        <el-table-column prop="totalQuantity" label="总数量" width="100" align="right" />
        <el-table-column prop="totalAmount" label="总金额" width="120" align="right">
          <template #default="{ row }">
            <span class="price-text">¥{{ row.totalAmount.toFixed(2) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="statusTagMap[row.status].type">{{ statusTagMap[row.status].text }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160" />
        <el-table-column label="操作" width="100" fixed="right" align="center">
          <template #default="{ row }">
            <el-button type="primary" size="small">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="新增采购入库" width="800px" :close-on-click-modal="false">
      <el-form :model="formData" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="入库单号">
              <el-input v-model="formData.ioNo" placeholder="请输入入库单号" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采购单号">
              <el-input v-model="formData.poNo" placeholder="请输入采购单号" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="入库门店">
              <el-select placeholder="请选择门店" style="width: 100%">
                <el-option label="邕城百货总店" :value="1" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="业务类型">
              <el-select v-model="formData.bizType" style="width: 100%">
                <el-option label="采购入库" value="PURCHASE_IN" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="总数量">
              <el-input-number v-model="formData.totalQuantity" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总金额">
              <el-input-number v-model="formData.totalAmount" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="formData.remark" type="textarea" :rows="3" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.receive-container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.price-text { color: #f56c6c; font-weight: 600; }
</style>