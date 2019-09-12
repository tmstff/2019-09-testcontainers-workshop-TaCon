import { expect } from 'chai'
import { shallowMount } from '@vue/test-utils'
import TaLandingPage from '@/components/TaLandingPage.vue'

describe('TaLandingPage.vue', () => {
  it('renders courses and about cards', () => {
    // const msg = 'new message'
    // const wrapper = shallowMount(TaLandingPage, {
    //   propsData: { msg },
    // })
    const wrapper = shallowMount(TaLandingPage)
    expect(wrapper.text()).to.include('Discover our courses')
    expect(wrapper.text()).to.include('About YouTrain')
  })
})
