import io.Teclado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
//parametrizado (isaac)
@RunWith(Parameterized.class)
public class TecladoTest {

    private Teclado teclado;
    private NotificacaoNew notificavel;

    @Parameterized.Parameter(0)
    public int botaoPressionado;

    @Parameterized.Parameter(1)
    public int valorEsperado;

    @Before
    public void inicializar() {
        teclado = new Teclado();
        notificavel = new NotificacaoNew(); // Substituído futuramente pelo controlador
    }
    //parametrizados (isaac)
    @Parameterized.Parameters(name = "{index}: Botão pressionado={0}, Valor esperado={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {Teclado.BOTAO_COCA_COLA, 37},
            {Teclado.BOTAO_COCA_ZERO, 3},
            {Teclado.BOTAO_COCA_LIGHT, 5},
            {Teclado.BOTAO_SPRITE, 7},
            {Teclado.BOTAO_SPRITE_ZERO, 11},
            {Teclado.BOTAO_FANTA_LARANJA, 13},
            {Teclado.BOTAO_FANTA_UVA, 17},
            {Teclado.BOTAO_KUAT_GUARANA, 19},
            {Teclado.BOTAO_SCHWEPPES, 23},
            {Teclado.BOTAO_SCHWEPPES_CITRUS, 29},
            {Teclado.BOTAO_DEVOLVER_DINHEIRO, 31},
            {35, 35}, // Botão inválido
            {-1, Teclado.NENHUM_BOTAO_PRESSIONADO} // Nenhum botão pressionado
        });
    }

    @Test
    public void testApertarBotao() {
        teclado.simulaApertarBotao(notificavel, botaoPressionado);
        assertEquals(botaoPressionado, teclado.getUltimoBotaoPressionado());
        assertEquals(valorEsperado, teclado.getUltimoBotaoPressionado());
    }
    
    
}
