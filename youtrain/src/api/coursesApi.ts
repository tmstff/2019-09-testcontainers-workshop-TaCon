/**
 * The REST API for course and courseDate.
 *
 * @author Mark Vz
 */

import axios from 'axios'
import dateFns from 'date-fns'
import { truncate } from 'lodash'

function err500(err: any) {
  // TODO: implement!
}

function buildQueryString(options: any = {}) {
  const { searchText, beginDate, endDate } = options
  const searchTextQuery = searchText ? `q=${searchText}` : null
  const beginDateQuery = beginDate ? `begin=${beginDate}` : null
  const endDateQuery = endDate ? `end=${endDate}` : null
  const subQueries = [searchTextQuery, beginDateQuery, endDateQuery].filter(
    q => q != null
  )
  const allSubQueries = subQueries.join('&')
  return allSubQueries ? `?${allSubQueries}` : ''
}

/**
 * Returns all found courses.
 */
async function findAll(options: any = {}): Promise<TaCourse[]> {
  const queryString = buildQueryString(options)
  try {
    const response = await axios.get(`/api/courses${queryString}`)
    const courseEntities: TaCourseEntity[] = response.data
    const courses: TaCourse[] = courseEntities.map(courseEntity => ({
      ...courseEntity,
      // Truncate the description since it may be too long for the data table.
      // TODO: move the truncation to the server-side to reduce sent payload
      description: truncate(courseEntity.description, { length: 40 }),
    }))
    return courses
  } catch (err) {
    err500(err)
    return Promise.resolve([])
  }
}

/**
 * Returns the course with id.
 */
async function findById(id: string): Promise<TaCourse | null> {
  try {
    const response = await axios.get(`/api/courses/${id}`)
    const courseEntity: TaCourseEntity = response.data
    const course: TaCourse = {
      ...courseEntity,
    }
    return course
  } catch (err) {
    err500(err)
    return Promise.resolve(null)
  }
}

/**
 * Creates the course.
 * Returns the created course.
 */
async function create(course: TaCourse): Promise<TaCourseEntity | null> {
  try {
    const response = await axios.post(`/api/courses`, { ...course })
    const courseEntity: TaCourseEntity = response.data
    return courseEntity
  } catch (err) {
    err500(err)
    return Promise.resolve(null)
  }
}

/**
 * Updates the course.
 * Returns the updated course.
 */
async function update(course: TaCourse): Promise<TaCourseEntity | null> {
  try {
    const id = course.id
    const response = await axios.patch(`/api/courses/${id}`, { ...course })
    const courseEntity: TaCourseEntity = response.data
    return courseEntity
  } catch (err) {
    err500(err)
    return Promise.resolve(null)
  }
}

/**
 * Deletes the course.
 * Returns true if deletion was successful; false otherwise.
 */
async function deleteById(courseId: number): Promise<boolean> {
  try {
    const response = await axios.delete(`/api/courses/${courseId}`)
    return response.data
  } catch (err) {
    err500(err)
    return Promise.resolve(false)
  }
}

/**
 * Books the course (actually the course date) for the given `courseDate`.
 * Returns the updated courseDate.
 */
async function book(
  courseDate: TaCourseDate
): Promise<TaCourseDateEntity | null> {
  if (courseDate.amount > 0) {
    try {
      const response = await axios.put(
        `/api/course-dates/${courseDate.id}/book`
      )
      const courseDateEntity: TaCourseDateEntity = response.data
      return courseDateEntity
    } catch (err) {
      err500(err)
      return Promise.resolve(null)
    }
  } else {
    throw new Error('Not allowed. Course is already fully booked.')
  }
}

export default {
  findAll,
  findById,
  create,
  update,
  deleteById,
  book,
}
