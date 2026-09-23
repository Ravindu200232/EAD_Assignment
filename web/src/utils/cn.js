// Returns one class string, e.g. cn('a', isOn && 'b') -> 'a b'.
export function cn(...classes) {
  return classes.filter(Boolean).join(' ')
}
