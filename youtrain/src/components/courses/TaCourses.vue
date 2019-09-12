<template>
  <v-flex xs12>
    <v-card flat>
      <v-data-table
        :headers="headers"
        :items="courses"
        :loading="!allCoursesLoaded"
        :hide-actions="true"
        class="elevation-1"
        id="coursesTable"
      >
        <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
        <template slot="items" slot-scope="props">
          <tr class="course">
            <td>
              <v-icon
                class="mr-2"
                @click="props.expanded = !props.expanded"
              >{{props.expanded ? 'expand_less' : 'expand_more'}}</v-icon>
            </td>
            <td>{{ props.item.title }}</td>
            <td>{{ props.item.description }}</td>
            <td>{{ props.item.teacher }}</td>
            <td class="text-xs-right">{{ numberUtils().formatCurrency(props.item.price) }}</td>
            <td class="text-xs-right">
              <v-icon class="mr-2" @click="handleDetailsClicked(props.item)">arrow_forward</v-icon>
              <v-icon class="mr-2" @click="handleEditClicked(props.item)">edit</v-icon>
            </td>
          </tr>
        </template>
        <template slot="expand" slot-scope="props">
          <v-card flat>
            <v-container fluid>
              <v-layout row wrap>
                <ta-course-dates
                  :course-dates="props.item.courseDates"
                  :handle-book-clicked="handleBookClicked"
                  :search-range-begin="searchRangeBegin"
                  :search-range-end="searchRangeEnd"
                />
              </v-layout>
            </v-container>
          </v-card>
        </template>
      </v-data-table>
      <v-fab-transition>
        <v-btn color="pink" dark fixed bottom right fab @click="handleCreateClicked">
          <v-icon>add</v-icon>
        </v-btn>
      </v-fab-transition>
    </v-card>
  </v-flex>
</template>

<script>
import { headers as coursesTableHeaders } from './coursesTableHeaders'
import { numberMixin } from '@/mixins/numberMixin'
import TaCourseDates from '../courseDates/TaCourseDates'

export default {
  name: 'TaCourses',
  mixins: [numberMixin],
  components: {
    TaCourseDates,
  },
  props: {
    courses: {
      type: Array,
    },
    searchRangeBegin: {
      type: Date,
    },
    searchRangeEnd: {
      type: Date,
    },
    handleDetailsClicked: {
      type: Function,
      required: true,
    },
    handleCreateClicked: {
      type: Function,
      required: true,
    },
    handleEditClicked: {
      type: Function,
      required: true,
    },
    handleBookClicked: {
      type: Function,
      required: true,
    },
    loading: {
      type: Boolean,
    },
  },
  data() {
    return {
      headers: coursesTableHeaders,
    }
  },
  computed: {
    allCoursesLoaded() {
      return !this.loading && this.courses != null
    },
  },
}
</script>
