<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  returnNo: '',
  supplierName: '',
  status: '',
  startDate: '',
  endDate: ''
})

const dialogVisible = ref(false)
const dialogTitle = ref('新增退货单')
const formData = reactive({
  id: null,
  returnNo: '',
  supplierId: null,
  supplierName: '',
  totalQuantity: 0,
  totalAmount: 0,
  reason: '',
  reasonType: '',
  status: 'PENDING',
  remark: ''
})

const formRules = {
  returnNo: [{ required: true, message: '请输入退货单号', trigger: 'blur' }],
  supplierName: [{ required: true, message: '请输入供应商', trigger: 'blur' }],
  reason: [{ required: true, message: '请输入退货原因', trigger: 'blur' }]
}

const supplierOptions = ref([
  { id: 1, name: '广州食品有限公司' },
  { id: 2, name: '深圳百货批发商' }
])

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        returnNo: 'RTN202401001',
        supplierName: '广州食品有限公司',
        totalQuantity: 20,
        totalAmount: 1000.00,
        reason: '商品过期',
        reasonType: 'EXPIRED',
        status: 'COMPLETED',
        createTime: '2024-01-18 14:00:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.returnNo = ''
  searchForm.supplierName = ''
  searchForm.status = ''
  searchForm.startDate = ''
  searchForm.endDate = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增退货单'
  Object.assign(formData, { id: null, returnNo: '', supplierId: null, supplierName: '', totalQuantity: 0, totalAmount: 0, reason: '', reasonType: '', status: 'PENDING', remark: '' })
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑退货单'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除退货单"${row.returnNo}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增退货单' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const statusTagMap = {
  PENDING: { type: 'warning', text: '待处理' },
  APPROVED: { type: 'success', text: '已审核' },
  IN_PROGRESS: { type: 'primary', text: '退货中' },
  COMPLETED: { type: 'success', text: '已完成' },
  REJECTED: { type: 'danger', text: '已拒绝' }
}

const reasonTypeMap = {
  EXPIRED: '商品过期',
  DAMAGED: '商品破损',
  QUALITY: '质量问题',
  WRONG: '发错货',
  OTHER: '其他'
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="退货单号">
          <el-input v-model="searchForm.returnNo" placeholder="请输入单号" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="供应商">
          <el-input v-model="searchForm.supplierName" placeholder="请输入供应商" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 120px">
            <el-option label="待处理" value="PENDING" />
            <el-option label="已审核" value="APPROVED" />
            <el-option label="退货中" value="IN_PROGRESS" />
            <el-option label="已完成" value="COMPLETED" />
            <el-option label="已拒绝" value="REJECTED" />
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
        <el-table-column prop="returnNo" label="退货单号" width="140" />
        <el-table-column prop="supplierName" label="供应商" min-width="150" show-overflow-tooltip />
        <el-table-column prop="totalQuantity" label="退货数量" width="100" align="right" />
        <el-table-column prop="totalAmount" label="退货金额" width="120" align="right">
          <template #default="{ row }">¥{{ row.totalAmount.toFixed(2) }}</template>
        </el-table-column>
        <el-table-column prop="reason" label="退货原因" min-width="150" show-overflow-tooltip />
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="800px" @closed="resetForm">
      <el-form :model="formData" :rules="formRules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="退货单号" prop="returnNo">
              <el-input v-model="formData.returnNo" placeholder="请输入退货单号" clearable :disabled="!!formData.id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="供应商" prop="supplierName">
              <el-select v-model="formData.supplierId" placeholder="请选择供应商" style="width: 100%">
                <el-option v-for="item in supplierOptions" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="退货原因类型" prop="reasonType">
              <el-select v-model="formData.reasonType" placeholder="请选择类型" style="width: 100%">
                <el-option label="商品过期" value="EXPIRED" />
                <el-option label="商品破损" value="DAMAGED" />
                <el-option label="质量问题" value="QUALITY" />
                <el-option label="发错货" value="WRONG" />
                <el-option label="其他" value="OTHER" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="退货原因" prop="reason">
              <el-input v-model="formData.reason" placeholder="请输入退货原因" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="formData.status" style="width: 100%">
                <el-option label="待处理" value="PENDING" />
                <el-option label="已审核" value="APPROVED" />
                <el-option label="退货中" value="IN_PROGRESS" />
                <el-option label="已完成" value="COMPLETED" />
                <el-option label="已拒绝" value="REJECTED" />
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
.container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
</style>