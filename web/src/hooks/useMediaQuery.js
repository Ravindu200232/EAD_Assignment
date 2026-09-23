import { useCallback, useSyncExternalStore } from 'react'

// Returns true while the media query matches.
export function useMediaQuery(query) {
  // Tells React when the query result changes.
  const subscribe = useCallback(
    (onChange) => {
      const list = window.matchMedia(query)
      list.addEventListener('change', onChange)
      return () => list.removeEventListener('change', onChange)
    },
    [query],
  )

  return useSyncExternalStore(subscribe, () => window.matchMedia(query).matches)
}
