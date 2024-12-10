import excecoes.SemProdutoDisponivelException;
import io.*;
import logica.*;
import maquinario.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe base de testes de integração.
 * 
 * Esses testes usam JUnit 4.
 * 
 * @author Jean Cheiran jeancheiran@unipampa.edu.br
 */
public class TestesDeIntegracao {
    
    EntradaEDispenserDeDinheiro cofre;
    DispenserDeBebidas bebidas;
    Teclado teclado;
    Display display;
    Controle controle;
    
    public TestesDeIntegracao() {  }
    
    @Before
    public void setUp(){
        cofre = new EntradaEDispenserDeDinheiro(100, 100, 100, 100, 100);
        bebidas = new DispenserDeBebidas(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        display = new Display();
        teclado = new Teclado();
        controle = new Controle(bebidas, cofre, display, teclado);
    }
    
    @After
    public void tearDown(){
        cofre = null;
        bebidas = null;
        display = null;
        teclado = null;
        controle = null;
    }
    
    // Teste feito pelo professor 
    @Test
    public void testFuncionamentoCorretoDeUmaCompraDeFantaUva() throws SemProdutoDisponivelException {        
        assertEquals("PRONTO", display.simulaVerMensagem());
        assertTrue(bebidas.isTravado());
        assertFalse(bebidas.isAberto());
        
        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
        assertEquals("R$1.00", display.simulaVerMensagem());
        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
        assertEquals("R$2.00", display.simulaVerMensagem());
        
        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
        
        assertFalse(cofre.hasDinheiroColocado());
        assertFalse(bebidas.isTravado());
        
        bebidas.simulaAbrirPortinha(controle);
        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
        bebidas.simulaFecharPortinha(controle);
        
        assertTrue(cofre.hasMoedasDevolvidas());
        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
        assertFalse(cofre.hasMoedasDevolvidas());
        
        assertEquals("PRONTO", display.simulaVerMensagem());
        assertTrue(bebidas.isTravado());
        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testFluxoAlt1(){
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_DEVOLVER_DINHEIRO);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.UM_REAL));
//        
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao1(){
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
        
        // recebe moedas acima do limite da maquina
        
        // mostrar erro no display e entra em manutenção
    }
    
    @Test
    public void testExcecao2() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
        
        // muitos botões ao mesmo tempo
        // mensagem solicitando escolher uma bebida
        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao3() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
        
        // escolhida uma bebida em falta
        // display indica ausencia da bebida
        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao4() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
        
        // não é possivel destravar o dispenser
        // indica o erro, devolve as moedas, enntra em manutenção
        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao5() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
        
        // moedas insuficientes para troco
        // liberar a maior quantidade possivel de troco, mostra o troco faltante, entra em manutenção
        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao6() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
        
        // não é possivel travar a porta
        // erro no display e entra em manutenção
        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao7() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao8() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao9() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao10() throws SemProdutoDisponivelException{
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
//        
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$1.00", display.simulaVerMensagem());
//        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
//        assertEquals("R$2.00", display.simulaVerMensagem());
//        
//        teclado.simulaApertarBotao(controle, Teclado.BOTAO_FANTA_UVA); //custa R$1,95
//        
//        assertFalse(cofre.hasDinheiroColocado());
//        assertFalse(bebidas.isTravado());
//        
//        bebidas.simulaAbrirPortinha(controle);
//        assertEquals(DispenserDeBebidas.FANTA_UVA, bebidas.simulaPegarBebida());
//        bebidas.simulaFecharPortinha(controle);
//        
//        assertTrue(cofre.hasMoedasDevolvidas());
//        assertEquals(1,cofre.simulaPegarMoedas(controle, Moeda.CINCO_CENTAVOS));
//        assertFalse(cofre.hasMoedasDevolvidas());
//        
//        assertEquals("PRONTO", display.simulaVerMensagem());
//        assertTrue(bebidas.isTravado());
//        assertFalse(bebidas.isAberto());
    }
}