// 导入 Vite 核心函数和 Vue 插件
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// 用于处理路径别名（比如 @ 指向 src 目录）
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  // 必须的 Vue 插件，没有这个无法运行 Vue 项目
  plugins: [vue()],

  // 路径别名配置（新手高频需求）
  resolve: {
    alias: {
      // 把 @ 指向项目根目录下的 src 文件夹
      // 比如在组件里可以用 import xxx from '@/components/xxx.vue'
      '@': path.resolve(__dirname, './src')
    }
  },

  // 开发服务器配置（启动项目时的端口、自动打开浏览器等）
  server: {
    // 自定义启动端口（默认是 5173，可改成 8080/3000 等）
    port: 8080,
    // 启动项目后自动打开浏览器（不用手动复制地址）
    open: true,
    // 允许局域网访问（比如手机/其他电脑访问你的 Mac 地址）
    host: '0.0.0.0',
    // 接口代理（对接后端时用，解决跨域问题）
    proxy: {
      // 示例：把 /api 开头的请求代理到后端接口
      '/api': {
        target: 'http://localhost:3000', // 替换成你的后端地址
        changeOrigin: true, // 开启跨域
        rewrite: (path) => path.replace(/^\/api/, '') // 去掉请求里的 /api 前缀
      }
    }
  },

  // 打包配置（上线时用）
  build: {
    // 打包输出目录（默认是 dist，可自定义）
    outDir: 'dist',
    // 压缩代码（提升加载速度）
    minify: 'esbuild'
  }
})