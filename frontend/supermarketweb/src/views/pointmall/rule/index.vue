<script setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  ruleName: '',
  ruleType: ''
})

const dialogVisible = ref(false)
const dialogTitle = ref('新增积分规则')
const formData = reactive({
  id: null,
  ruleName: '',
  ruleType: '',
  ruleDesc: '',
  points: 0,
  minAmount: 0,
  maxPoints: 0,
  status: 1,
  remark: ''
})

const formRules = {
  ruleName: [{ required: true, message: '请输入规则名称', trigger: 'blur' }],
  ruleType: [{ required: true, message: '请选择规则类型', trigger: 'change' }],
  points: [{ required: true, message: '请输入积分值', trigger: 'blur' }]
}

const ruleTypeOptions = [
  { value: 'SHOPPING', label: '购物积分' },
  { value: 'SIGN_IN', label: '签到积分' },
  { value: 'REVIEW', label: '评价积分' },
  { value: 'INVITE', label: '邀请积分' }
]

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        ruleName: '购物消费积分',
        ruleType: 'SHOPPING',
        ruleDesc: '每消费 1 元积 1 分',
        points: 1,
        minAmount: 0,
        maxPoints: 1000,
        status: 1,
        createTime: '2024-01-01 00:00:00'
      },
      {
        id: 2,
        ruleName: '每日签到积分',
        ruleType: 'SIGN_IN',
        ruleDesc: '每日签到获得积分',
        points: 10,
        minAmount: 0,
        maxPoints: 10,
        status: 1,
        createTime: '2024-01-01 00:00:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.ruleName = ''
  searchForm.ruleType = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增积分规则'
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑积分规则'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除规则"${row.ruleName}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增积分规则' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const resetForm = () => {
  Object.assign(formData, {
    id: null,
    ruleName: '',
    ruleType: '',
    ruleDesc: '',
    points: 0,
    minAmount: 0,
    maxPoints: 0,
    status: 1,
    remark: ''
  })
}

const statusMap = {
  0: { type: 'info', text: '禁用' },
  1: { type: 'success', text: '启用' }
}

const getRuleTypeLabel = (value) => {
  const item = ruleTypeOptions.find(opt => opt.value === value)
  return item ? item.label : value
}
</script>

<template>
  <div class="rule-container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="规则名称">
          <el-input v-model="searchForm.ruleName" placeholder="请输入名称" clearable style="width: 180px" />
        </el-form-item>
        <el-form-item label="规则类型">
          <el-select v-model="searchForm.ruleType" placeholder="请选择类型" clearable style="width: 140px">
            <el-option v-for="item in ruleTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
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
      <template #header>
        <div class="card-header">
          <span>积分规则配置</span>
          <el-tag type="info">总规则：{{ tableData.length }}</el-tag>
        </div>
      </template>
      <el-table v-loading="loading" :data="tableData" style="width: 100%" border stripe>
        <el-table-column type="index" label="序号" width="60" align="center" />
        <el-table-column prop="ruleName" label="规则名称" min-width="150" show-overflow-tooltip />
        <el-table-column label="规则类型" width="120" align="center">
          <template #default="{ row }">
            <el-tag>{{ getRuleTypeLabel(row.ruleType) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="ruleDesc" label="规则描述" min-width="200" show-overflow-tooltip />
        <el-table-column prop="points" label="积分值" width="80" align="right" />
        <el-table-column prop="minAmount" label="最低金额" width="100" align="right">
          <template #default="{ row }">¥{{ row.minAmount.toFixed(2) }}</template>
        </el-table-column>
        <el-table-column prop="maxPoints" label="每日上限" width="80" align="right" />
        <el-table-column label="状态" width="80" align="center">
          <template #default="{ row }">
            <el-tag :type="statusMap[row.status].type">{{ statusMap[row.status].text }}</el-tag>
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
            <el-form-item label="规则名称" prop="ruleName">
              <el-input v-model="formData.ruleName" placeholder="请输入规则名称" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规则类型" prop="ruleType">
              <el-select v-model="formData.ruleType" placeholder="请选择规则类型" style="width: 100%">
                <el-option v-for="item in ruleTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="规则描述" prop="ruleDesc">
              <el-input v-model="formData.ruleDesc" type="textarea" :rows="2" placeholder="请输入规则描述" maxlength="256" show-word-limit />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="积分值" prop="points">
              <el-input-number v-model="formData.points" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最低金额" prop="minAmount">
              <el-input-number v-model="formData.minAmount" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="每日上限" prop="maxPoints">
              <el-input-number v-model="formData.maxPoints" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="启用状态" prop="status">
              <el-switch v-model="formData.status" :active-value="1" :inactive-value="0" />
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
.rule-container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.card-header { display: flex; justify-content: space-between; align-items: center; }
:deep(.el-dialog__body) { max-height: 70vh; overflow-y: auto; }
</style>