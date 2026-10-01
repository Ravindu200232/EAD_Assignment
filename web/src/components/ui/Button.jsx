import { Link } from 'react-router'
import Spinner from './Spinner'
import { buttonClasses } from './styles'

// Button with optional icons and a loading state.
export default function Button({
  variant,
  size,
  fullWidth,
  icon: Icon,
  iconRight: IconRight,
  loading = false,
  to,
  type = 'button',
  disabled,
  className,
  children,
  ...rest
}) {
  const classes = buttonClasses({ variant, size, fullWidth, className })
  const content = (
    <>
      {loading ? <Spinner size="sm" /> : Icon && <Icon aria-hidden="true" />}
      {children}
      {IconRight && <IconRight aria-hidden="true" />}
    </>
  )

  if (to) {
    return (
      <Link to={to} className={classes} {...rest}>
        {content}
      </Link>
    )
  }

  return (
    <button type={type} className={classes} disabled={disabled || loading} aria-busy={loading || undefined} {...rest}>
      {content}
    </button>
  )
}
