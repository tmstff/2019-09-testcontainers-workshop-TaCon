<template>
  <v-menu
    :close-on-content-click="false"
    v-model="dateMenu"
    :nudge-right="40"
    lazy
    transition="scale-transition"
    offset-y
    full-width
    min-width="290px"
  >
    <v-text-field
      slot="activator"
      v-model="formattedDate"
      :label="label"
      prepend-icon="event"
      readonly
      clearable
    ></v-text-field>
    <v-date-picker v-model="dateValue" @input="handleDateChange"></v-date-picker>
  </v-menu>
</template>

<script>
import dateTimeUtils from '@/utils/dateTimeUtils'

export default {
  name: 'TaDatePicker',
  props: {
    value: {
      type: String,
    },
    label: {
      type: String,
    },
  },
  data() {
    return {
      dateMenu: false,
      dateValue: this.value,
    }
  },
  computed: {
    formattedDate: {
      get() {
        return this.dateValue ? dateTimeUtils.formatDate(this.dateValue) : null
      },
      set(val) {
        this.updateDateValue(val)
      },
    },
  },
  methods: {
    updateDateValue(val) {
      this.dateValue = val
      this.$emit('input', this.dateValue)
    },
    handleDateChange(e) {
      this.dateMenu = false
      this.updateDateValue(e)
    },
  },
}
</script>
