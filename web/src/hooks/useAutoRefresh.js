import { useEffect, useEffectEvent } from 'react'

// Runs `refresh` every `intervalMs` while the browser tab is visible.
export function useAutoRefresh(refresh, intervalMs = 30000) {
  const onTick = useEffectEvent(() => refresh())

  useEffect(() => {
    // Refreshes only when someone can see the page.
    const tick = () => {
      if (document.visibilityState === 'visible') onTick()
    }
    const timer = setInterval(tick, intervalMs)
    document.addEventListener('visibilitychange', tick)
    return () => {
      clearInterval(timer)
      document.removeEventListener('visibilitychange', tick)
    }
  }, [intervalMs])
}
