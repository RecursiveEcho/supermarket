import request from '@/utils/request'
import { withMock } from '@/utils/withMock'
import { pageCheckMock, pageInMock, pageOutMock, pageStockMock, pageWarningMock } from '@/mocks/inventory'

// 这些接口路径是“约定”，你后端按同路径实现即可自动有数据
const _pageStock = (params) => request({ url: '/inventory/stock/page', method: 'get', params })
const _pageIn = (params) => request({ url: '/inventory/in/page', method: 'get', params })
const _pageOut = (params) => request({ url: '/inventory/out/page', method: 'get', params })
const _pageCheck = (params) => request({ url: '/inventory/check/page', method: 'get', params })
const _pageWarning = (params) => request({ url: '/inventory/warning/page', method: 'get', params })

export const pageStock = withMock(_pageStock, async (params) => pageStockMock(params))
export const pageIn = withMock(_pageIn, async (params) => pageInMock(params))
export const pageOut = withMock(_pageOut, async (params) => pageOutMock(params))
export const pageCheck = withMock(_pageCheck, async (params) => pageCheckMock(params))
export const pageWarning = withMock(_pageWarning, async (params) => pageWarningMock(params))

