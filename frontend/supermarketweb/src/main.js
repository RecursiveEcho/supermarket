import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from '@/App.vue'
import router from './router'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)

// 添加全局样式，清除默认边距
const style = document.createElement('style')
style.textContent = `
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  body {
    margin: 0;
    padding: 0;
  }
  #app {
    width: 100%;
    height: 100vh;
  }
`
document.head.appendChild(style)

app.mount('#app')
