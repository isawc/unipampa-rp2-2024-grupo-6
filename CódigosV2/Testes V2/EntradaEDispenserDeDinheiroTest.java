import excecoes.EntradaDeDinheiroEntupidaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import maquinario.EntradaEDispenserDeDinheiro;
import maquinario.Moeda;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EntradaEDispenserDeDinheiroTest {

    private EntradaEDispenserDeDinheiro dispenser;
    private NotificacaoNew notificavel;

    @Before
    public void inicializar() {
        // substituir pelo controlador
        notificavel = new NotificacaoNew();
    }

    @Test
    public void testAdicionarMoedaValida() {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        Moeda moeda = Moeda.CINCO_CENTAVOS;
        dispenser.simulaColocarMoeda(notificavel, moeda);

        assertEquals(1, dispenser.contarMoedasEntrada(moeda));
    }

    @Test(expected = NullPointerException.class)
    public void testAdicionarMoedaNula() {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        dispenser.simulaColocarMoeda(notificavel, null);
    }

    @Test
    public void testAdicionarMoedaNaCapacidadeMaxima() {
        dispenser = new EntradaEDispenserDeDinheiro(0, 0, 0, 0, 0);
        Moeda moeda = Moeda.CINCO_CENTAVOS;
        for (int i = 0; i < 31; i++) {
            dispenser.simulaColocarMoeda(notificavel, moeda);
        }
    }

    @Test
    public void testAdicionarDiversasMoedas() {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        Moeda[] moedas = {Moeda.CINCO_CENTAVOS, Moeda.DEZ_CENTAVOS, Moeda.VINTE_E_CINCO_CENTAVOS};
        for (Moeda moeda : moedas) {
            dispenser.simulaColocarMoeda(notificavel, moeda);
            assertEquals(1, dispenser.contarMoedasEntrada(moeda));
        }
    }

    //corrigido
    @Test
    public void testRemoverMoedasDezCentavos() {
    dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
    dispenser.simulaColocarMoeda(notificavel, Moeda.DEZ_CENTAVOS);
    dispenser.simulaColocarMoeda(notificavel, Moeda.DEZ_CENTAVOS);
    int quantidadeRetirada = dispenser.simulaPegarMoedas(notificavel, Moeda.DEZ_CENTAVOS); // Adicionado o parâmetro notificavel

    assertEquals(0, quantidadeRetirada);
    assertFalse(dispenser.hasMoedasDevolvidas());
}

    //corrigido
    @Test
    public void testDispenserVazioApósRetirada() {
    dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
    dispenser.simulaColocarMoeda(notificavel, Moeda.CINCO_CENTAVOS);
    dispenser.simulaPegarMoedas(notificavel, Moeda.CINCO_CENTAVOS);  // Corrigido: passando o 'notificavel' como parâmetro

    assertFalse(dispenser.hasMoedasDevolvidas());
}

    //corrigido
    @Test
public void testDevolverDinheiro() {
    dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
    
    Moeda[] moedas = Moeda.values();
    for (Moeda moeda : moedas) {
        dispenser.simulaColocarMoeda(notificavel, moeda);
        assertEquals(true, dispenser.hasDinheiroColocado());
        
        try {
            dispenser.devolverDinheiro();
            assertEquals(true, dispenser.hasMoedasDevolvidas());
            dispenser.simulaPegarMoedas(notificavel, moeda); // Adicionado o parâmetro notificavel
            assertEquals(false, dispenser.hasMoedasDevolvidas());
        } catch (EntradaDeDinheiroEntupidaException ex) {
            Logger.getLogger(EntradaEDispenserDeDinheiroTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


}

