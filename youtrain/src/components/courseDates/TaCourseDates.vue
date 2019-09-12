<template>
  <v-flex xs12>
    <v-card flat>
      <v-fab-transition v-if="editMode">
        <v-btn color="pink" dark top right fab @click="handleAddClicked">
          <v-icon>add</v-icon>
        </v-btn>
      </v-fab-transition>

      <v-data-table
        :headers="headers"
        :items="courseDates"
        :hide-actions="true"
        class="elevation-1"
      >
        <template slot="items" slot-scope="props">
          <tr :class="{highlight: courseDateInRange(props.item)}">
            <td>{{ props.item.courseNo }}</td>
            <td v-if="editMode">
              <v-edit-dialog
                :return-value.sync="props.item.begin"
                lazy
                @save="save"
                @cancel="cancel"
                @open="open"
                @close="close"
              >
                {{ dateTimeUtils().formatDateTime(props.item.begin) }}
                <v-text-field slot="input" v-model="props.item.begin" label="Edit" single-line></v-text-field>
              </v-edit-dialog>
            </td>
            <td v-else>{{ dateTimeUtils().formatDateTime(props.item.begin) }}</td>
            <td v-if="editMode">
              <v-edit-dialog
                :return-value.sync="props.item.end"
                lazy
                @save="save"
                @cancel="cancel"
                @open="open"
                @close="close"
              >
                {{ dateTimeUtils().formatDateTime(props.item.end) }}
                <v-text-field slot="input" v-model="props.item.end" label="Edit" single-line></v-text-field>
              </v-edit-dialog>
            </td>
            <td v-else>{{ dateTimeUtils().formatDateTime(props.item.end) }}</td>
            <td v-if="editMode" class="text-xs-right">
              <v-edit-dialog
                :return-value.sync="props.item.amount"
                lazy
                @save="save"
                @cancel="cancel"
                @open="open"
                @close="close"
              >
                {{ props.item.amount }}
                <v-text-field slot="input" v-model="props.item.amount" label="Edit" single-line></v-text-field>
              </v-edit-dialog>
            </td>
            <td class="text-xs-right" v-else>{{ props.item.amount }}</td>
            <td class="text-xs-right">
              <v-icon
                v-if="!editMode"
                class="mr-2"
                @click="handleBookClicked(props.item)"
              >shopping_basket</v-icon>
              <v-icon v-if="editMode" class="mr-2" @click="handleRemoveClicked(props.item)">delete</v-icon>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-card>
  </v-flex>
</template>

<script>
import dateFns from 'date-fns'
import { headers as courseDatesTableHeaders } from './courseDatesTableHeaders'
import { dateTimeMixin } from '@/mixins/dateTimeMixin'

export default {
  name: 'TaCourseDates',
  mixins: [dateTimeMixin],
  props: {
    courseDates: {
      type: Array,
    },
    searchRangeBegin: {
      type: Date,
    },
    searchRangeEnd: {
      type: Date,
    },
    handleBookClicked: {
      type: Function,
      required: false,
    },
    handleAddClicked: {
      type: Function,
      required: false,
    },
    handleRemoveClicked: {
      type: Function,
      required: false,
    },
    editMode: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      headers: courseDatesTableHeaders,
    }
  },
  methods: {
    courseDateInRange(courseDate) {
      try {
        return (
          dateFns.isWithinRange(
            courseDate.begin,
            this.searchRangeBegin,
            this.searchRangeEnd
          ) &&
          dateFns.isWithinRange(
            courseDate.end,
            this.searchRangeBegin,
            this.searchRangeEnd
          )
        )
      } catch (err) {
        // TODO: show validation error, don't absorb the exception here!
        // Start of range is after end of range
      }
    },
    open() {
      // console.log()
    },
    close() {
      // console.log()
    },
    save() {
      // console.log()
    },
    cancel() {
      // console.log()
    },
  },
}
</script>

<style lang="stylus" scoped>
.highlight {
  background-color: #ffffbb;
}
</style>
