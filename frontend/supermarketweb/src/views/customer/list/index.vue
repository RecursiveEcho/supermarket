<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  customerName: '',
  phone: '',
  levelId: '',
  status: ''
})

const levelOptions = ref([
  { id: 1, name: '普通客户' },
  { id: 2, name: 'VIP 客户' },
  { id: 3, name: '重要客户' }
])

const dialogVisible = ref(false)
const dialogTitle = ref('新增客户')
const formData = reactive({
  id: null,
  customerName: '',
  phone: '',
  email: '',
  levelId: null,
  source: '',
  status: 1,
  remark: ''
})

const formRules = {
  customerName: [{ required: true, message: '请输入客户姓名', trigger: 'blur' }],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
  ]
}

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        customerName: '李先生',
        phone: '13800138001',
        email: 'li@example.com',
        levelName: 'VIP 客户',
        source: '门店',
        status: 1,
        createTime: '2024-01-10'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.customerName = ''
  searchForm.phone = ''
  searchForm.levelId = ''
  searchForm.status = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增客户'
  Object.assign(formData, { id: null, customerName: '', phone: '', email: '', levelId: null, source: '', status: 1, remark: '' })
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑客户'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除客户"${row.customerName}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增客户' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const statusTagMap = {
  1: { type: 'success', text: '活跃' },
  0: { type: 'info', text: '休眠' }
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="客户姓名">
          <el-input v-model="searchForm.customerName" placeholder="请输入姓名" clearable style="width: 120px" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="searchForm.phone" placeholder="请输入手机号" clearable style="width: 130px" />
        </el-form-item>
        <el-form-item label="客户等级">
          <el-select v-model="searchForm.levelId" placeholder="请选择等级" clearable style="width: 120px">
            <el-option v-for="item in levelOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 100px">
            <el-option label="活跃" value="1" />
            <el-option label="休眠" value="0" />
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
        <el-table-column prop="customerName" label="客户姓名" width="100" />
        <el-table-column prop="phone" label="手机号" width="130" />
        <el-table-column prop="email" label="邮箱" width="150" show-overflow-tooltip />
        <el-table-column prop="levelName" label="等级" width="100" />
        <el-table-column prop="source" label="来源" width="100" />
        <el-table-column label="状态" width="80" align="center">
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="700px" @closed="resetForm">
      <el-form :model="formData" :rules="formRules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户姓名" prop="customerName">
              <el-input v-model="formData.customerName" placeholder="请输入姓名" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="formData.phone" placeholder="请输入手机号" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="formData.email" placeholder="请输入邮箱" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户等级" prop="levelId">
              <el-select v-model="formData.levelId" placeholder="请选择等级" style="width: 100%">
                <el-option v-for="item in levelOptions" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="客户来源" prop="source">
              <el-input v-model="formData.source" placeholder="例：门店/线上" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="formData.status">
                <el-radio :label="1">活跃</el-radio>
                <el-radio :label="0">休眠</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="formData.remark" type="textarea" :rows="3" placeholder="请输入备注" maxlength="256" show-word-limit />
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