import request from '@/utils/request'

export function pageMember(params) {
  return request({
    url: '/member/getMemberByPage',
    method: 'get',
    params
  })
}

export function getMember(id) {
  return request({
    url: `/member/getMember/${id}`,
    method: 'get'
  })
}

export function addMember(data) {
  return request({
    url: '/member/addMember',
    method: 'post',
    data
  })
}

export function updateMember(data) {
  return request({
    url: '/member/updateMember',
    method: 'put',
    data
  })
}

export function deleteMember(id) {
  return request({
    url: `/member/deleteMember/${id}`,
    method: 'delete'
  })
}

export function queryMember(data) {
  return request({
    url: '/member/queryMember',
    method: 'post',
    data
  })
}

export function saveMember(data) {
  return data?.id ? updateMember(data) : addMember(data)
}
