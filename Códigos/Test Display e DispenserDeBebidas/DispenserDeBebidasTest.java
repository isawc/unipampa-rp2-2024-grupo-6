package maquinario;
import excecoes.DispenserEmperradoException;
import excecoes.SemProdutoDisponivelException;
import excecoes.SinalInvalidoException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DispenserDeBebidasTest {

    private DispenserDeBebidas dispenser;

    @Before
    public void setUp() {
        dispenser = new DispenserDeBebidas(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
    }
    
    @Test
    public void testInicializacao() {
    assertFalse(dispenser.isAberto());
    assertFalse(dispenser.isTravado());
}

    @Test
    public void testLiberarProdutoComQuantidadeDisponivel() throws SemProdutoDisponivelException, SinalInvalidoException, DispenserEmperradoException {
        dispenser.destravar();
        dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);
        dispenser.simulaAbrirPortinha();
        assertEquals(DispenserDeBebidas.COCA_COLA, dispenser.simulaPegarBebida());
        dispenser.simulaFecharPortinha();
        
        System.out.println("produto liberado!");
    }

    @Test
    public void testLiberarProdutoComQuantidadeIndisponivel() throws SinalInvalidoException, DispenserEmperradoException {
        dispenser = new DispenserDeBebidas(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        dispenser.destravar();

        try {
            dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);
            fail("erro");
        } catch (SemProdutoDisponivelException e) {
            System.out.println("Sem estoque.");
        }
    }

    @Test
    public void testLiberarProdutoInvalido() throws DispenserEmperradoException {
        dispenser.destravar();

        try {
            dispenser.liberarProduto(99);
            fail("não é para liberar!");
        } catch (SinalInvalidoException e) {
            System.out.println("Produto inválido!");
        } catch (SemProdutoDisponivelException e) {
            fail("Esperava SinalInvalidoException, mas uma exceção diferente foi lançada.");
        }
    }

    @Test
    public void testLiberarProdutoComDispenserTravado() {
        try {
            dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);
            fail("DISPENSER TRAVADO!!!!");
        } catch (SemProdutoDisponivelException | SinalInvalidoException e) {
            System.out.println("Teste de tentativa de liberação com dispenser travado aprovado.");
        }
    }

    @Test
    public void testAbrirFecharPortinhaComProdutoLiberado() throws SemProdutoDisponivelException, SinalInvalidoException, DispenserEmperradoException {
        dispenser.destravar();
        dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);
        
        dispenser.simulaAbrirPortinha();
        assertTrue(dispenser.isAberto());
        System.out.println("Teste de abertura da portinha aprovado.");

        dispenser.simulaFecharPortinha();
        assertFalse(dispenser.isAberto());
        System.out.println("Teste de fechamento da portinha aprovado.");
    }
    
    @Test
    public void testEmperrar() {
        dispenser.simulaEmperrarPortinha();
        assertFalse(dispenser.isAberto());
        assertTrue(dispenser.isTravado());
    }
    
    @Test
    public void testAbrirEFechar() {
    dispenser.simulaAbrirPortinha();
    assertTrue(dispenser.isAberto());
    dispenser.simulaFecharPortinha();
    assertFalse(dispenser.isAberto());
    }
}
