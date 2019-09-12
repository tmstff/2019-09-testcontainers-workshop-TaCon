<template>
  <v-flex xs12>
    <v-card flat>
      <v-data-table
        :headers="headers"
        :items="teachers"
        :loading="!allTeachersLoaded"
        id="teachersTable"
      >
        <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
        <template slot="items" slot-scope="props">
          <tr class="teacher">
            <td>{{ props.item }}</td>
          </tr>
        </template>
      </v-data-table>
    </v-card>
  </v-flex>
</template>

<script>

export default {
  name: 'TaTeachers',
  components: {
  },
  props: {
    teachers: {
      type: Array,
    },
    loading: {
      type: Boolean,
    },
  },
  data() {
    return {
      headers: [
          {
            text: 'Name',
            align: 'left',
            value: 'name',
            sortable: true,
          }
      ]
    }
  },
  computed: {
    allTeachersLoaded() {
      return !this.loading && this.teachers != null
    },
  },
}
</script>
