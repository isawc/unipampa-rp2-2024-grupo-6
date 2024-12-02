import maquinario.DispenserDeBebidas;
import excecoes.DispenserEmperradoException;
import excecoes.SemProdutoDisponivelException;
import excecoes.SinalInvalidoException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DispenserDeBebidasTest {

    private DispenserDeBebidas dispenser;
    private NotificacaoNew notificavel;
    
    @Before
    public void setUp() {
        dispenser = new DispenserDeBebidas(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        notificavel = new NotificacaoNew();
    }
    //parametrizado (isaac)
    @Test
    @Parameters({
        "179, 179",
        "193, 193",
        "199, 199"
    })
    public void testLiberarProdutoComQuantidadeDisponivel(int idBebida, int bebidaEsperada)
            throws SemProdutoDisponivelException, SinalInvalidoException, DispenserEmperradoException {
        dispenser.destravar();
        dispenser.liberarProduto(idBebida);

        dispenser.simulaAbrirPortinha(notificavel);
        assertEquals(bebidaEsperada, dispenser.simulaPegarBebida());

        dispenser.simulaFecharPortinha(notificavel);

        System.out.println("Produto liberado com sucesso para bebida: " + idBebida);
    }

    @Test
    public void testLiberarProdutoComQuantidadeIndisponivel() throws SinalInvalidoException, DispenserEmperradoException {
        dispenser = new DispenserDeBebidas(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        dispenser.destravar();

        try {
            dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);
            fail("Erro esperado devido ao estoque vazio.");
        } catch (SemProdutoDisponivelException e) {
            System.out.println("Sem estoque.");
        }
    }

    @Test
    public void testLiberarProdutoInvalido() throws DispenserEmperradoException {
        dispenser.destravar();

        try {
            dispenser.liberarProduto(99);
            fail("Não é para liberar!");
        } catch (SinalInvalidoException e) {
            System.out.println("Produto inválido!");
        } catch (SemProdutoDisponivelException e) {
            fail("Esperava SinalInvalidoException, mas uma exceção diferente foi lançada.");
        }
    }
    //parametrizado
    @Test
    @Parameters({
        "true",
        "false"
    })
    public void testAbrirFecharPortinhaComProdutoLiberado(boolean portinhaEsperadaAberta) 
            throws SemProdutoDisponivelException, SinalInvalidoException, DispenserEmperradoException {
        dispenser.destravar();
        dispenser.liberarProduto(DispenserDeBebidas.COCA_COLA);

        dispenser.simulaAbrirPortinha(notificavel);

        assertTrue("Esperava que a portinha estivesse aberta após liberar o produto", dispenser.isAberto());

        dispenser.simulaFecharPortinha(notificavel);

        assertFalse("Esperava que a portinha estivesse fechada após o fechamento explícito", dispenser.isAberto());

        dispenser.simulaFecharPortinha(notificavel);

        assertFalse("Esperava que a portinha estivesse fechada após fechamento", dispenser.isAberto());
        System.out.println("Teste de abertura e fechamento da portinha aprovado.");
    }

    @Test
    public void testEmperrar() {
        dispenser.simulaEmperrarPortinha();
        assertFalse(dispenser.isAberto());
        assertTrue(dispenser.isTravado());
    }
}
