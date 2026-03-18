<template>
  <div class="container">
    <el-card class="search-card" shadow="never">
      <el-form :model="searchForm" inline>
        <el-form-item label="商品名称">
          <el-input v-model="searchForm.name" placeholder="请输入商品名称" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="table-card" shadow="never">
      <template #header>
        <div class="table-header">
          <span>{{ title }}</span>
          <el-button type="primary" :icon="Plus" @click="handleAdd">新增商品</el-button>
        </div>
      </template>

      <el-table v-loading="loading" :data="tableData" border stripe>
        <el-table-column prop="name" label="商品名称" />
        <el-table-column prop="costPrice" label="成本价" width="120" />
        <el-table-column prop="sellingPrice" label="售价" width="120" />
        <el-table-column prop="stock" label="库存" width="120" />
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
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
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="成本价" prop="costPrice">
          <el-input-number v-model="formData.costPrice" :min="0" :precision="2" :step="0.1" />
        </el-form-item>
        <el-form-item label="售价" prop="sellingPrice">
          <el-input-number v-model="formData.sellingPrice" :min="0" :precision="2" :step="0.1" />
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input-number v-model="formData.stock" :min="0" :step="1" />
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
import { computed, onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Search } from '@element-plus/icons-vue'
import { deleteProduct, pageProduct, queryProduct, saveProduct } from '@/api/product'

const props = defineProps({
  title: { type: String, default: '商品列表' }
})

const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)

const dialogVisible = ref(false)
const dialogTitle = ref('新增商品')
const formRef = ref()

const searchForm = reactive({
  name: ''
})

const formData = reactive({
  id: null,
  name: '',
  costPrice: 0,
  sellingPrice: 0,
  stock: 0
})

const rules = {
  name: [{ required: true, message: '请输入商品名称', trigger: 'blur' }]
}

const queryMode = ref(false)
const queryAllList = ref([])

const hasQuery = computed(() => !!searchForm.name)

const loadData = async () => {
  loading.value = true
  try {
    if (queryMode.value) {
      const start = (pageNum.value - 1) * pageSize.value
      const end = start + pageSize.value
      tableData.value = queryAllList.value.slice(start, end)
      total.value = queryAllList.value.length
    } else {
      const res = await pageProduct({ pageNum: pageNum.value, pageSize: pageSize.value })
      tableData.value = res?.list || []
      total.value = Number(res?.total || 0)
    }
  } finally {
    loading.value = false
  }
}

const resetSearch = () => {
  searchForm.name = ''
  queryMode.value = false
  queryAllList.value = []
  pageNum.value = 1
  loadData()
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
    const list = await queryProduct({ name: searchForm.name || undefined })
    queryAllList.value = Array.isArray(list) ? list : []
    queryMode.value = true
    pageNum.value = 1
    loadData()
  } finally {
    loading.value = false
  }
}

const handleAdd = () => {
  dialogTitle.value = '新增商品'
  Object.assign(formData, { id: null, name: '', costPrice: 0, sellingPrice: 0, stock: 0 })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑商品'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleSubmit = async () => {
  await formRef.value?.validate()
  await saveProduct(formData)
  ElMessage.success(dialogTitle.value === '新增商品' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm(`确定要删除商品"${row.name}"吗？`, '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await deleteProduct(row.id)
    ElMessage.success('删除成功')
    loadData()
  } catch {}
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

onMounted(loadData)
</script>

<style scoped>
.container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.table-card { min-height: 500px; }
.table-header { display: flex; justify-content: space-between; align-items: center; }
.pagination { display: flex; justify-content: flex-end; padding-top: 16px; }
</style>

