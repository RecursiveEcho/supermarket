## 前端启动

```bash
cd frontend/supermarketweb
npm install
npm run dev
```

## 对接后端的约定（你写完后端即可有数据）

- **请求前缀**：前端统一请求 `/api/...`
- **开发代理**：`vite.config.js` 会把 `/api` 代理到 `http://localhost:8081`，并且转发时会去掉 `/api` 前缀
  - 例：前端请求 `/api/customer/getCustomerByPage` 实际打到后端 `/customer/getCustomerByPage`
- **后端返回结构**（前端已按此解析）：

```json
{ "code": 1, "msg": "success", "data": {} }
```

## Mock 机制（不影响后端）

- 默认行为：**优先请求真实接口**；如果接口暂时不可用，会**自动回退到本地 Mock**，保证页面能跑。
- 可通过环境变量控制（见 `.env.example`）：
  - **VITE_USE_MOCK**：强制只用 Mock
  - **VITE_FALLBACK_MOCK**：真实接口失败时是否回退 Mock（默认 true）
