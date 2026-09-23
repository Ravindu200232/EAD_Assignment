import Alert from './Alert'

// Shows nothing when there is no error.
export default function FormAlert({ error, fieldErrors = {} }) {
  if (!error) return null

  const hasFieldErrors = Object.keys(fieldErrors).length > 0
  return <Alert tone="danger">{hasFieldErrors ? 'Please check the highlighted fields.' : error}</Alert>
}
