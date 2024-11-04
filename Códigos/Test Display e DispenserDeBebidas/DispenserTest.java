package io;
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
    public void testDisplayEntradaEntupida() {
        display.mostrarMensagem("ENTRADA DE $ ENTUPIDA");
        String mensagemAtual = display.simulaVerMensagem();
        System.out.println(mensagemAtual);
        assertEquals("ENTRADA DE $ ENTUPIDA", mensagemAtual);
    }

    @Test
    public void testDisplayEscolhaUmaBebida() {
        display.mostrarMensagem("ESCOLHA UMA BEBIDA");
        String mensagemAtual = display.simulaVerMensagem();
        System.out.println(mensagemAtual);
        assertEquals("ESCOLHA UMA BEBIDA", mensagemAtual);
    }
}
