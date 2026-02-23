describe('Products flow', () => {
  beforeEach(() => {
    cy.visit('/products');
  });

  it('should list products', () => {
    cy.contains('Produtos').should('exist');
    cy.get('table tbody tr').should('have.length.greaterThan', 0);
  });

  it('should create a product', () => {
    cy.contains('Add Produto').click();

    const productName = 'Produto Cypress' + Date.now();
    cy.get('input[placeholder="Insira o nome do produto"]').type(
      'Produto Cypress' + Date.now(),
    );

    cy.get('input[placeholder="0"]').clear().type('199.99');

    cy.contains('Salvar').click();

    cy.contains('Próxima').click();

    function clickUntilDisable() {
      cy.contains('Próximo').then(($btn) => {
        if ($btn.is(':disabled') || $btn.hasClass('disabled')) {
          cy.log('Botão Próximo agora está desabilitado');
          return;
        }

        cy.wrap($btn).click();
        clicarAteDesabilitar();
      });
    }

    it('Click Next until it becomes inactive.', () => {
      clickUntilDisable();

      cy.contains('Próximo').should('be.disabled');
      cy.contains('Produto Cypress' + Date.now()).should('exist');
    });
  });
});
