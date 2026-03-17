import request from '@/utils/request'

// 获取商品列表
export function getProductList(params) {
  return request({
    url: '/product/list',
    method: 'get',
    params
  })
}

// 获取商品详情
export function getProductDetail(id) {
  return request({
    url: `/product/detail/${id}`,
    method: 'get'
  })
}

// 保存商品（新增/编辑）
export function saveProduct(data) {
  return request({
    url: '/product/save',
    method: 'post',
    data
  })
}

// 删除商品
export function deleteProduct(id) {
  return request({
    url: `/product/delete/${id}`,
    method: 'delete'
  })
}
