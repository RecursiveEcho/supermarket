<script setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  poNo: '',
  supplierId: '',
  status: '',
  startDate: '',
  endDate: ''
})

const supplierOptions = ref([
  { id: 1, name: '广西商贸集团有限公司' },
  { id: 2, name: '南宁食品批发公司' }
])

const dialogVisible = ref(false)
const dialogTitle = ref('新增采购订单')
const formData = reactive({
  id: null,
  poNo: '',
  supplierId: null,
  orderDate: '',
  totalAmount: 0,
  status: 'CREATED',
  remark: ''
})

const formRules = {
  poNo: [{ required: true, message: '请输入采购单号', trigger: 'blur' }],
  supplierId: [{ required: true, message: '请选择供应商', trigger: 'change' }]
}

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        poNo: 'PO202401001',
        supplierName: '广西商贸集团有限公司',
        orderDate: '2024-01-15',
        totalAmount: 15000.00,
        status: 'CREATED',
        createTime: '2024-01-15 10:30:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.poNo = ''
  searchForm.supplierId = ''
  searchForm.status = ''
  searchForm.startDate = ''
  searchForm.endDate = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增采购订单'
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑采购订单'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除采购单"${row.poNo}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增采购订单' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const resetForm = () => {
  Object.assign(formData, {
    id: null,
    poNo: '',
    supplierId: null,
    orderDate: '',
    totalAmount: 0,
    status: 'CREATED',
    remark: ''
  })
}

const statusTagMap = {
  CREATED: { type: 'primary', text: '已创建' },
  CONFIRMED: { type: 'success', text: '已确认' },
  RECEIVED: { type: 'warning', text: '已收货' },
  CANCELLED: { type: 'danger', text: '已取消' }
}
</script>

<template>
  <div class="purchase-container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="采购单号">
          <el-input v-model="searchForm.poNo" placeholder="请输入单号" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="供应商">
          <el-select v-model="searchForm.supplierId" placeholder="请选择供应商" clearable style="width: 180px">
            <el-option v-for="item in supplierOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 120px">
            <el-option label="已创建" value="CREATED" />
            <el-option label="已确认" value="CONFIRMED" />
            <el-option label="已收货" value="RECEIVED" />
            <el-option label="已取消" value="CANCELLED" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button :icon="Plus" @click="handleAdd">新增</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <el-table v-loading="loading" :data="tableData" style="width: 100%" border stripe>
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="poNo" label="采购单号" width="140" />
        <el-table-column prop="supplierName" label="供应商" min-width="180" show-overflow-tooltip />
        <el-table-column prop="orderDate" label="订单日期" width="120" />
        <el-table-column prop="totalAmount" label="采购金额" width="120" align="right">
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
        <el-table-column label="操作" width="150" fixed="right" align="center">
          <template #default="{ row }">
            <el-button type="primary" size="small" :icon="Edit" @click="handleEdit(-1, row)">编辑</el-button>
            <el-button type="danger" size="small" :icon="Delete" @click="handleDelete(-1, row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="800px" :close-on-click-modal="false" @closed="resetForm">
      <el-form ref="formRef" :model="formData" :rules="formRules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="采购单号" prop="poNo">
              <el-input v-model="formData.poNo" placeholder="请输入采购单号" clearable :disabled="!!formData.id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="供应商" prop="supplierId">
              <el-select v-model="formData.supplierId" placeholder="请选择供应商" style="width: 100%">
                <el-option v-for="item in supplierOptions" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="订单日期" prop="orderDate">
              <el-date-picker v-model="formData.orderDate" type="date" placeholder="选择日期" style="width: 100%" format="YYYY-MM-DD" value-format="YYYY-MM-DD" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采购金额" prop="totalAmount">
              <el-input-number v-model="formData.totalAmount" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="formData.status" style="width: 100%">
                <el-option label="已创建" value="CREATED" />
                <el-option label="已确认" value="CONFIRMED" />
                <el-option label="已收货" value="RECEIVED" />
                <el-option label="已取消" value="CANCELLED" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="formData.remark" type="textarea" :rows="3" placeholder="请输入备注" maxlength="512" show-word-limit />
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
.purchase-container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.price-text { color: #f56c6c; font-weight: 600; }
:deep(.el-dialog__body) { max-height: 70vh; overflow-y: auto; }
</style>