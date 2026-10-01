import { useEffect } from 'react'

export const APP_NAME = 'Smart Solar Microgrid'

// Sets "<page> · Smart Solar Microgrid" as the tab title.
export function useDocumentTitle(title) {
  useEffect(() => {
    document.title = title ? `${title} · ${APP_NAME}` : APP_NAME
  }, [title])
}
