import request from '@/utils/request'

export function pageProduct(params) {
  return request({
    url: '/product/pageProduct',
    method: 'get',
    params
  })
}

export function getProduct(id) {
  return request({
    url: `/product/getProduct/${id}`,
    method: 'get'
  })
}

export function addProduct(data) {
  return request({
    url: '/product/addProduct',
    method: 'post',
    data
  })
}

export function updateProduct(data) {
  return request({
    url: '/product/updateProduct',
    method: 'put',
    data
  })
}

export function deleteProduct(id) {
  return request({
    url: `/product/deleteProduct/${id}`,
    method: 'delete'
  })
}

export function queryProduct(data) {
  return request({
    url: '/product/queryProduct',
    method: 'post',
    data
  })
}

export function saveProduct(data) {
  return data?.id ? updateProduct(data) : addProduct(data)
}
