import io.Display;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayTest {

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
    // guilherme
    public void testDisplayErrosPadronizados(){
        display.mostrarMensagem("ENTRADA DE $ ENTUPIDA");
        System.out.println(display.simulaVerMensagem());
        assertEquals("ENTRADA DE $ ENTUPIDA", display.simulaVerMensagem());
        
        display.mostrarMensagem("ESCOLHA UMA BEBIDA");
        System.out.println(display.simulaVerMensagem());
        assertEquals("ESCOLHA UMA BEBIDA", display.simulaVerMensagem());
        
        display.mostrarMensagem("BEBIDA INDISPONIVEL");
        System.out.println(display.simulaVerMensagem());
        assertEquals("BEBIDA INDISPONIVEL", display.simulaVerMensagem());
        
        display.mostrarMensagem("PROBLEMA NO DISPENSER");
        System.out.println(display.simulaVerMensagem());
        assertEquals("PROBLEMA NO DISPENSER", display.simulaVerMensagem());
        
        double cont = 0.25;
        display.mostrarMensagem("FALTA R$" + cont);
        System.out.println(display.simulaVerMensagem());
        assertEquals("FALTA R$" + cont, display.simulaVerMensagem());
        
        display.mostrarMensagem("DINHEIRO INSUFICIENTE");
        System.out.println(display.simulaVerMensagem());
        assertEquals("DINHEIRO INSUFICIENTE", display.simulaVerMensagem());
    }
    
    @Test
    //guilherme
    public void testDisplayValor(){
        // Valores financeiros mostrados no display devem ser precedidos dos caracteres R$, sem 
        // quaisquer espaços e usando . (ponto) como divisão entre reais e centavos. Centavos 
        // sempre são apresentados com dois dígitos. Por exemplo: R$3.55
        double cont = 0.0;
        
        cont = cont + 25.5;
        display.mostrarMensagem("R$" + cont);
        System.out.println(display.simulaVerMensagem());
        assertEquals("R$" + cont, display.simulaVerMensagem());
        
        cont = cont + 0.07;
        display.mostrarMensagem("R$" + cont);
        System.out.println(display.simulaVerMensagem());
        assertEquals("R$" + cont, display.simulaVerMensagem());
        
        cont = cont + 0.003;
        display.mostrarMensagem("R$" + cont);
        System.out.println(display.simulaVerMensagem());
        assertEquals("R$" + cont, display.simulaVerMensagem());
        
        fail("Não respeita a regra de negocio");
    }

    @Test
    public void testMostrarMensagemCurta() {
        String mensagemCurta = "Olá";
        display.mostrarMensagem(mensagemCurta);
        
        //guilherme
        System.out.println(display.simulaVerMensagem());
        System.out.println("caracteres exibidos: " + display.simulaVerMensagem().length());
        
        assertEquals(mensagemCurta, display.simulaVerMensagem());
    }
     
    @Test
    public void testMostrarMensagemLonga() {
        // Podem ser exibidos, no máximo, 25 caracteres por vez no display. Mensagens acima de 
        // 25 caracteres serão truncadas para mostrar apenas os primeiros 25 caracteres.
        String mensagemLonga = "Esta é uma mensagem muito longa para o display";
        display.mostrarMensagem(mensagemLonga);
        
        //guilherme
        System.out.println(display.simulaVerMensagem());
        System.out.println("caracteres exibidos: " + display.simulaVerMensagem().length());
        
        assertEquals(mensagemLonga.substring(0, 25), display.simulaVerMensagem());
    }

   
}