import io.Display;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DisplayTest {

    private Display display;

    @Before
    public void setUp() {
        display = new Display();
    }

    @Test
    @Parameters({
        "PRONTO"
    })
    public void testDisplayProntoParaUso(String mensagemEsperada) {
        display.mostrarMensagem(mensagemEsperada);
        String mensagemAtual = display.simulaVerMensagem();
        System.out.println(mensagemAtual);
        assertEquals(mensagemEsperada, mensagemAtual);
    }

    @Test
    @Parameters({
        "Olá",  
        "Eai",   
        "Oi"    
    })
    public void testMostrarMensagemCurta(String mensagemCurta) {
        display.mostrarMensagem(mensagemCurta);
        assertEquals(mensagemCurta, display.simulaVerMensagem());
    }

    @Test
    public void testMostrarMensagemLonga() {
        String mensagemLonga = "Esta é uma mensagem muito longa para o display";
        display.mostrarMensagem(mensagemLonga);
        assertEquals(mensagemLonga.substring(0, 25), display.simulaVerMensagem());
    }

    @Test
    public void testEspacosNoInicio() {
        String mensagem = " Mensagem com espaços no início";
        String mensagemEsperada = mensagem.substring(0, 25);

        display.mostrarMensagem(mensagem);

        assertEquals("Os espaços no início devem ser considerados como caracteres.", mensagemEsperada, display.simulaVerMensagem());
    }

}
