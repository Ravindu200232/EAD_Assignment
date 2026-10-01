import { Suspense, lazy } from 'react'
import MapErrorBoundary from './MapErrorBoundary'
import MapNotice from './MapNotice'
import { useMapProvider } from './mapProvider'

const GooglePickerMap = lazy(() => import('./GooglePickerMap'))
const LeafletPickerMap = lazy(() => import('./LeafletPickerMap'))

// Location map: { lat, lng, onPick, label }.
export default function PickerMap(props) {
  const { provider, notice } = useMapProvider()
  const openStreetMap = <LeafletPickerMap {...props} />

  return (
    <div>
      <MapNotice text={notice} />
      <Suspense fallback={<div aria-hidden="true" className="h-80 animate-pulse rounded-tile bg-well" />}>
        {provider === 'google' ? (
          <MapErrorBoundary fallback={openStreetMap}>
            <GooglePickerMap {...props} />
          </MapErrorBoundary>
        ) : (
          openStreetMap
        )}
      </Suspense>
    </div>
  )
}
