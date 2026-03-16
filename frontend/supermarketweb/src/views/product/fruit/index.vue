<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'

// 表格数据
const tableData = ref([])
const loading = ref(false)

// 搜索和筛选
const searchForm = reactive({
  spuCode: '',
  spuName: '',
  categoryId: '',
  status: ''
})

// 分类选项
const categoryOptions = ref([
  { id: 1, name: '水果' },
  { id: 2, name: '蔬菜' },
  { id: 3, name: '叶菜类' },
  { id: 4, name: '根茎类' }
])

// 对话框控制
const dialogVisible = ref(false)
const dialogTitle = ref('新增商品')
const formData = reactive({
  id: null,
  spuCode: '',
  spuName: '',
  categoryId: null,
  brandName: '',
  spec: '',
  unit: '个',
  saleUnit: '个',
  convertRate: 1,
  purchasePrice: 0,
  salePrice: 0,
  memberPrice: 0,
  taxRate: 0,
  isWeighed: 0,
  status: 1,
  remark: ''
})

// 表单验证规则
const formRules = {
  spuCode: [{ required: true, message: '请输入商品编码', trigger: 'blur' }],
  spuName: [{ required: true, message: '请输入商品名称', trigger: 'blur' }],
  categoryId: [{ required: true, message: '请选择分类', trigger: 'change' }],
  salePrice: [{ required: true, message: '请输入售价', trigger: 'blur' }]
}

// 加载数据
const loadData = () => {
  loading.value = true
  // TODO: 调用后端 API 加载数据
  // 模拟数据
  setTimeout(() => {
    tableData.value = [
      {
        id: 1,
        spuCode: 'FG001',
        spuName: '红富士苹果',
        categoryName: '水果',
        brandName: '烟台',
        spec: '80mm',
        unit: '个',
        saleUnit: 'kg',
        purchasePrice: 8.50,
        salePrice: 12.80,
        memberPrice: 11.50,
        isWeighed: 1,
        status: 1,
        createTime: '2024-01-15 10:30:00'
      },
      {
        id: 2,
        spuCode: 'FG002',
        spuName: '香蕉',
        categoryName: '水果',
        brandName: '菲律宾',
        spec: '500g',
        unit: '把',
        saleUnit: 'kg',
        purchasePrice: 6.00,
        salePrice: 9.90,
        memberPrice: 8.90,
        isWeighed: 1,
        status: 1,
        createTime: '2024-01-16 14:20:00'
      }
    ]
    loading.value = false
  }, 500)
}

// 搜索
const handleSearch = () => {
  loadData()
}

// 重置搜索
const handleReset = () => {
  searchForm.spuCode = ''
  searchForm.spuName = ''
  searchForm.categoryId = ''
  searchForm.status = ''
  loadData()
}

// 新增
const handleAdd = () => {
  dialogTitle.value = '新增商品'
  resetForm()
  dialogVisible.value = true
}

// 编辑
const handleEdit = (index, row) => {
  dialogTitle.value = '编辑商品'
  Object.assign(formData, {
    id: row.id,
    spuCode: row.spuCode,
    spuName: row.spuName,
    categoryId: row.categoryId,
    brandName: row.brandName,
    spec: row.spec,
    unit: row.unit,
    saleUnit: row.saleUnit,
    convertRate: row.convertRate,
    purchasePrice: row.purchasePrice,
    salePrice: row.salePrice,
    memberPrice: row.memberPrice,
    taxRate: row.taxRate,
    isWeighed: row.isWeighed,
    status: row.status,
    remark: row.remark
  })
  dialogVisible.value = true
}

// 删除
const handleDelete = (index, row) => {
  ElMessageBox.confirm(
    `确定要删除商品"${row.spuName}"吗？`,
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    // TODO: 调用删除 API
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

// 提交表单
const handleSubmit = () => {
  // TODO: 调用保存 API
  ElMessage.success(dialogTitle.value === '新增商品' ? '添加成功' : '更新成功')
  dialogVisible.value = false
  loadData()
}

// 重置表单
const resetForm = () => {
  Object.assign(formData, {
    id: null,
    spuCode: '',
    spuName: '',
    categoryId: null,
    brandName: '',
    spec: '',
    unit: '个',
    saleUnit: '个',
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

// 状态标签
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
    <!-- 搜索区域 -->
    <el-card class="search-card" shadow="never">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="商品编码">
          <el-input
            v-model="searchForm.spuCode"
            placeholder="请输入商品编码"
            clearable
            style="width: 200px"
          />
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input
            v-model="searchForm.spuName"
            placeholder="请输入商品名称"
            clearable
            style="width: 200px"
          />
        </el-form-item>
        <el-form-item label="商品分类">
          <el-select
            v-model="searchForm.categoryId"
            placeholder="请选择分类"
            clearable
            style="width: 150px"
          >
            <el-option
              v-for="item in categoryOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select
            v-model="searchForm.status"
            placeholder="请选择状态"
            clearable
            style="width: 120px"
          >
            <el-option label="在售" value="1" />
            <el-option label="停用" value="0" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">
            搜索
          </el-button>
          <el-button :icon="Plus" @click="handleAdd">
            新增
          </el-button>
          <el-button @click="handleReset">
            重置
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 表格区域 -->
    <el-card class="table-card" shadow="never">
      <el-table
        v-loading="loading"
        :data="tableData"
        style="width: 100%"
        border
        stripe
      >
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
            <el-tag :type="statusTagMap[row.status].type">
              {{ statusTagMap[row.status].text }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160" />
        <el-table-column label="操作" width="150" fixed="right" align="center">
          <template #default="{ row }">
            <el-button
              type="primary"
              size="small"
              :icon="Edit"
              @click="handleEdit(-1, row)"
            >
              编辑
            </el-button>
            <el-button
              type="danger"
              size="small"
              :icon="Delete"
              @click="handleDelete(-1, row)"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增/编辑对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="800px"
      :close-on-click-modal="false"
      @closed="resetForm"
    >
      <el-form
        ref="formRef"
        :model="formData"
        :rules="formRules"
        label-width="100px"
      >
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="商品编码" prop="spuCode">
              <el-input
                v-model="formData.spuCode"
                placeholder="请输入商品编码"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品名称" prop="spuName">
              <el-input
                v-model="formData.spuName"
                placeholder="请输入商品名称"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="商品分类" prop="categoryId">
              <el-select
                v-model="formData.categoryId"
                placeholder="请选择分类"
                style="width: 100%"
              >
                <el-option
                  v-for="item in categoryOptions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品牌" prop="brandName">
              <el-input
                v-model="formData.brandName"
                placeholder="请输入品牌"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="规格" prop="spec">
              <el-input
                v-model="formData.spec"
                placeholder="例：500g/盒"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="基本单位" prop="unit">
              <el-input
                v-model="formData.unit"
                placeholder="单位"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="销售单位" prop="saleUnit">
              <el-input
                v-model="formData.saleUnit"
                placeholder="销售单位"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位换算" prop="convertRate">
              <el-input-number
                v-model="formData.convertRate"
                :min="0.0001"
                :precision="4"
                :step="1"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="标准进价" prop="purchasePrice">
              <el-input-number
                v-model="formData.purchasePrice"
                :min="0"
                :precision="2"
                :step="0.1"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="标准售价" prop="salePrice">
              <el-input-number
                v-model="formData.salePrice"
                :min="0"
                :precision="2"
                :step="0.1"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="会员价" prop="memberPrice">
              <el-input-number
                v-model="formData.memberPrice"
                :min="0"
                :precision="2"
                :step="0.1"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="税率 (%)" prop="taxRate">
              <el-input-number
                v-model="formData.taxRate"
                :min="0"
                :max="100"
                :precision="2"
                :step="0.1"
                style="width: 100%"
              />
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
              <el-input
                v-model="formData.remark"
                type="textarea"
                :rows="3"
                placeholder="请输入备注信息"
                maxlength="512"
                show-word-limit
              />
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
.product-container {
  padding: 20px;
}

.search-card {
  margin-bottom: 20px;
}

.search-form {
  display: flex;
  flex-wrap: wrap;
}

.search-form :deep(.el-form-item) {
  margin-right: 16px;
}

.table-card {
  min-height: 500px;
}

.price-text {
  color: #f56c6c;
  font-weight: 600;
}

:deep(.el-dialog__body) {
  max-height: 70vh;
  overflow-y: auto;
}
</style>