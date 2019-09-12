<template>
  <v-container fluid grid-list-md id="teachers">
    <v-layout row wrap>
      <!--<ta-teachers
        :teachers="teachers"
        :loading="loading"
      />-->
    </v-layout>
  </v-container>
</template>

<script>
import Vue from 'vue'
import TaTeachers from '@/components/teachers/TaTeachers'
import teachersApi from '@/api/teachersApi'

export default Vue.extend({
  name: 'TaTeachersOverview',
  components: {
    TaTeachers
  },
  data() {
    return {
      teachers: undefined,
      loading: false,
    }
  },
  methods: {
    fetchTeachers() {
      this.loading = true
      teachersApi
        .findAll()
        .then(teachers => (this.teachers = teachers))
        .then(() => (this.loading = false))
    },
  },
  created() {
    this.fetchTeachers()
  },
  watch: {
  },
})
</script>
