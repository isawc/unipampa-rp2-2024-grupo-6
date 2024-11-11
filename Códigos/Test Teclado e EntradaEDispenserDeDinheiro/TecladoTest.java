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
        notificavel = new NotificacaoNew();
    }

    @Test
    public void testApertarBotaoCocaCola() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_COCA_COLA);
        assertEquals(Teclado.BOTAO_COCA_COLA, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoCocaColaZero() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_COCA_ZERO);
        assertEquals(Teclado.BOTAO_COCA_ZERO, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoCocaColaLight() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_COCA_LIGHT);
        assertEquals(Teclado.BOTAO_COCA_LIGHT, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSprite() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SPRITE);
        assertEquals(Teclado.BOTAO_SPRITE, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSpriteZero() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SPRITE_ZERO);
        assertEquals(Teclado.BOTAO_SPRITE_ZERO, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoFantaLaranja() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_FANTA_LARANJA);
        assertEquals(Teclado.BOTAO_FANTA_LARANJA, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoFantaUva() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_FANTA_UVA);
        assertEquals(Teclado.BOTAO_FANTA_UVA, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoKuatGuarana() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_KUAT_GUARANA);
        assertEquals(Teclado.BOTAO_KUAT_GUARANA, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSchweppes() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SCHWEPPES);
        assertEquals(Teclado.BOTAO_SCHWEPPES, teclado.getUltimoBotaoPressionado());
    }

    @Test
    public void testApertarBotaoSchweppesCitrus() {
        teclado.simulaApertarBotao(notificavel, Teclado.BOTAO_SCHWEPPES_CITRUS);
        assertEquals(Teclado.BOTAO_SCHWEPPES_CITRUS, teclado.getUltimoBotaoPressionado());
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
    }
}
