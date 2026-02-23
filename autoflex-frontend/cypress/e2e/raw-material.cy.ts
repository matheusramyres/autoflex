describe('Product Materials', () => {
  beforeEach(() => {
    cy.visit('/products');
  });

  it('should expand product and show materials', () => {
    cy.get('table tbody tr').first().find('button').first().click();

    cy.contains('Materiais de Composição').should('exist');
  });

  it('should add material to product', () => {
    cy.get('table tbody tr').first().find('button').first().click();

    cy.contains('Add Material').click();

    cy.get('[data-cy="materialId"]').clear().type(1);
    cy.get('[data-cy="quantity"]').clear().type(5);
    cy.get('[data-cy="saveMaterial"]').click();
  });

  it('should remove material from product', () => {
    cy.get('table tbody tr').first().find('button').first().click();

    cy.get('[data-cy="deleteMaterial"]').first().click();

    cy.contains('Item removido').should('exist');
  });
});
