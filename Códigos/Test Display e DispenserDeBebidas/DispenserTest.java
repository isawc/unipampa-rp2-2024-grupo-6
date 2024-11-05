import io.Display;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DispenserTest {

    private Display display;

    @Before
    public void setUp() {
        display = new Display();
    }

    @Test
    public void testDisplayProntoParaUso() {
        display.mostrarMensagem("PRONTO");
        String mensagemAtual = display.simulaVerMensagem();
        System.out.println(mensagemAtual);
        assertEquals("PRONTO", mensagemAtual);
    }

    @Test
    public void testMostrarMensagemCurta() {
        String mensagemCurta = "Olá";
        display.mostrarMensagem(mensagemCurta);
        assertEquals(mensagemCurta, display.simulaVerMensagem());
    }
   
        @Test
    public void testMostrarMensagemLonga() {
        String mensagemLonga = "Esta é uma mensagem muito longa para o display";
        display.mostrarMensagem(mensagemLonga);
        assertEquals(mensagemLonga.substring(0, 25), display.simulaVerMensagem());
    }

   
}