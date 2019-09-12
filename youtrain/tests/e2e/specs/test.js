// https://docs.cypress.io/api/introduction/api.html

describe('Should render all expected words', () => {
  it('Visits the app root url (landing page)', () => {
    cy.visit('/')
    cy.contains('span', 'You')
    cy.contains('span', 'Train')
    cy.contains('span', 'About')
    cy.contains('span', 'Discover our courses')
    cy.contains('span', 'About YouTrain')
  })
})
