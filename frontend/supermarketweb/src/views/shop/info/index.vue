<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  storeCode: '',
  storeName: '',
  status: ''
})

const dialogVisible = ref(false)
const dialogTitle = ref('新增门店')

const getInitialFormData = () => ({
  id: null,
  storeCode: '',
  storeName: '',
  storeType: 'SUPERMARKET',
  province: '',
  city: '',
  district: '',
  address: '',
  contactName: '',
  contactPhone: '',
  status: 1,
  openDate: '',
  remark: ''
})

const formData = reactive(getInitialFormData())

const formRules = {
  storeCode: [
    { required: true, message: '请输入门店编码', trigger: 'blur' },
    { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  storeName: [
    { required: true, message: '请输入门店名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  contactPhone: [
    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
  ],
  address: [
    { required: true, message: '请输入详细地址', trigger: 'blur' }
  ]
}

const STORE_TYPE_OPTIONS = [
  { value: 'SUPERMARKET', label: '超市' },
  { value: 'CONVENIENCE', label: '便利店' }
]

const STATUS_MAP = {
  1: { type: 'success', text: '营业中' },
  0: { type: 'danger', text: '已停业' }
}

const deepClone = (obj) => JSON.parse(JSON.stringify(obj))

const loadData = async () => {
  loading.value = true
  try {
    await new Promise(resolve => setTimeout(resolve, 500))
    tableData.value = [
      {
        id: 1,
        storeCode: 'YC001',
        storeName: '邕城百货总店',
        storeType: 'SUPERMARKET',
        province: '广西',
        city: '南宁',
        district: '青秀区',
        address: '民族大道 100 号',
        contactName: '张经理',
        contactPhone: '13800138001',
        status: 1,
        openDate: '2020-01-15'
      }
    ]
  } catch (error) {
    ElMessage.error('加载数据失败')
  } finally {
    loading.value = false
  }
}

const handleSearch = () => {
  loadData()
}

const handleReset = () => {
  Object.assign(searchForm, { storeCode: '', storeName: '', status: '' })
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增门店'
  Object.assign(formData, getInitialFormData())
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑门店'
  Object.assign(formData, deepClone(row))
  dialogVisible.value = true
}

const handleDelete = async (index, row) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除门店"${row.storeName}"吗？删除后无法恢复。`,
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    )

    loading.value = true
    await new Promise(resolve => setTimeout(resolve, 500))

    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  } finally {
    loading.value = false
  }
}

const handleSubmit = async () => {
  try {
    loading.value = true
    await new Promise(resolve => setTimeout(resolve, 500))

    ElMessage.success(dialogTitle.value === '新增门店' ? '添加成功' : '更新成功')
    dialogVisible.value = false
    loadData()
  } catch (error) {
    ElMessage.error('操作失败')
  } finally {
    loading.value = false
  }
}

const resetForm = () => {
  Object.assign(formData, getInitialFormData())
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="门店编码">
          <el-input v-model="searchForm.storeCode" placeholder="请输入编码" clearable style="width: 150px" />
        </el-form-item>
        <el-form-item label="门店名称">
          <el-input v-model="searchForm.storeName" placeholder="请输入名称" clearable style="width: 180px" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 100px">
            <el-option label="营业中" value="1" />
            <el-option label="已停业" value="0" />
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
        <el-table-column prop="storeCode" label="门店编码" width="120" />
        <el-table-column prop="storeName" label="门店名称" min-width="180" show-overflow-tooltip />
        <el-table-column label="门店类型" width="100">
          <template #default="{ row }">
            <el-tag>{{ row.storeType === 'SUPERMARKET' ? '超市' : '便利店' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="city" label="城市" width="100" />
        <el-table-column prop="address" label="地址" min-width="200" show-overflow-tooltip />
        <el-table-column prop="contactPhone" label="联系电话" width="130" />
        <el-table-column label="状态" width="80" align="center">
          <template #default="{ row }">
            <el-tag :type="STATUS_MAP[row.status].type">{{ STATUS_MAP[row.status].text }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="openDate" label="开业日期" width="120" />
        <el-table-column label="操作" width="150" fixed="right" align="center">
          <template #default="{ row }">
            <el-button type="primary" size="small" :icon="Edit" @click="handleEdit(-1, row)">编辑</el-button>
            <el-button type="danger" size="small" :icon="Delete" @click="handleDelete(-1, row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="900px" :close-on-click-modal="false" @closed="resetForm">
      <el-form :model="formData" :rules="formRules" label-width="120px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="门店编码" prop="storeCode">
              <el-input v-model="formData.storeCode" placeholder="请输入门店编码" clearable :disabled="!!formData.id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="门店名称" prop="storeName">
              <el-input v-model="formData.storeName" placeholder="请输入门店名称" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="门店类型" prop="storeType">
              <el-select v-model="formData.storeType" style="width: 100%">
                <el-option v-for="item in STORE_TYPE_OPTIONS" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="formData.status">
                <el-radio :label="1">营业中</el-radio>
                <el-radio :label="0">已停业</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="省份" prop="province">
              <el-input v-model="formData.province" placeholder="省份" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="城市" prop="city">
              <el-input v-model="formData.city" placeholder="城市" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="区县" prop="district">
              <el-input v-model="formData.district" placeholder="区县" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="详细地址" prop="address">
              <el-input v-model="formData.address" type="textarea" :rows="2" placeholder="请输入详细地址" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系人" prop="contactName">
              <el-input v-model="formData.contactName" placeholder="请输入联系人" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="contactPhone">
              <el-input v-model="formData.contactPhone" placeholder="请输入手机号" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="开业日期" prop="openDate">
              <el-date-picker v-model="formData.openDate" type="date" placeholder="选择日期" style="width: 100%" />
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
