<script setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  checkNo: '',
  storeId: '',
  status: '',
  startDate: '',
  endDate: ''
})

const storeOptions = ref([
  { id: 1, name: '邕城百货总店' },
  { id: 2, name: '邕城百货朝阳店' }
])

const dialogVisible = ref(false)
const dialogTitle = ref('新增盘点单')
const formData = reactive({
  id: null,
  checkNo: '',
  storeId: null,
  checkType: 'REGULAR',
  totalQuantity: 0,
  diffQuantity: 0,
  diffAmount: 0,
  status: 'CREATED',
  remark: ''
})

const formRules = {
  checkNo: [{ required: true, message: '请输入盘点单号', trigger: 'blur' }],
  storeId: [{ required: true, message: '请选择门店', trigger: 'change' }]
}

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        checkNo: 'CK202401001',
        storeName: '邕城百货总店',
        checkType: 'REGULAR',
        checkDate: '2024-01-15',
        totalQuantity: 1000,
        diffQuantity: 5,
        diffAmount: 250.00,
        status: 'COMPLETED',
        createTime: '2024-01-15 09:00:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.checkNo = ''
  searchForm.storeId = ''
  searchForm.status = ''
  searchForm.startDate = ''
  searchForm.endDate = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增盘点单'
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑盘点单'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除盘点单"${row.checkNo}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增盘点单' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const resetForm = () => {
  Object.assign(formData, {
    id: null,
    checkNo: '',
    storeId: null,
    checkType: 'REGULAR',
    totalQuantity: 0,
    diffQuantity: 0,
    diffAmount: 0,
    status: 'CREATED',
    remark: ''
  })
}

const statusTagMap = {
  CREATED: { type: 'primary', text: '已创建' },
  IN_PROGRESS: { type: 'warning', text: '盘点中' },
  COMPLETED: { type: 'success', text: '已完成' },
  CANCELLED: { type: 'info', text: '已取消' }
}

const checkTypeMap = {
  REGULAR: '定期盘点',
  SPOT: '临时盘点',
  CYCLE: '循环盘点'
}
</script>

<template>
  <div class="check-container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="盘点单号">
          <el-input v-model="searchForm.checkNo" placeholder="请输入单号" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="门店">
          <el-select v-model="searchForm.storeId" placeholder="请选择门店" clearable style="width: 150px">
            <el-option v-for="item in storeOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 120px">
            <el-option label="已创建" value="CREATED" />
            <el-option label="盘点中" value="IN_PROGRESS" />
            <el-option label="已完成" value="COMPLETED" />
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
        <el-table-column prop="checkNo" label="盘点单号" width="140" />
        <el-table-column prop="storeName" label="门店" width="150" />
        <el-table-column label="盘点类型" width="120" align="center">
          <template #default="{ row }">
            <el-tag>{{ checkTypeMap[row.checkType] }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="checkDate" label="盘点日期" width="120" />
        <el-table-column prop="totalQuantity" label="盘点总数" width="100" align="right" />
        <el-table-column prop="diffQuantity" label="差异数量" width="100" align="right">
          <template #default="{ row }">
            <span :class="{ 'text-danger': row.diffQuantity !== 0 }">{{ row.diffQuantity > 0 ? '+' : '' }}{{ row.diffQuantity }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="diffAmount" label="差异金额" width="120" align="right">
          <template #default="{ row }">
            <span :class="{ 'text-danger': row.diffAmount !== 0 }">¥{{ row.diffAmount.toFixed(2) }}</span>
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
            <el-form-item label="盘点单号" prop="checkNo">
              <el-input v-model="formData.checkNo" placeholder="请输入盘点单号" clearable :disabled="!!formData.id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="盘点门店" prop="storeId">
              <el-select v-model="formData.storeId" placeholder="请选择门店" style="width: 100%">
                <el-option v-for="item in storeOptions" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="盘点类型" prop="checkType">
              <el-select v-model="formData.checkType" style="width: 100%">
                <el-option label="定期盘点" value="REGULAR" />
                <el-option label="临时盘点" value="SPOT" />
                <el-option label="循环盘点" value="CYCLE" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="盘点总数" prop="totalQuantity">
              <el-input-number v-model="formData.totalQuantity" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="差异数量" prop="diffQuantity">
              <el-input-number v-model="formData.diffQuantity" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="差异金额" prop="diffAmount">
              <el-input-number v-model="formData.diffAmount" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="formData.status" style="width: 100%">
                <el-option label="已创建" value="CREATED" />
                <el-option label="盘点中" value="IN_PROGRESS" />
                <el-option label="已完成" value="COMPLETED" />
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
.check-container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.text-danger { color: #f56c6c; font-weight: 600; }
:deep(.el-dialog__body) { max-height: 70vh; overflow-y: auto; }
</style>