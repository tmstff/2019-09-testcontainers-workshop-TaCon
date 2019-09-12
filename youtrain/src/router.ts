import Vue from 'vue'
import Router from 'vue-router'
import TaHome from './views/TaHome.vue'
import TaAbout from './views/TaAbout.vue'
import TaCoursesOverview from './views/TaCoursesOverview.vue'
import TaTeacherOverview from './views/TaTeacherOverview.vue'
import TaCourseDetails from './views/TaCourseDetails.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: TaHome,
    },
    {
      path: '/about',
      name: 'about',
      component: TaAbout,
    },
    /*{
      path: '/teachers',
      name: 'teacherOverview',
      component: TaTeacherOverview,
    },*/
    {
      path: '/courses',
      name: 'coursesOverview',
      component: TaCoursesOverview,
    },
    {
      path: '/courses/:courseId',
      name: 'courseDetails',
      component: TaCourseDetails,
      props: true,
    },
  ],
})
