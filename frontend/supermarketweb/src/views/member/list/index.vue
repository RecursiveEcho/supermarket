<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :model="searchForm" inline>
        <el-form-item label="用户名">
          <el-input v-model="searchForm.userName" placeholder="请输入用户名" clearable />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="searchForm.name" placeholder="请输入姓名" clearable />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="searchForm.phone" placeholder="请输入手机号" clearable />
        </el-form-item>
        <el-form-item label="会员等级">
          <el-select v-model="searchForm.level" placeholder="请选择会员等级" clearable style="width: 160px;">
            <el-option v-for="item in levelOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <template #header>
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <span>会员列表</span>
          <el-button type="primary" :icon="Plus" @click="handleAdd">新增会员</el-button>
        </div>
      </template>

      <el-table v-loading="loading" :data="tableData" border stripe>
        <el-table-column prop="userName" label="用户名" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="phone" label="手机号" />
        <el-table-column prop="gender" label="性别" width="80" />
        <el-table-column prop="age" label="年龄" width="80" />
        <el-table-column prop="card" label="卡号" />
        <el-table-column prop="level" label="会员等级" width="120" />
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="{ $index, row }">
            <el-button type="primary" size="small" @click="handleEdit($index, row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete($index, row)">删除</el-button>
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form ref="formRef" :model="formData" :rules="rules" label-width="100px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formData.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formData.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="formData.gender" placeholder="请选择性别" style="width: 160px;">
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="formData.age" :min="0" :max="120" />
        </el-form-item>
        <el-form-item label="卡号" prop="card">
          <el-input v-model="formData.card" placeholder="请输入卡号" />
        </el-form-item>
        <el-form-item label="会员等级" prop="level">
          <el-select v-model="formData.level" placeholder="请选择会员等级" style="width: 160px;">
            <el-option v-for="item in levelOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus } from '@element-plus/icons-vue'
import { pageMember, queryMember, saveMember, deleteMember } from '@/api/member'

const tableData = ref([])
const loading = ref(false)
const dialogVisible = ref(false)
const dialogTitle = ref('新增会员')
const formRef = ref()

const searchForm = reactive({
  userName: '',
  name: '',
  phone: '',
  level: ''
})

const formData = reactive({
  id: null,
  userName: '',
  name: '',
  age: null,
  phone: '',
  gender: '',
  card: '',
  level: ''
})

const levelOptions = ref(['普通会员', 'VIP会员', '黄金会员'])

const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const queryMode = ref(false)
const queryAllList = ref([])

const rules = {
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }]
}

const hasQuery = computed(() => {
  return !!(searchForm.userName || searchForm.name || searchForm.phone || searchForm.level)
})

const loadData = async () => {
  loading.value = true
  try {
    if (queryMode.value) {
      const start = (pageNum.value - 1) * pageSize.value
      const end = start + pageSize.value
      tableData.value = queryAllList.value.slice(start, end)
      total.value = queryAllList.value.length
    } else {
      const res = await pageMember({ pageNum: pageNum.value, pageSize: pageSize.value })
      tableData.value = res?.list || []
      total.value = Number(res?.total || 0)
    }
  } finally {
    loading.value = false
  }
}

const resetForm = () => {
  searchForm.userName = ''
  searchForm.name = ''
  searchForm.phone = ''
  searchForm.level = ''
  queryMode.value = false
  queryAllList.value = []
  pageNum.value = 1
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增会员'
  Object.assign(formData, { id: null, userName: '', name: '', age: null, phone: '', gender: '', card: '', level: '' })
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑会员'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleSubmit = async () => {
  await formRef.value?.validate()
  await saveMember(formData)
  ElMessage.success(dialogTitle.value === '新增会员' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const handleDelete = async (index, row) => {
  try {
    await ElMessageBox.confirm(`确定要删除会员"${row.name}"吗？`, '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await deleteMember(row.id)
    ElMessage.success('删除成功')
    loadData()
  } catch (error) {}
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
    const list = await queryMember({
      userName: searchForm.userName || undefined,
      name: searchForm.name || undefined,
      phone: searchForm.phone || undefined,
      level: searchForm.level || undefined
    })
    queryAllList.value = Array.isArray(list) ? list : []
    queryMode.value = true
    pageNum.value = 1
    loadData()
  } finally {
    loading.value = false
  }
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

<style scoped>
.container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.table-card { min-height: 500px; }
.pagination { display: flex; justify-content: flex-end; padding-top: 16px; }
</style>
