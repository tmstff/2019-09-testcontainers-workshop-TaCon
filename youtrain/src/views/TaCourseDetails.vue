<template>
  <v-container fluid grid-list-md>
    <v-layout row wrap>
      <ta-course :course="course" :handle-book-clicked="bookCourse"/>
    </v-layout>
  </v-container>
</template>

<script>
import Vue from 'vue'
import TaCourse from '@/components/course/TaCourse.vue'
import coursesApi from '@/api/coursesApi'

export default Vue.extend({
  name: 'TaCourseDetails',
  components: {
    TaCourse,
  },
  props: {
    courseId: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      course: null,
    }
  },
  methods: {
    bookCourse(courseDate) {
      coursesApi
        .book(courseDate)
        .then(updatedCourseDate => {
          this.course.courseDates.find(
            cd => cd.id === updatedCourseDate.id
          ).amount = updatedCourseDate.amount
        })
        .catch(err => alert(err))
    },
  },
  created() {
    coursesApi.findById(this.courseId).then(course => {
      this.course = course
    })
  },
})
</script>
