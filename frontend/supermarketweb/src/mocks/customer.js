export const mockCustomers = [
  {
    id: 1,
    userName: 'cust001',
    name: '李先生',
    age: 30,
    gender: '男',
    phone: '13800138001',
    address: '南宁市青秀区民族大道 123 号',
    createTime: '2026-03-01 10:00:00',
    updateTime: '2026-03-10 12:00:00'
  },
  {
    id: 2,
    userName: 'cust002',
    name: '王女士',
    age: 26,
    gender: '女',
    phone: '13800138002',
    address: '南宁市西乡塘区大学路 88 号',
    createTime: '2026-03-02 11:00:00',
    updateTime: '2026-03-11 12:00:00'
  }
]

export function mockPage(list, pageNum = 1, pageSize = 10) {
  const pn = Number(pageNum) || 1
  const ps = Number(pageSize) || 10
  const total = list.length
  const start = (pn - 1) * ps
  const end = start + ps
  const slice = list.slice(start, end)
  const pages = Math.max(1, Math.ceil(total / ps))
  return {
    pageNum: pn,
    pageSize: ps,
    total,
    pages,
    hasPrevious: pn > 1,
    hasNext: pn < pages,
    list: slice
  }
}

export function mockFilterCustomers(query = {}) {
  const { userName, name, phone, gender } = query || {}
  return mockCustomers.filter(c => {
    const okUser = !userName || String(c.userName || '').includes(String(userName))
    const okName = !name || String(c.name || '').includes(String(name))
    const okPhone = !phone || String(c.phone || '').includes(String(phone))
    const okGender = !gender || String(c.gender || '') === String(gender)
    return okUser && okName && okPhone && okGender
  })
}

