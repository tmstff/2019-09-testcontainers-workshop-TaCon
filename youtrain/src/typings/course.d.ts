/**
 * Typings for entities and business models.
 *
 * @author Mark Vz
 */

interface TaCourseDateEntity {
  id: number
  courseId: number
  courseNo: string
  begin: string
  end: string
  amount: number
}

interface TaCourseDate {
  id: number
  courseNo: string
  begin: Date
  end: Date
  amount: number
}

interface TaCourseEntity {
  id: number
  title: string
  description: string
  teacher: string
  price: number
  courseDates: TaCourseDateEntity[]
}

interface TaCourse {
  id: number
  title: string
  description: string
  teacher: string
  price: number
  courseDates: TaCourseDateEntity[]
}
