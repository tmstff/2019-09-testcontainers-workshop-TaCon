/**
 * The Vue.js entry point. Here the SPA will be "injected" into
 * the HTML element with the id 'app' (see file index.html).
 *
 * @author Mark Vz
 */

import Vue from 'vue'
import './plugins/vuetify'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
