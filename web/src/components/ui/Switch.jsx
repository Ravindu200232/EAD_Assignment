import { cn } from '../../utils/cn'

// Switch with a short visible word next to it.
export default function Switch({ checked, onChange, label, onText = 'On', offText = 'Off', disabled = false, className }) {
  return (
    <button
      type="button"
      role="switch"
      aria-checked={checked}
      aria-label={label}
      disabled={disabled}
      onClick={() => onChange(!checked)}
      className={cn(
        'inline-flex min-h-11 items-center gap-3 rounded-full pr-2 transition-all duration-200',
        'focus-visible:outline-none focus-visible:ring-4 focus-visible:ring-accent/30 disabled:cursor-not-allowed disabled:opacity-60',
        className,
      )}
    >
      <span
        aria-hidden="true"
        className={cn(
          'relative h-8 w-14 shrink-0 rounded-full shadow-clay-pressed-sm transition-colors duration-200',
          checked ? 'bg-emerald-400' : 'bg-well',
        )}
      >
        <span
          className={cn(
            'absolute top-1 left-1 size-6 rounded-full bg-white shadow-clay-button transition-transform duration-200',
            checked && 'translate-x-6',
          )}
        />
      </span>
      <span aria-hidden="true" className="font-heading text-sm font-bold text-ink">
        {checked ? onText : offText}
      </span>
    </button>
  )
}
