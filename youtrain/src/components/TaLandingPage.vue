<template>
  <v-container grid-list-md>
    <v-layout align-center justify-center row fill-height id="cards">
      <v-flex xs4>
        <v-card dark>
          <v-img :src="openSignPic" height="300px">
            <v-container fill-height fluid pa-2>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline white--text">Discover our courses</span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-img>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn to="/courses" icon>
              <v-icon>arrow_forward</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
      <!--<v-flex xs4>
        <v-card dark>
          <v-img :src="teachersPic" height="300px">
            <v-container fill-height fluid pa-2>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline white--text">Discover our teachers</span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-img>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn to="/teachers" icon>
              <v-icon>arrow_forward</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>-->
      <v-flex xs4>
        <v-card dark>
          <v-img :src="aboutUsPic" height="300px">
            <v-container fill-height fluid pa-2>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline white--text">About YouTrain</span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-img>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn to="/about" icon>
              <v-icon>arrow_forward</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import openSignPic from '@/assets/images/open-sign.jpg'
import teachersPic from '@/assets/images/teachers.jpg'
import aboutUsPic from '@/assets/images/about-us.jpg'

export default {
  name: 'TaLandingPage',
  data() {
    return {
      openSignPic,
      teachersPic,
      aboutUsPic,
    }
  },
}
</script>
