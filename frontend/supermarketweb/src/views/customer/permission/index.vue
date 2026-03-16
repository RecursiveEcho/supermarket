<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Save } from '@element-plus/icons-vue'

const loading = ref(false)
const searchKeyword = ref('')
const customerList = ref([])
const selectedCustomerId = ref(null)
const selectedCustomerName = ref('')
const permissions = reactive({
  productView: false,
  orderCreate: false,
  orderView: false,
  paymentView: false,
  refundCreate: false,
  dataExport: false,
  reportView: false
})

const permissionOptions = [
  { key: 'productView', label: '商品查看', desc: '查看商品价格、库存等信息' },
  { key: 'orderCreate', label: '订单创建', desc: '创建采购订单' },
  { key: 'orderView', label: '订单查看', desc: '查看历史订单记录' },
  { key: 'paymentView', label: '支付查看', desc: '查看支付记录和账单' },
  { key: 'refundCreate', label: '退货申请', desc: '申请退货退款' },
  { key: 'dataExport', label: '数据导出', desc: '导出业务数据' },
  { key: 'reportView', label: '报表查看', desc: '查看统计报表' }
]

const loadCustomers = () => {
  loading.value = true
  setTimeout(() => {
    customerList.value = [
      { id: 1, customerCode: 'KH001', customerName: '南宁科技有限公司' },
      { id: 2, customerCode: 'KH002', customerName: '广西贸易公司' }
    ]
    loading.value = false
  }, 500)
}

const handleRowChange = (row) => {
  if (row) {
    selectedCustomerId.value = row.id
    selectedCustomerName.value = row.customerName
    loadPermissions()
  }
}

const loadPermissions = () => {
  if (!selectedCustomerId.value) return
  loading.value = true
  setTimeout(() => {
    permissions.productView = true
    permissions.orderView = true
    permissions.paymentView = true
    loading.value = false
  }, 500)
}

const handleSave = () => {
  loading.value = true
  setTimeout(() => {
    ElMessage.success('权限保存成功')
    loading.value = false
  }, 500)
}

const handleSelectAll = (checked) => {
  Object.keys(permissions).forEach(key => {
    permissions[key] = checked
  })
}

loadCustomers()
</script>

<template>
  <div class="permission-container">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card shadow="never">
          <template #header>
            <span>客户列表</span>
          </template>
          <el-input v-model="searchKeyword" placeholder="搜索客户" clearable style="margin-bottom: 16px" />
          <el-table :data="customerList" style="width: 100%" border highlight-current-row @current-change="handleRowChange">
            <el-table-column prop="customerCode" label="编码" width="80" />
            <el-table-column prop="customerName" label="名称" show-overflow-tooltip />
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>权限配置 - {{ selectedCustomerName || '请选择客户' }}</span>
              <el-button type="primary" :icon="Save" @click="handleSave" :disabled="!selectedCustomerId" :loading="loading">保存配置</el-button>
            </div>
          </template>
          <div v-if="!selectedCustomerId" class="empty-tip">
            <el-empty description="请从左侧选择客户" />
          </div>
          <div v-else>
            <el-form label-width="120px">
              <el-form-item label="全选">
                <el-checkbox @change="handleSelectAll">全选/全不选</el-checkbox>
              </el-form-item>
              <el-divider />
              <el-form-item v-for="item in permissionOptions" :key="item.key" :label="item.label">
                <el-checkbox v-model="permissions[item.key]" />
                <span class="permission-desc">{{ item.desc }}</span>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.permission-container { padding: 20px; }
.card-header { display: flex; justify-content: space-between; align-items: center; }
.empty-tip { height: 400px; display: flex; justify-content: center; align-items: center; }
.permission-desc { margin-left: 10px; color: #909399; font-size: 13px; }
</style>