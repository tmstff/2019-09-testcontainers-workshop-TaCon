<template>
  <v-flex v-if="courseToEdit" xs12>
    <v-dialog :value="showDialog" @input="handleClose" width="70%" @keydown.esc="handleClose">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>{{dialogTitle}}</v-card-title>

        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field
              v-model="courseToEdit.title"
              :rules="titleRules"
              :counter="256"
              label="Title"
              required
            ></v-text-field>
            <v-textarea
              v-model="courseToEdit.description"
              :rules="descriptionRules"
              :counter="2048"
              label="Description"
              required
            ></v-textarea>
            <v-text-field
              v-model="courseToEdit.teacher"
              :rules="teacherRules"
              :counter="128"
              label="Teacher"
              required
            ></v-text-field>
            <v-text-field v-model="courseToEdit.price" :rules="priceRules" label="Price" required></v-text-field>
          </v-form>

          <ta-course-dates
            :key="courseToEdit.id"
            :course-dates="courseToEdit.courseDates"
            :handle-add-clicked="handleAddCourseDateClicked"
            :handle-remove-clicked="handleRemoveCourseDateClicked"
            :edit-mode="true"
          />
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="secondary" flat @click="handleClose">Cancel</v-btn>
          <v-btn v-if="!createMode" color="error" flat @click="handleDelete">Delete course</v-btn>
          <v-btn color="primary" flat :disabled="!valid" @click="handleSave">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-flex>
</template>

<script>
import TaCourseDates from '../courseDates/TaCourseDates'
import coursesApi from '@/api/coursesApi'

function buildRequiredRule(label) {
  return [v => !!v || `${label} is required`]
}

function buildMustBeNumberRule(label) {
  // TODO: respect locales (decimal point)
  return [v => (v && !isNaN(v)) || `${label} must be a number`]
}

function buildMaxLengthRule(label, len) {
  return [
    v =>
      (v && v.length <= len) || `${label} must be less than ${len} characters`,
  ]
}

function buildRequiredAndMaxLengthRules(label, len) {
  return buildRequiredRule(label).concat(buildMaxLengthRule(label, len))
}

export default {
  name: 'TaCourseEdit',
  components: {
    TaCourseDates,
  },
  model: {
    prop: 'showDialog',
    event: 'change',
  },
  props: {
    showDialog: {
      type: Boolean,
    },
    course: {
      required: true,
    },
  },
  data() {
    return {
      courseToEdit: this.course ? { ...this.course } : {},
      // FIXME: Save button is enabled when dialog is opened in create mode
      valid: true,
      titleRules: buildRequiredAndMaxLengthRules('Title', 256),
      descriptionRules: buildRequiredAndMaxLengthRules('Description', 2048),
      teacherRules: buildRequiredAndMaxLengthRules('Teacher', 128),
      priceRules: buildRequiredRule('Price').concat(
        buildMustBeNumberRule('Price')
      ),
    }
  },
  computed: {
    createMode() {
      return this.course == null
    },
    dialogTitle() {
      return this.createMode ? 'Create Course' : 'Edit Course'
    },
  },
  methods: {
    handleSave() {
      if (this.createMode) {
        this.$emit('create', this.courseToEdit)
      } else {
        this.$emit('update', this.courseToEdit)
      }
      this.handleClose()
    },
    handleAddCourseDateClicked() {
      if (!this.courseToEdit.courseDates) {
        this.$set(this.courseToEdit, 'courseDates', [])
      }
      this.courseToEdit.courseDates.push({
        begin: new Date().toISOString(),
        end: new Date().toISOString(),
        amount: 10,
      })
    },
    handleRemoveCourseDateClicked(courseDate) {
      const index = this.courseToEdit.courseDates.indexOf(courseDate)
      this.courseToEdit.courseDates.splice(index, 1)
    },
    handleDelete() {
      // TODO: add confirm dialog
      this.$emit('delete', this.courseToEdit.id)
      this.handleClose()
    },
    handleClose() {
      this.$emit('change', false)
    },
  },
}
</script>
