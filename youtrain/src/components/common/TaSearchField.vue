<template>
  <v-text-field
    slot="activator"
    :value="text"
    @input="debouncedHandleTextChange"
    :label="label"
    prepend-icon="search"
    clearable
  ></v-text-field>
</template>

<script>
import { debounce } from 'lodash'

export default {
  name: 'TaSearchField',
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
      text: this.value,
      debouncedHandleTextChange: null,
    }
  },
  methods: {
    handleTextChange(e) {
      this.text = e
      this.$emit('input', this.text)
    },
  },
  created() {
    this.debouncedHandleTextChange = debounce(this.handleTextChange, 300)
  },
}
</script>
