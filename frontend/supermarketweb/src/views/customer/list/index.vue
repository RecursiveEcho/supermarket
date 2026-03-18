<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'
import { deleteCustomer, pageCustomer, queryCustomer, saveCustomer } from '@/api/customer'

const tableData = ref([])
const loading = ref(false)
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)
const queryMode = ref(false)
const queryAllList = ref([])

const searchForm = reactive({
  userName: '',
  name: '',
  phone: '',
  gender: ''
})

const dialogVisible = ref(false)
const dialogTitle = ref('新增客户')
const formData = reactive({
  id: null,
  userName: '',
  name: '',
  age: null,
  gender: '',
  phone: '',
  address: ''
})

const formRules = {
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  name: [{ required: true, message: '请输入客户姓名', trigger: 'blur' }],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
  ]
}

const hasQuery = computed(() => !!(searchForm.userName || searchForm.name || searchForm.phone || searchForm.gender))

const loadData = async () => {
  loading.value = true
  try {
    if (queryMode.value) {
      const start = (pageNum.value - 1) * pageSize.value
      const end = start + pageSize.value
      tableData.value = queryAllList.value.slice(start, end)
      total.value = queryAllList.value.length
    } else {
      const res = await pageCustomer({ pageNum: pageNum.value, pageSize: pageSize.value })
      tableData.value = res?.list || []
      total.value = Number(res?.total || 0)
    }
  } finally {
    loading.value = false
  }
}

const handleSearch = async () => {
  if (!hasQuery.value) {
    queryMode.value = false
    pageNum.value = 1
    loadData()
    return
  }
  loading.value = true
  try {
    const list = await queryCustomer({
      userName: searchForm.userName || undefined,
      name: searchForm.name || undefined,
      phone: searchForm.phone || undefined,
      gender: searchForm.gender || undefined
    })
    queryAllList.value = Array.isArray(list) ? list : []
    queryMode.value = true
    pageNum.value = 1
    loadData()
  } finally {
    loading.value = false
  }
}

const handleReset = () => {
  searchForm.userName = ''
  searchForm.name = ''
  searchForm.phone = ''
  searchForm.gender = ''
  queryMode.value = false
  queryAllList.value = []
  pageNum.value = 1
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增客户'
  Object.assign(formData, { id: null, userName: '', name: '', age: null, gender: '', phone: '', address: '' })
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑客户'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除客户"${row.name}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return deleteCustomer(row.id)
  }).then(() => {
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
}

const handleSubmit = async () => {
  await saveCustomer(formData)
  ElMessage.success(dialogTitle.value === '新增客户' ? '添加成功' : '更新成功')
  dialogVisible.value = false
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
        <el-form-item label="用户名">
          <el-input v-model="searchForm.userName" placeholder="请输入用户名" clearable style="width: 120px" />
        </el-form-item>
        <el-form-item label="客户姓名">
          <el-input v-model="searchForm.name" placeholder="请输入姓名" clearable style="width: 120px" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="searchForm.phone" placeholder="请输入手机号" clearable style="width: 130px" />
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="searchForm.gender" placeholder="请选择性别" clearable style="width: 120px">
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
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
        <el-table-column prop="userName" label="用户名" width="120" />
        <el-table-column prop="name" label="客户姓名" width="100" />
        <el-table-column prop="phone" label="手机号" width="130" />
        <el-table-column prop="gender" label="性别" width="80" />
        <el-table-column prop="age" label="年龄" width="80" />
        <el-table-column prop="address" label="收货地址" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" width="160" />
        <el-table-column label="操作" width="150" fixed="right" align="center">
          <template #default="{ row }">
            <el-button type="primary" size="small" :icon="Edit" @click="handleEdit(-1, row)">编辑</el-button>
            <el-button type="danger" size="small" :icon="Delete" @click="handleDelete(-1, row)">删除</el-button>
          </template>
        </el-table-column>
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="700px" @closed="resetForm">
      <el-form :model="formData" :rules="formRules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户名" prop="userName">
              <el-input v-model="formData.userName" placeholder="请输入用户名" clearable />
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
            <el-form-item label="客户姓名" prop="name">
              <el-input v-model="formData.name" placeholder="请输入姓名" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-select v-model="formData.gender" placeholder="请选择性别" style="width: 100%">
                <el-option label="男" value="男" />
                <el-option label="女" value="女" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="年龄" prop="age">
              <el-input-number v-model="formData.age" :min="0" :max="120" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收货地址" prop="address">
              <el-input v-model="formData.address" placeholder="请输入收货地址" clearable />
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
.pagination { display: flex; justify-content: flex-end; padding-top: 16px; }
</style>