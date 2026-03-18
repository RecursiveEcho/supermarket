import { mockPage } from './customer'

export const mockStock = [
  {
    id: 1,
    productCode: 'FG001',
    productName: '红富士苹果',
    categoryId: 1,
    categoryName: '果蔬',
    storeId: 1,
    storeName: '邕城百货总店',
    stockQuantity: 500,
    warningStock: 100,
    unit: 'kg',
    lastUpdateTime: '2026-03-10 10:30:00'
  }
]

export const mockInRecords = [
  { id: 1, billNo: 'IN20260310001', productCode: 'FG001', productName: '红富士苹果', quantity: 100, operator: '管理员', createTime: '2026-03-10 11:00:00', remark: '' }
]

export const mockOutRecords = [
  { id: 1, billNo: 'OUT20260311001', productCode: 'FG001', productName: '红富士苹果', quantity: 20, operator: '管理员', createTime: '2026-03-11 09:20:00', remark: '' }
]

export const mockCheckRecords = [
  { id: 1, billNo: 'CHK20260312001', productCode: 'FG001', productName: '红富士苹果', stockBefore: 500, stockAfter: 480, operator: '管理员', createTime: '2026-03-12 14:10:00', remark: '' }
]

export const mockWarnings = [
  { id: 1, productCode: 'FG001', productName: '红富士苹果', stockQuantity: 80, warningStock: 100, storeName: '邕城百货总店', lastUpdateTime: '2026-03-12 15:10:00' }
]

export const pageStockMock = (params = {}) => mockPage(mockStock, params.pageNum, params.pageSize)
export const pageInMock = (params = {}) => mockPage(mockInRecords, params.pageNum, params.pageSize)
export const pageOutMock = (params = {}) => mockPage(mockOutRecords, params.pageNum, params.pageSize)
export const pageCheckMock = (params = {}) => mockPage(mockCheckRecords, params.pageNum, params.pageSize)
export const pageWarningMock = (params = {}) => mockPage(mockWarnings, params.pageNum, params.pageSize)

