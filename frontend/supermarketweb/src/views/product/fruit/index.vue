<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Edit, Delete } from '@element-plus/icons-vue'
import { getProductList, saveProduct, deleteProduct } from '@/api/product'

const tableData = ref([])
const loading = ref(false)


// 加载数据
const loadData = async () => {
  loading.value = true
  try {
    const res = await getProductList({
      spuCode: searchForm.spuCode,
      spuName: searchForm.spuName,
      categoryId: searchForm.categoryId,
      status: searchForm.status,
      page: 1,
      size: 10
    })
    tableData.value = res.records || res.content || res || []
  } catch (error) {
    console.error('加载数据失败:', error)
    ElMessage.error('加载数据失败')
  } finally {
    loading.value = false
  }
}


// 提交表单
const handleSubmit = async () => {
  try {
    await saveProduct(formData)
    ElMessage.success(dialogTitle.value === '新增商品' ? '添加成功' : '更新成功')
    dialogVisible.value = false
    loadData()
  } catch (error) {
    console.error('保存失败:', error)
  }
}

// 删除
const handleDelete = async (index, row) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除商品"${row.spuName}"吗？`,
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    )

    await deleteProduct(row.id)
    tableData.value.splice(index, 1)
    ElMessage.success('删除成功')
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除失败:', error)
    }
  }
}

</script>

