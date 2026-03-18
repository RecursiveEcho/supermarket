const isTrue = (val) => String(val).toLowerCase() === 'true'

export function withMock(realFn, mockFn, options = {}) {
  const useMock = isTrue(import.meta.env.VITE_USE_MOCK) || options.forceMock === true
  const fallbackMock = import.meta.env.VITE_FALLBACK_MOCK == null
    ? true
    : isTrue(import.meta.env.VITE_FALLBACK_MOCK)

  return async (...args) => {
    if (useMock) return await mockFn(...args)

    try {
      return await realFn(...args)
    } catch (e) {
      if (!fallbackMock) throw e
      console.warn('[withMock] real api failed, fallback to mock:', e?.message || e)
      return await mockFn(...args)
    }
  }
}

