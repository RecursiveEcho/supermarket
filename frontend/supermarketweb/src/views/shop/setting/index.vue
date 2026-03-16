<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Save, Upload } from '@element-plus/icons-vue'

const loading = ref(false)
const shopForm = reactive({
  shopName: '邕城百货总店',
  shopCode: 'SHOP001',
  shopType: 'FLAGSHIP',
  businessLicense: '',
  legalPerson: '',
  contactPhone: '',
  contactEmail: '',
  province: '广西壮族自治区',
  city: '南宁市',
  district: '青秀区',
  address: '民族大道 123 号',
  longitude: 108.36654,
  latitude: 22.81627,
  openTime: '08:00',
  closeTime: '22:00',
  description: '大型综合超市',
  shopImages: []
})

const shopTypeOptions = [
  { value: 'FLAGSHIP', label: '旗舰店' },
  { value: 'STANDARD', label: '标准店' },
  { value: 'COMMUNITY', label: '社区店' }
]

const handleSave = () => {
  loading.value = true
  setTimeout(() => {
    ElMessage.success('店铺设置保存成功')
    loading.value = false
  }, 500)
}

const handleUpload = () => {
  ElMessage.info('图片上传功能待实现')
}
</script>

<template>
  <div class="setting-container">
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>店铺设置</span>
          <el-button type="primary" :icon="Save" @click="handleSave" :loading="loading">保存设置</el-button>
        </div>
      </template>
      <el-form :model="shopForm" label-width="120px" style="max-width: 800px">
        <el-divider content-position="left">基本信息</el-divider>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="店铺名称">
              <el-input v-model="shopForm.shopName" placeholder="请输入店铺名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="店铺编码">
              <el-input v-model="shopForm.shopCode" placeholder="请输入店铺编码" :disabled="true" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="店铺类型">
              <el-select v-model="shopForm.shopType" style="width: 100%">
                <el-option v-for="item in shopTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="营业执照">
              <el-input v-model="shopForm.businessLicense" placeholder="请输入营业执照编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="法人代表">
              <el-input v-model="shopForm.legalPerson" placeholder="请输入法人代表" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话">
              <el-input v-model="shopForm.contactPhone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系邮箱">
              <el-input v-model="shopForm.contactEmail" placeholder="请输入联系邮箱" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="店铺描述">
              <el-input v-model="shopForm.description" placeholder="请输入店铺描述" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="left">地址信息</el-divider>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="省份">
              <el-input v-model="shopForm.province" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="城市">
              <el-input v-model="shopForm.city" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="区县">
              <el-input v-model="shopForm.district" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="详细地址">
              <el-input v-model="shopForm.address" type="textarea" :rows="2" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="经度">
              <el-input-number v-model="shopForm.longitude" :precision="6" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="纬度">
              <el-input-number v-model="shopForm.latitude" :precision="6" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="left">营业时间</el-divider>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="营业时间">
              <el-time-picker v-model="shopForm.openTime" format="HH:mm" value-format="HH:mm" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="打烊时间">
              <el-time-picker v-model="shopForm.closeTime" format="HH:mm" value-format="HH:mm" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="left">店铺图片</el-divider>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="店铺图片">
              <el-upload action="#" list-type="picture-card" :auto-upload="false" :on-change="handleUpload">
                <el-icon><Upload /></el-icon>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.setting-container { padding: 20px; }
.card-header { display: flex; justify-content: space-between; align-items: center; }
.el-divider { margin: 20px 0; }
</style>