<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const features = ref([
  { title: '商品管理', desc: '智能管理各类商品库存', icon: '📦', path: '/product/fruit' },
  { title: '会员管理', desc: '完善的会员服务体系', icon: '👤', path: '/member/list' },
  { title: '数据分析', desc: '实时掌握经营数据', icon: '📊', path: '/shop/data' },
  { title: '员工管理', desc: '高效的员工排班系统', icon: '👥', path: '/clerk/info' },
  { title: '客户管理', desc: '客户信息与权限管理', icon: '👥', path: '/customer/list' }
])

const banners = ref([])

onMounted(() => {
  const images = import.meta.glob('/public/images/banner*.jpg', { eager: true })
  const bannerList = Object.keys(images)
    .filter(path => {
      const match = path.match(/banner(\d+)/)
      const num = match ? parseInt(match[1]) : 0
      return num >= 1 && num <= 9
    })
    .map((path) => ({
      image: path.replace('/public', '')
    }))
  bannerList.sort((a, b) => {
    const numA = parseInt(a.image.match(/banner(\d+)/)?.[1] || 0)
    const numB = parseInt(b.image.match(/banner(\d+)/)?.[1] || 0)
    return numA - numB
  })
  banners.value = bannerList
})

const navigateTo = (path) => {
  router.push(path)
}
</script>

<template>
  <div class="home-container">
    <div class="carousel-wrapper">
      <el-carousel :interval="2000" height="450px" :autoplay="true" arrow="always" indicator-position="none" :pause-on-hover="false">
        <el-carousel-item v-for="(banner, index) in banners" :key="index">
          <div class="carousel-item">
            <img :src="banner.image" alt="轮播图" />
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>

    <div class="features-section">
      <h2 class="section-title">核心功能</h2>
      <el-row :gutter="20" justify="center">
        <el-col :span="24/5" v-for="(feature, index) in features" :key="index">
          <el-card class="feature-card" shadow="hover" @click="navigateTo(feature.path)">
            <div class="feature-icon">{{ feature.icon }}</div>
            <h3>{{ feature.title }}</h3>
            <p>{{ feature.desc }}</p>
            <div class="feature-link">点击进入 →</div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<style scoped>
.home-container {
  padding: 0 20px 20px 20px;
  background: #f5f7fa;
  min-height: calc(100vh - 60px);
}

.carousel-wrapper {
  width: calc(100% + 40px);
  margin-left: -20px;
  margin-right: -20px;
  overflow: hidden;
  background: url('/images/banner10.jpg') center/cover no-repeat;
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  position: relative;
}

.carousel-wrapper::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.1);
  pointer-events: none;
  border-radius: 12px;
}

.carousel-item {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  background: transparent;
}

.carousel-item img {
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
  object-fit: contain;
  display: block;
}

.el-carousel {
  border-radius: 12px;
  overflow: hidden;
  box-shadow: none;
}

.features-section {
  width: calc(100% + 40px);
  margin-left: -20px;
  margin-right: -20px;
  margin-bottom: -20px;
  padding: 60px 40px;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  box-shadow: 0 -8px 16px rgba(0, 0, 0, 0.15);
  position: relative;
  margin-top: 30px;
  border-radius: 12px 12px 0 0;
}

.features-section::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image:
    radial-gradient(circle at 20% 50%, rgba(212, 175, 55, 0.05) 0%, transparent 50%),
    radial-gradient(circle at 80% 80%, rgba(212, 175, 55, 0.05) 0%, transparent 50%);
  pointer-events: none;
}

.section-title {
  text-align: center;
  font-size: clamp(24px, 4vw, 32px);
  color: #2c3e50;
  margin-bottom: 50px;
  font-weight: 600;
  position: relative;
  z-index: 1;
}

.feature-card {
  border-radius: 16px;
  text-align: center;
  padding: clamp(15px, 2vw, 30px) clamp(10px, 1.5vw, 15px);
  transition: all 0.3s;
  margin-bottom: 0;
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(5px);
  border: 2px solid rgba(212, 175, 55, 0.3);
  cursor: pointer;
  position: relative;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.feature-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 16px 32px rgba(212, 175, 55, 0.3);
  border-color: #D4AF37;
  background: rgba(255, 255, 255, 0.95);
}

.feature-card:active {
  transform: translateY(-5px) scale(0.98);
}

.feature-icon {
  font-size: clamp(40px, 4vw, 56px);
  margin-bottom: 15px;
  transition: transform 0.3s;
}

.feature-card:hover .feature-icon {
  transform: scale(1.2);
}

.feature-card h3 {
  color: #2c3e50;
  font-size: clamp(16px, 2vw, 20px);
  margin-bottom: 12px;
  font-weight: 600;
}

.feature-card p {
  color: #555;
  font-size: clamp(12px, 1.5vw, 14px);
  line-height: 1.6;
  margin-bottom: 12px;
}

.feature-link {
  color: #D4AF37;
  font-size: 13px;
  font-weight: 500;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.3s;
}

.feature-card:hover .feature-link {
  opacity: 1;
  transform: translateY(0);
}

/* 响应式布局 */
@media (max-width: 1200px) {
  .feature-card {
    padding: 20px 15px;
  }
}

@media (max-width: 768px) {
  .home-container {
    padding: 0 10px 10px 10px;
  }

  .carousel-wrapper {
    width: calc(100% + 20px);
    margin-left: -10px;
    margin-right: -10px;
  }

  .el-carousel {
    height: 350px;
  }

  .features-section {
    width: calc(100% + 20px);
    margin-left: -10px;
    margin-right: -10px;
    margin-bottom: -10px;
    padding: 40px 20px;
  }

  .feature-card {
    padding: 20px 15px;
  }
}
</style>