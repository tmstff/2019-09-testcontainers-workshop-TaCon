<template>
  <v-flex v-if="course" xs12>
    <v-btn to="/courses" flat icon color="grey">
      <v-icon>arrow_back</v-icon>
    </v-btn>
    <br>
    <br>
    <v-card flat>
      <h1>{{course.title}}</h1>
      <h3>By {{course.teacher}}</h3>
      <div class="description">
        <p>{{course.description}}</p>
      </div>
      <div>Price: {{numberUtils().formatCurrency(course.price)}}</div>
    </v-card>
    <div class="dates">
      <ta-course-dates :course-dates="course.courseDates" :handle-book-clicked="handleBookClicked"/>
    </div>
  </v-flex>
</template>

<script>
import TaCourseDates from '../courseDates/TaCourseDates'
import { numberMixin } from '@/mixins/numberMixin'
import coursesApi from '@/api/coursesApi'

export default {
  name: 'TaCourse',
  mixins: [numberMixin],
  components: {
    TaCourseDates,
  },
  props: {
    course: {
      required: true,
    },
    handleBookClicked: {
      type: Function,
      required: true,
    },
  },
}
</script>

<style lang="stylus" scoped>
.description {
  margin-top: 1em;
}

.dates {
  margin-top: 2em;
}
</style>
