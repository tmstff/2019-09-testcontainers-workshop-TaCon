/**
 * Provides the numberUtils as mixin for Vue components.
 *
 * @author Mark Vz
 */

import numberUtils from '@/utils/numberUtils'

export const numberMixin = {
  methods: {
    numberUtils() {
      return numberUtils
    },
  },
}
