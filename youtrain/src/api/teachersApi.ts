import axios from 'axios'

function err500(err: any) {
  // TODO: implement!
}

/**
 * Returns all found courses.
 */
async function findAll(options: any = {}): Promise<string[]> {
  try {
    const response = await axios.get(`/api/teachers`)
    return response.data
  } catch (err) {
    err500(err)
    return Promise.resolve([])
  }
}

export default {
  findAll
}
