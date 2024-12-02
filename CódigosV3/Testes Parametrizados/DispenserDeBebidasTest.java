import maquinario.DispenserDeBebidas;
import excecoes.DispenserEmperradoException;
import excecoes.SemProdutoDisponivelException;
import excecoes.SinalInvalidoException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DispenserDeBebidasTest {

    private DispenserDeBebidas dispenser;
    private NotificacaoNew notificavel; // Variável 'notificavel' do tipo NotificacaoNew

    @Before
    public void setUp() {
        dispenser = new DispenserDeBebidas(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        notificavel = new NotificacaoNew(); // Inicializando 'notificavel'
    }
    //CORRIGIDO
    @Test
    public void testLiberarProdutoComQuantidadeDisponivel() throws SemProdutoDisponivelException, SinalInvalidoException, DispenserEmperradoException {
        dispenser.destravar();
        dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);
        
        // Passando o 'notificavel' como parâmetro
        dispenser.simulaAbrirPortinha(notificavel);  // Corrigido: passando 'notificavel' como parâmetro
        assertEquals(DispenserDeBebidas.COCA_COLA, dispenser.simulaPegarBebida());
        
        // Passando o 'notificavel' para simulaFecharPortinha
        dispenser.simulaFecharPortinha(notificavel);  // Corrigido: passando 'notificavel' aqui também

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
    //corrigido
    @Test
    public void testAbrirFecharPortinhaComProdutoLiberado() throws SemProdutoDisponivelException, SinalInvalidoException, DispenserEmperradoException {
        dispenser.destravar();
        dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);

        // Passando 'notificavel' para simulaAbrirPortinha
        dispenser.simulaAbrirPortinha(notificavel);  // Corrigido: passando 'notificavel' como parâmetro
        assertTrue(dispenser.isAberto());
        System.out.println("Teste de abertura da portinha aprovado.");

        // Passando 'notificavel' para simulaFecharPortinha
        dispenser.simulaFecharPortinha(notificavel);  // Corrigido: passando 'notificavel' aqui também
        assertFalse(dispenser.isAberto());
        System.out.println("Teste de fechamento da portinha aprovado.");
    }

    @Test
    public void testEmperrar() {
        dispenser.simulaEmperrarPortinha();
        assertFalse(dispenser.isAberto());
        assertTrue(dispenser.isTravado());
    }

}
