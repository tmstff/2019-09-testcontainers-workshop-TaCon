/**
 * Provides the dateTimeUtils as mixin for Vue components.
 *
 * @author Mark Vz
 */

import dateTimeUtils from '@/utils/dateTimeUtils'

export const dateTimeMixin = {
  methods: {
    dateTimeUtils() {
      return dateTimeUtils
    },
  },
}
