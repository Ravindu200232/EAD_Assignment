import client from './client'

// Checks the text read from a QR code. Returns { canComplete, message, reservation, ... }.
export async function verifyQrCode(payload) {
  const { data } = await client.post('/checkin/verify', { payload })
  return data
}

// Marks the booking as completed with the energy that was actually delivered.
export async function completeTransfer(reservationId, payload, deliveredKwh) {
  const { data } = await client.post(`/checkin/${encodeURIComponent(reservationId)}/complete`, { payload, deliveredKwh })
  return data
}
