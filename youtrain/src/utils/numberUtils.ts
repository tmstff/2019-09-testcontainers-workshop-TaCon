/**
 * Some util functions handling numbers and their formatting.
 *
 * @author Mark Vz
 */

function formatCurrency(n: number): string {
  return Number(n).toLocaleString('de-DE', {
    style: 'currency',
    currency: 'EUR',
  })
}

export default {
  formatCurrency,
}
