<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  levelName: '',
  status: ''
})

const dialogVisible = ref(false)
const dialogTitle = ref('新增会员等级')
const formData = reactive({
  id: null,
  levelName: '',
  minPoint: 0,
  discountRate: 100,
  status: 1,
  remark: ''
})

const formRules = {
  levelName: [{ required: true, message: '请输入等级名称', trigger: 'blur' }],
  minPoint: [{ required: true, message: '请输入最低积分', trigger: 'blur' }],
  discountRate: [{ required: true, message: '请输入折扣率', trigger: 'blur' }]
}

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        levelName: '普通会员',
        minPoint: 0,
        discountRate: 100,
        status: 1,
        createTime: '2024-01-01'
      },
      {
        id: 2,
        levelName: '银卡会员',
        minPoint: 1000,
        discountRate: 95,
        status: 1,
        createTime: '2024-01-01'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.levelName = ''
  searchForm.status = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增会员等级'
  Object.assign(formData, { id: null, levelName: '', minPoint: 0, discountRate: 100, status: 1, remark: '' })
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑会员等级'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除等级"${row.levelName}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增会员等级' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const statusTagMap = {
  1: { type: 'success', text: '启用' },
  0: { type: 'danger', text: '禁用' }
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="等级名称">
          <el-input v-model="searchForm.levelName" placeholder="请输入等级名称" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 100px">
            <el-option label="启用" value="1" />
            <el-option label="禁用" value="0" />
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
        <el-table-column prop="levelName" label="等级名称" width="150" />
        <el-table-column prop="minPoint" label="最低积分" width="120" align="right" />
        <el-table-column prop="discountRate" label="折扣率 (%)" width="120" align="right">
          <template #default="{ row }">{{ row.discountRate }}%</template>
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px" @closed="resetForm">
      <el-form :model="formData" :rules="formRules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="等级名称" prop="levelName">
              <el-input v-model="formData.levelName" placeholder="请输入等级名称" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最低积分" prop="minPoint">
              <el-input-number v-model="formData.minPoint" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="折扣率 (%)" prop="discountRate">
              <el-input-number v-model="formData.discountRate" :min="0" :max="100" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="formData.status">
                <el-radio :label="1">启用</el-radio>
                <el-radio :label="0">禁用</el-radio>
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