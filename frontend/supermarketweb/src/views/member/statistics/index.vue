<template>
  <div class="container">
    <el-row :gutter="16" class="cards">
      <el-col :xs="24" :sm="8">
        <el-card shadow="never">
          <div class="card-title">会员总数</div>
          <el-statistic :value="totalMembers" />
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="8">
        <el-card shadow="never">
          <div class="card-title">男性 / 女性</div>
          <div class="kv">
            <span>男 {{ genderStats.male }}</span>
            <span>女 {{ genderStats.female }}</span>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="8">
        <el-card shadow="never">
          <div class="card-title">平均年龄</div>
          <el-statistic :value="avgAge" :precision="1" />
        </el-card>
      </el-col>
    </el-row>

    <el-card shadow="never" class="table-card">
      <template #header>
        <div class="table-header">
          <span>按等级统计</span>
          <el-button :loading="loading" @click="loadData">刷新</el-button>
        </div>
      </template>

      <el-table :data="levelRows" border stripe>
        <el-table-column prop="level" label="会员等级" />
        <el-table-column prop="count" label="人数" width="120" />
        <el-table-column label="占比">
          <template #default="{ row }">
            <el-progress :percentage="row.percent" :stroke-width="10" />
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { pageMember } from '@/api/member'

const loading = ref(false)
const memberList = ref([])

const loadData = async () => {
  loading.value = true
  try {
    // 取较大的 pageSize，便于统计（如果后端数据很多，后续可以改成专门的统计接口）
    const res = await pageMember({ pageNum: 1, pageSize: 10000 })
    memberList.value = res?.list || []
  } finally {
    loading.value = false
  }
}

const totalMembers = computed(() => memberList.value.length)

const genderStats = computed(() => {
  let male = 0
  let female = 0
  for (const m of memberList.value) {
    if (m?.gender === '男') male += 1
    else if (m?.gender === '女') female += 1
  }
  return { male, female }
})

const avgAge = computed(() => {
  const ages = memberList.value.map(m => Number(m?.age)).filter(n => Number.isFinite(n) && n >= 0)
  if (!ages.length) return 0
  return ages.reduce((a, b) => a + b, 0) / ages.length
})

const levelRows = computed(() => {
  const map = new Map()
  for (const m of memberList.value) {
    const level = m?.level || '未设置'
    map.set(level, (map.get(level) || 0) + 1)
  }
  const total = totalMembers.value || 1
  return Array.from(map.entries())
    .map(([level, count]) => ({
      level,
      count,
      percent: Math.round((count / total) * 1000) / 10
    }))
    .sort((a, b) => b.count - a.count)
})

onMounted(loadData)
</script>

<style scoped>
.container { padding: 20px; }
.cards { margin-bottom: 16px; }
.card-title { font-size: 14px; color: #606266; margin-bottom: 8px; }
.kv { display: flex; gap: 16px; color: #303133; font-size: 16px; }
.table-card { min-height: 300px; }
.table-header { display: flex; align-items: center; justify-content: space-between; }
</style>
