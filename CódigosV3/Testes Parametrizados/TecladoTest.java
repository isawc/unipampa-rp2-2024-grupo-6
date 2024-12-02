import io.Teclado;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TecladoTest {
    private Teclado teclado;
    private NotificacaoNew notificavel;

    @Before
    public void inicializar() {
        teclado = new Teclado();
        // no futuro vai ser substituido pelo controlador
        notificavel = new NotificacaoNew();
    }

    
    //mudar valoresperado
    @Test
    public void testApertarBotaoCocaCola() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_COCA_COLA);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_COCA_COLA, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 37;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoCocaColaZero() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_COCA_ZERO);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_COCA_ZERO, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 3;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoCocaColaLight() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_COCA_LIGHT);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_COCA_LIGHT, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 5;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSprite() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SPRITE);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_SPRITE, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 7;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSpriteZero() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SPRITE_ZERO);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_SPRITE_ZERO, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 11;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoFantaLaranja() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_FANTA_LARANJA);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_FANTA_LARANJA, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 13;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoFantaUva() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_FANTA_UVA);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_FANTA_UVA, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 17;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoKuatGuarana() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_KUAT_GUARANA);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_KUAT_GUARANA, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 19;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSchweppes() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SCHWEPPES);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_SCHWEPPES, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 23;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSchweppesCitrus() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SCHWEPPES_CITRUS);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_SCHWEPPES_CITRUS, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 29;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoInvalido() {
        int botaoInvalido = 35;
        teclado.simulaApertarBotao(notificavel, botaoInvalido);
        assertEquals(35, teclado.getUltimoBotaoPressionado());
        
    }

    @Test
    public void testApertarCombinacaoDeBotoes() {
        int combinacaoEsperada = Teclado.BOTAO_COCA_COLA * 103 * Teclado.BOTAO_COCA_LIGHT * Teclado.BOTAO_SPRITE;

        teclado.simulaApertarBotoes(notificavel, Teclado.BOTAO_COCA_COLA, Teclado.BOTAO_COCA_LIGHT, Teclado.BOTAO_SPRITE);
        assertEquals(combinacaoEsperada, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testNenhumBotaoPressionado() {
        assertEquals(Teclado.NENHUM_BOTAO_PRESSIONADO, teclado.getUltimoBotaoPressionado());
        
        // verifica se está identificado corretamente
        int valorEsperado = -1;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }
    
    @Test
    public void testApertarDevolverDinheiro(){
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_DEVOLVER_DINHEIRO);
        
        // verifica se o botão foi apertado
        assertEquals(Teclado.BOTAO_DEVOLVER_DINHEIRO, teclado.getUltimoBotaoPressionado());
        
        // verifica se o botão apertado está identificado corretamente
        int valorEsperado = 31;
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }
}
