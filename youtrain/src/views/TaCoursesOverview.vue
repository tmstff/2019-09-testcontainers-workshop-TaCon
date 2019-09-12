<template>
  <v-container fluid grid-list-md id="courses">
    <ta-course-edit
      :key="courseToEdit ? courseToEdit.id : 'new'"
      :course="courseToEdit"
      v-model="showDialog"
      @create="createCourse"
      @update="updateCourse"
      @delete="deleteCourse"
    />
    <v-layout row wrap>
      <v-flex xs12>
        <v-card>
          <v-card-text>
            <ta-search-field v-model="searchText" label="Search"/>
            <v-flex xl2 md4 xs12>
              <ta-date-picker v-model="beginDate" label="Begin date"/>
              <ta-date-picker v-model="endDate" label="End date"/>
            </v-flex>
          </v-card-text>
        </v-card>
      </v-flex>
      <ta-courses
        :courses="courses"
        :loading="loading"
        :handle-details-clicked="showCourse"
        :handle-create-clicked="openCreateCourse"
        :handle-edit-clicked="openEditCourse"
        :handle-book-clicked="bookCourse"
        :search-range-begin="beginDateForSearchRange"
        :search-range-end="endDateForSearchRange"
      />
    </v-layout>
  </v-container>
</template>

<script>
import Vue from 'vue'
import dateFns from 'date-fns'
import TaCourses from '@/components/courses/TaCourses'
import TaCourseEdit from '@/components/course/TaCourseEdit'
import TaDatePicker from '@/components/common/TaDatePicker'
import TaSearchField from '@/components/common/TaSearchField'
import coursesApi from '@/api/coursesApi'

export default Vue.extend({
  name: 'TaCoursesOverview',
  components: {
    TaCourses,
    TaCourseEdit,
    TaSearchField,
    TaDatePicker,
  },
  data() {
    return {
      courses: undefined,
      courseToEdit: undefined,
      showDialog: false,
      loading: false,
      searchText: null,
      beginDate: null,
      endDate: null,
    }
  },
  computed: {
    beginDateForSearchRange() {
      return dateFns.parse(this.beginDate)
    },
    endDateForSearchRange() {
      return dateFns.parse(this.endDate)
    },
  },
  methods: {
    fetchCourses() {
      this.loading = true
      coursesApi
        .findAll({
          searchText: this.searchText,
          beginDate: this.beginDate,
          endDate: this.endDate,
        })
        .then(courses => (this.courses = courses))
        .then(() => (this.loading = false))
    },
    showCourse(course) {
      this.$router.push(`/courses/${course.id}`)
    },
    openCreateCourse(course) {
      this.courseToEdit = null
      this.showDialog = true
    },
    openEditCourse(course) {
      coursesApi.findById(course.id).then(c => {
        this.courseToEdit = c
        this.showDialog = true
      })
    },
    createCourse(updatedCourse) {
      coursesApi.create(updatedCourse).then(() => this.fetchCourses())
    },
    updateCourse(updatedCourse) {
      coursesApi.update(updatedCourse).then(() => this.fetchCourses())
    },
    deleteCourse(courseId) {
      coursesApi.deleteById(courseId).then(() => this.fetchCourses())
    },
    bookCourse(courseDate) {
      coursesApi
        .book(courseDate)
        .then(() => this.fetchCourses())
        .catch(err => alert(err))
    },
  },
  created() {
    this.fetchCourses()
  },
  watch: {
    searchText(newVal) {
      this.fetchCourses()
    },
    beginDate(newVal) {
      this.fetchCourses()
    },
    endDate(newVal) {
      this.fetchCourses()
    },
  },
})
</script>
