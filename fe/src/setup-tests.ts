// jsdom kennt weder matchMedia noch ResizeObserver; PrimeNG benutzt beides
// (Kontextmenue und Tabs).
Object.defineProperty(window, 'matchMedia', {
  writable: true,
  value: (query: string) => ({
    matches: false,
    media: query,
    onchange: null,
    addListener: () => {},
    removeListener: () => {},
    addEventListener: () => {},
    removeEventListener: () => {},
    dispatchEvent: () => false,
  }),
});

Object.defineProperty(window, 'ResizeObserver', {
  writable: true,
  value: class {
    observe() {}
    unobserve() {}
    disconnect() {}
  },
});
