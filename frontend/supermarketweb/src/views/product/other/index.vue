<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

const tableData = ref([])
const loading = ref(false)

const searchForm = reactive({
  spuCode: '',
  spuName: '',
  categoryId: '',
  status: ''
})

const categoryOptions = ref([
  { id: 1, name: '日用品' },
  { id: 2, name: '清洁用品' },
  { id: 3, name: '纸品' },
  { id: 4, name: '其他' }
])

const dialogVisible = ref(false)
const dialogTitle = ref('新增商品')
const formData = reactive({
  id: null,
  spuCode: '',
  spuName: '',
  categoryId: null,
  brandName: '',
  spec: '',
  unit: '件',
  saleUnit: '件',
  convertRate: 1,
  purchasePrice: 0,
  salePrice: 0,
  memberPrice: 0,
  taxRate: 0,
  isWeighed: 0,
  status: 1,
  remark: ''
})

const formRules = {
  spuCode: [{ required: true, message: '请输入商品编码', trigger: 'blur' }],
  spuName: [{ required: true, message: '请输入商品名称', trigger: 'blur' }],
  categoryId: [{ required: true, message: '请选择分类', trigger: 'change' }],
  salePrice: [{ required: true, message: '请输入售价', trigger: 'blur' }]
}

const loadData = () => {
  loading.value = true
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        spuCode: 'OTH001',
        spuName: '维达抽纸',
        categoryName: '纸品',
        brandName: '维达',
        spec: '120 抽*3 包',
        unit: '提',
        saleUnit: '提',
        purchasePrice: 5.00,
        salePrice: 8.90,
        memberPrice: 8.00,
        isWeighed: 0,
        status: 1,
        createTime: '2024-01-15 10:30:00'
      },
      {
        id: 2,
        spuCode: 'OTH002',
        spuName: '蓝月亮洗衣液',
        categoryName: '清洁用品',
        brandName: '蓝月亮',
        spec: '3kg',
        unit: '瓶',
        saleUnit: '瓶',
        purchasePrice: 25.00,
        salePrice: 39.90,
        memberPrice: 36.00,
        isWeighed: 0,
        status: 1,
        createTime: '2024-01-16 09:15:00'
      }
    ]
    loading.value = false
  }, 500)
}

const handleSearch = () => loadData()
const handleReset = () => {
  searchForm.spuCode = ''
  searchForm.spuName = ''
  searchForm.categoryId = ''
  searchForm.status = ''
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '新增商品'
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (index, row) => {
  dialogTitle.value = '编辑商品'
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (index, row) => {
  ElMessageBox.confirm(`确定要删除商品"${row.spuName}"吗？`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const handleSubmit = () => {
  ElMessage.success(dialogTitle.value === '新增商品' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

const resetForm = () => {
  Object.assign(formData, {
    id: null,
    spuCode: '',
    spuName: '',
    categoryId: null,
    brandName: '',
    spec: '',
    unit: '件',
    saleUnit: '件',
    convertRate: 1,
    purchasePrice: 0,
    salePrice: 0,
    memberPrice: 0,
    taxRate: 0,
    isWeighed: 0,
    status: 1,
    remark: ''
  })
}

const statusTagMap = {
  1: { type: 'success', text: '在售' },
  0: { type: 'danger', text: '停用' }
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="product-container">
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="商品编码">
          <el-input v-model="searchForm.spuCode" placeholder="请输入商品编码" clearable style="width: 200px" />
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input v-model="searchForm.spuName" placeholder="请输入商品名称" clearable style="width: 200px" />
        </el-form-item>
        <el-form-item label="商品分类">
          <el-select v-model="searchForm.categoryId" placeholder="请选择分类" clearable style="width: 150px">
            <el-option v-for="item in categoryOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 120px">
            <el-option label="在售" value="1" />
            <el-option label="停用" value="0" />
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
        <el-table-column prop="spuCode" label="商品编码" width="120" />
        <el-table-column prop="spuName" label="商品名称" min-width="180" show-overflow-tooltip />
        <el-table-column prop="categoryName" label="分类" width="100" />
        <el-table-column prop="brandName" label="品牌" width="100" show-overflow-tooltip />
        <el-table-column prop="spec" label="规格" width="100" show-overflow-tooltip />
        <el-table-column prop="unit" label="单位" width="80" />
        <el-table-column prop="purchasePrice" label="进价" width="90" align="right">
          <template #default="{ row }">
            <span class="price-text">¥{{ row.purchasePrice.toFixed(2) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="salePrice" label="售价" width="90" align="right">
          <template #default="{ row }">
            <span class="price-text">¥{{ row.salePrice.toFixed(2) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="memberPrice" label="会员价" width="90" align="right">
          <template #default="{ row }">
            <span class="price-text">¥{{ row.memberPrice.toFixed(2) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="称重" width="80" align="center">
          <template #default="{ row }">
            <el-tag :type="row.isWeighed === 1 ? 'success' : 'info'">
              {{ row.isWeighed === 1 ? '是' : '否' }}
            </el-tag>
          </template>
        </el-table-column>
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

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="800px" :close-on-click-modal="false" @closed="resetForm">
      <el-form ref="formRef" :model="formData" :rules="formRules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="商品编码" prop="spuCode">
              <el-input v-model="formData.spuCode" placeholder="请输入商品编码" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品名称" prop="spuName">
              <el-input v-model="formData.spuName" placeholder="请输入商品名称" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="商品分类" prop="categoryId">
              <el-select v-model="formData.categoryId" placeholder="请选择分类" style="width: 100%">
                <el-option v-for="item in categoryOptions" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品牌" prop="brandName">
              <el-input v-model="formData.brandName" placeholder="请输入品牌" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="规格" prop="spec">
              <el-input v-model="formData.spec" placeholder="例：120 抽*3 包" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位" prop="unit">
              <el-input v-model="formData.unit" placeholder="单位" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="进价" prop="purchasePrice">
              <el-input-number v-model="formData.purchasePrice" :min="0" :precision="2" :step="0.1" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="售价" prop="salePrice">
              <el-input-number v-model="formData.salePrice" :min="0" :precision="2" :step="0.1" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="会员价" prop="memberPrice">
              <el-input-number v-model="formData.memberPrice" :min="0" :precision="2" :step="0.1" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="税率 (%)" prop="taxRate">
              <el-input-number v-model="formData.taxRate" :min="0" :max="100" :precision="2" :step="0.1" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="是否称重" prop="isWeighed">
              <el-radio-group v-model="formData.isWeighed">
                <el-radio :label="1">是</el-radio>
                <el-radio :label="0">否</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="formData.status">
                <el-radio :label="1">在售</el-radio>
                <el-radio :label="0">停用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="formData.remark" type="textarea" :rows="3" placeholder="请输入备注信息" maxlength="512" show-word-limit />
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
.product-container { padding: 20px; }
.search-card { margin-bottom: 20px; }
.search-form { display: flex; flex-wrap: wrap; }
.search-form :deep(.el-form-item) { margin-right: 16px; }
.table-card { min-height: 500px; }
.price-text { color: #f56c6c; font-weight: 600; }
:deep(.el-dialog__body) { max-height: 70vh; overflow-y: auto; }
</style>