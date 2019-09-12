<template>
  <div>
    <ta-landing-page/>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import TaLandingPage from '@/components/TaLandingPage.vue'

export default Vue.extend({
  name: 'TaHome',
  components: {
    TaLandingPage,
  },
})
</script>
