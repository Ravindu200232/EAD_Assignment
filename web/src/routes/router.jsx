import { Navigate, createHashRouter } from 'react-router'
import { PageLoader } from '../components/ui/Spinner'
import AppLayout from '../layouts/AppLayout'
import AuthLayout from '../layouts/AuthLayout'
import PublicLayout from '../layouts/PublicLayout'
import RouteErrorPage from '../pages/errors/RouteErrorPage'
import { BACKOFFICE_ONLY } from '../utils/roles'
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
      { index: true, element: <Navigate to="/login" replace /> },
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
        <AppLayout />
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
