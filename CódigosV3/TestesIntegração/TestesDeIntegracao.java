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
        assertEquals("PRONTO", display.simulaVerMensagem());
        assertTrue(bebidas.isTravado());
        assertFalse(bebidas.isAberto());
        
        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
        assertEquals("R$1.00", display.simulaVerMensagem());
        cofre.simulaColocarMoeda(controle, Moeda.UM_REAL);
        assertEquals("R$2.00", display.simulaVerMensagem());
        
        //receber dinheiro de volta
        //moedas da entrada para saida de troco
        
        assertEquals("PRONTO", display.simulaVerMensagem());
        assertTrue(bebidas.isTravado());
        assertFalse(bebidas.isAberto());
    }
    
    @Test
    public void testExcecao1(){
        
    }
    
    @Test
    public void testExcecao2(){
        
    }
    
    @Test
    public void testExcecao3(){
        
    }
    
    @Test
    public void testExcecao4(){
        
    }
    
    @Test
    public void testExcecao5(){
        
    }
    
    @Test
    public void testExcecao6(){
        
    }
    
    @Test
    public void testExcecao7(){
        
    }
    
    @Test
    public void testExcecao8(){
        
    }
    
    @Test
    public void testExcecao9(){
        
    }
    
    @Test
    public void testExcecao10(){
        
    }
}