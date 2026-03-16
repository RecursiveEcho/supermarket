<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'

const activeName = ref('basic')

const basicForm = reactive({
  systemName: '邕城百货管理系统',
  companyName: '邕城百货有限公司',
  customerServicePhone: '400-123-4567',
  customerServiceEmail: 'service@example.com'
})

const businessForm = reactive({
  taxRate: 13,
  minOrderAmount: 0,
  freeShippingAmount: 99,
  maxReturnDays: 7
})

const handleSave = () => {
  ElMessage.success('保存成功')
}
</script>

<template>
  <div class="container">
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="基本设置" name="basic">
        <el-form :model="basicForm" label-width="150px" style="padding: 20px">
          <el-form-item label="系统名称">
            <el-input v-model="basicForm.systemName" clearable />
          </el-form-item>
          <el-form-item label="公司名称">
            <el-input v-model="basicForm.companyName" clearable />
          </el-form-item>
          <el-form-item label="客服电话">
            <el-input v-model="basicForm.customerServicePhone" clearable />
          </el-form-item>
          <el-form-item label="客服邮箱">
            <el-input v-model="basicForm.customerServiceEmail" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSave">保存设置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="业务设置" name="business">
        <el-form :model="businessForm" label-width="150px" style="padding: 20px">
          <el-form-item label="默认税率 (%)">
            <el-input-number v-model="businessForm.taxRate" :min="0" :max="100" />
          </el-form-item>
          <el-form-item label="最低起送金额">
            <el-input-number v-model="businessForm.minOrderAmount" :min="0" :precision="2" />
          </el-form-item>
          <el-form-item label="包邮金额">
            <el-input-number v-model="businessForm.freeShippingAmount" :min="0" :precision="2" />
          </el-form-item>
          <el-form-item label="最大退货天数">
            <el-input-number v-model="businessForm.maxReturnDays" :min="0" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSave">保存设置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.container { padding: 20px; }
</style>