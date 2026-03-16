<script setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  ioNo: '',
  storeId: '',
  bizType: '',
  status: '',
  startDate: '',
  endDate: ''
})

const storeOptions = ref([
  { id: 1, name: '邕城百货总店' },
  { id: 2, name: '邕城百货朝阳店' }
])

const bizTypeOptions = [
  { value: 'SALES_OUT', label: '销售出库' },
  { value: 'RETURN_OUT', label: '退货出库' },
  { value: 'TRANSFER_OUT', label: '调拨出库' },
  { value: 'ADJUST_OUT', label: '盘亏出库' }
]

const dialogVisible = ref(false)
const dialogTitle = ref('新增出库单')
const formData = reactive({
  id: null,
  ioNo: '',
  storeId: null,
  ioType: 'OUT',
  bizType: 'SALES_OUT',
  bizNo: '',
  totalQuantity: 0,
  totalAmount: 0,
  status: 'CREATED',
  remark: ''
})

const formRules = {
  ioNo: [{ required: true, message: '请输入出库单号', trigger: 'blur' }],
  storeId: [{ required: true, message: '请选择门店', trigger: 'change' }]
}

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        ioNo: 'OUT202401001',
        storeName: '邕城百货总店',
        bizType: 'SALES_OUT',
        bizNo: 'SO202401001',
        totalQuantity: 200,
        totalAmount: 8000.00,
        status: 'CONFIRMED',
        createTime: '2024-01-16 14:30:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.ioNo = ''
  searchForm.storeId = ''
  searchForm.bizType = ''
  searchForm.status = ''
  searchForm.startDate = ''
  searchForm.endDate = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增出库单'
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑出库单'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除出库单"${row.ioNo}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增出库单' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const resetForm = () => {
  Object.assign(formData, {
    id: null,
    ioNo: '',
    storeId: null,
    ioType: 'OUT',
    bizType: 'SALES_OUT',
    bizNo: '',
    totalQuantity: 0,
    totalAmount: 0,
    status: 'CREATED',
    remark: ''
  })
}

const statusTagMap = {
  CREATED: { type: 'primary', text: '已创建' },
  CONFIRMED: { type: 'success', text: '已确认' },
  CANCELLED: { type: 'danger', text: '已取消' }
}

const getBizTypeLabel = (value) => {
  const item = bizTypeOptions.find(opt => opt.value === value)
  return item ? item.label : value
}
</script>

<template>
  <div class="out-container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="出库单号">
          <el-input v-model="searchForm.ioNo" placeholder="请输入单号" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="门店">
          <el-select v-model="searchForm.storeId" placeholder="请选择门店" clearable style="width: 150px">
            <el-option v-for="item in storeOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="业务类型">
          <el-select v-model="searchForm.bizType" placeholder="请选择类型" clearable style="width: 120px">
            <el-option v-for="item in bizTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
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
          <el-button :icon="Plus" @click="handleAdd">新增</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <el-table v-loading="loading" :data="tableData" style="width: 100%" border stripe>
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="ioNo" label="出库单号" width="140" />
        <el-table-column prop="storeName" label="门店" width="150" />
        <el-table-column label="业务类型" width="120" align="center">
          <template #default="{ row }">
            <el-tag>{{ getBizTypeLabel(row.bizType) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="bizNo" label="关联单号" width="140" show-overflow-tooltip />
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
            <el-form-item label="出库单号" prop="ioNo">
              <el-input v-model="formData.ioNo" placeholder="请输入出库单号" clearable :disabled="!!formData.id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出库门店" prop="storeId">
              <el-select v-model="formData.storeId" placeholder="请选择门店" style="width: 100%">
                <el-option v-for="item in storeOptions" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="业务类型" prop="bizType">
              <el-select v-model="formData.bizType" style="width: 100%">
                <el-option v-for="item in bizTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关联单号" prop="bizNo">
              <el-input v-model="formData.bizNo" placeholder="请输入关联业务单号" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="总数量" prop="totalQuantity">
              <el-input-number v-model="formData.totalQuantity" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总金额" prop="totalAmount">
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
.out-container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.price-text { color: #f56c6c; font-weight: 600; }
:deep(.el-dialog__body) { max-height: 70vh; overflow-y: auto; }
</style>
