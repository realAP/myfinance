import {setupZoneTestEnv} from 'jest-preset-angular/setup-env/zone';

setupZoneTestEnv();

// jsdom kennt matchMedia nicht, PrimeNG benutzt es (z. B. im Kontextmenue).
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

// Ebenso fehlt jsdom der ResizeObserver, den PrimeNGs Tabs benutzen.
Object.defineProperty(window, 'ResizeObserver', {
  writable: true,
  value: class {
    observe() {}
    unobserve() {}
    disconnect() {}
  },
});
