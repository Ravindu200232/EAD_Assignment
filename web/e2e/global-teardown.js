import { MongoClient } from 'mongodb'

// Runs once after all tests.
export default async function globalTeardown() {
  const name = process.env.E2E_DB_NAME
  if (!name?.startsWith('SolarGridDb_WebE2E_')) return

  if (process.env.E2E_KEEP_DB) {
    console.log(`Keeping test database ${name}`)
    return
  }

  const client = new MongoClient(process.env.E2E_MONGO_URL ?? 'mongodb://localhost:27017', {
    serverSelectionTimeoutMS: 5000,
  })
  try {
    await client.connect()
    await client.db(name).dropDatabase()
    console.log(`Removed test database ${name}`)
  } finally {
    await client.close()
  }
}
