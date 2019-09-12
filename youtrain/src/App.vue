<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <router-link class="toolbar-title" to="/">
          <span>You</span>
          <span class="font-weight-light">Train</span>
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn flat to="/about">
        <span class="mr-2">About</span>
      </v-btn>
    </v-toolbar>

    <v-content>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style lang="stylus" scoped>
.toolbar-title {
  color: inherit;
  text-decoration: inherit;
}
</style>
