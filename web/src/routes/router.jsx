import { createHashRouter } from 'react-router'
import { PageLoader } from '../components/ui/Spinner'
import AppLayout from '../layouts/AppLayout'
import AuthLayout from '../layouts/AuthLayout'
import PublicLayout from '../layouts/PublicLayout'
import { NavBadgesProvider } from '../context/NavBadgesContext'
import RouteErrorPage from '../pages/errors/RouteErrorPage'
import { BACKOFFICE_ONLY, Roles } from '../utils/roles'
import { RedirectIfSignedIn, RequireAuth, RequireRole } from './guards'

// Turns a dynamic import into a lazy route that renders the file's default export.
const page = (load) => async () => ({ Component: (await load()).default })

const initialLoader = <PageLoader fullScreen />

export const routes = [
  {
    element: <PublicLayout />,
    errorElement: <RouteErrorPage />,
    hydrateFallbackElement: initialLoader,
    children: [
      // Public home page.
      { index: true, lazy: page(() => import('../pages/home/HomePage')) },
      {
        path: 'login',
        element: (
          <RedirectIfSignedIn>
            <AuthLayout />
          </RedirectIfSignedIn>
        ),
        children: [{ index: true, lazy: page(() => import('../pages/auth/LoginPage')) }],
      },
    ],
  },
  {
    element: (
      <RequireAuth>
        <NavBadgesProvider>
          <AppLayout />
        </NavBadgesProvider>
      </RequireAuth>
    ),
    errorElement: <RouteErrorPage />,
    hydrateFallbackElement: initialLoader,
    children: [
      // Check-in, account and staff users.
      { path: 'check-in', lazy: page(() => import('../pages/checkin/CheckInPage')) },
      { path: 'account', lazy: page(() => import('../pages/account/AccountPage')) },
      {
        element: <RequireRole roles={BACKOFFICE_ONLY} />,
        children: [{ path: 'users', lazy: page(() => import('../pages/users/UsersPage')) }],
      },
      // Dashboards, prosumers and the activation queue.
      { path: 'prosumers', lazy: page(() => import('../pages/prosumers/ProsumersPage')) },
      { path: 'prosumers/:nic', lazy: page(() => import('../pages/prosumers/ProsumerDetailsPage')) },
      {
        element: <RequireRole roles={BACKOFFICE_ONLY} />,
        children: [
          { path: 'dashboard', lazy: page(() => import('../pages/dashboard/DashboardPage')) },
          { path: 'activations', lazy: page(() => import('../pages/prosumers/PendingActivationsPage')) },
        ],
      },
      {
        element: <RequireRole roles={[Roles.GridOperator]} />,
        children: [{ path: 'operations', lazy: page(() => import('../pages/dashboard/OperationsPage')) }],
      },
      // Stations, schedules, battery bays, slots and maps.
      { path: 'stations', lazy: page(() => import('../pages/stations/StationsPage')) },
      { path: 'stations/:id', lazy: page(() => import('../pages/stations/StationDetailsPage')) },
      {
        element: <RequireRole roles={BACKOFFICE_ONLY} />,
        children: [
          { path: 'stations/new', lazy: page(() => import('../pages/stations/StationFormPage')) },
          { path: 'stations/:id/edit', lazy: page(() => import('../pages/stations/StationFormPage')) },
        ],
      },
      // Reservations, the booking wizard and booking qr codes.
      { path: 'reservations', lazy: page(() => import('../pages/reservations/ReservationsPage')) },
      { path: 'reservations/new', lazy: page(() => import('../pages/reservations/BookingWizardPage')) },
      { path: 'reservations/:id', lazy: page(() => import('../pages/reservations/ReservationDetailsPage')) },
      { path: 'reservations/:id/edit', lazy: page(() => import('../pages/reservations/BookingWizardPage')) },
    ],
  },
  {
    path: '*',
    hydrateFallbackElement: initialLoader,
    lazy: page(() => import('../pages/errors/NotFoundPage')),
  },
]

// Creates the router used by the app (tests build their own with memory routers).
export function createAppRouter() {
  return createHashRouter(routes)
}
