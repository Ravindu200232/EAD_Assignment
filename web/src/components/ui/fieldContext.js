import { createContext, useContext } from 'react'

export const FieldContext = createContext(null)

// Returns the props a control needs from its surrounding Field (or nothing).
export function useFieldProps() {
  const field = useContext(FieldContext)
  if (!field) return {}

  return {
    id: field.id,
    'aria-describedby': field.describedBy,
    'aria-invalid': field.invalid || undefined,
    required: field.required || undefined,
  }
}
