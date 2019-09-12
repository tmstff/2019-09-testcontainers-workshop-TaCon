/**
 * Some util functions handling dates and times.
 *
 * @author Mark Vz
 */

import dateFns from 'date-fns'

function formatDateTime(date: Date): string {
  return dateFns.format(date, 'DD.MM.YYYY HH:mm')
}

function formatDate(date: Date): string {
  return dateFns.format(date, 'DD.MM.YYYY')
}

export default {
  formatDateTime,
  formatDate,
}
