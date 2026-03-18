import request from '@/utils/request'
import { withMock } from '@/utils/withMock'
import { mockFilterCustomers, mockPage } from '@/mocks/customer'

const _pageCustomer = (params) =>
  request({
    url: '/customer/getCustomerByPage',
    method: 'get',
    params
  })

const _getCustomer = (id) =>
  request({
    url: `/customer/getCustomer/${id}`,
    method: 'get'
  })

const _addCustomer = (data) =>
  request({
    url: '/customer/addCustomer',
    method: 'post',
    data
  })

const _updateCustomer = (data) =>
  request({
    url: '/customer/updateCustomer',
    method: 'put',
    data
  })

const _deleteCustomer = (id) =>
  request({
    url: `/customer/deleteCustomer/${id}`,
    method: 'delete'
  })

const _queryCustomer = (data) =>
  request({
    url: '/customer/queryCustomer',
    method: 'post',
    data
  })

export const pageCustomer = withMock(
  _pageCustomer,
  async (params = {}) => mockPage(mockFilterCustomers({}), params.pageNum, params.pageSize)
)

export const getCustomer = withMock(
  _getCustomer,
  async (id) => mockFilterCustomers({}).find(c => Number(c.id) === Number(id)) || null
)

export const addCustomer = withMock(
  _addCustomer,
  async () => null
)

export const updateCustomer = withMock(
  _updateCustomer,
  async () => null
)

export const deleteCustomer = withMock(
  _deleteCustomer,
  async () => null
)

export const queryCustomer = withMock(
  _queryCustomer,
  async (data = {}) => mockFilterCustomers(data)
)

export function saveCustomer(data) {
  return data?.id ? updateCustomer(data) : addCustomer(data)
}

