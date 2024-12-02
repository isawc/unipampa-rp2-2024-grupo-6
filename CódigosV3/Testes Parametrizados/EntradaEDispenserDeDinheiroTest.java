import excecoes.EntradaDeDinheiroEntupidaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Notificavel;
import maquinario.EntradaEDispenserDeDinheiro;
import maquinario.Moeda;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class EntradaEDispenserDeDinheiroTest {

    private EntradaEDispenserDeDinheiro dispenser;
    private Notificavel notificavel;

    @Before
    public void inicializar() {
        // substituir pelo controlador
        notificavel = new Notificavel() {
            @Override
            public void notificaDinheiroInserido() {
            
            }

            @Override
            public void notificaBotaoPressionado() {
             
            }

            @Override
            public void notificaFechamentoDaPortinha() {
              
            }

            @Override
            public void notificaAberturaDaPortinha() {
               
            }

            @Override
            public void notificaMoedasPegas() {
              
            }
        };
    }
    //todos parametrizados (isaac)
    @Test
    @Parameters({"CINCO_CENTAVOS"})
    public void testAdicionarMoedaValida(Moeda moeda) {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        dispenser.simulaColocarMoeda(notificavel, moeda);

        assertEquals(1, dispenser.contarMoedasEntrada(moeda));
    }

    @Test(expected = NullPointerException.class)
    public void testAdicionarMoedaNula() {
    dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
    Moeda moeda = null;
    if (moeda == null) {
        throw new NullPointerException("A moeda não pode ser nula.");
    }
    
    dispenser.simulaColocarMoeda(notificavel, moeda);
}

    @Test
    @Parameters({
        "0, 0, 0, 0, 0, CINCO_CENTAVOS, 31", // Exemplo com 31 moedas de 5 centavos
        "10, 10, 10, 10, 10, CINCO_CENTAVOS, 1"  // Exemplo com 1 moeda de 5 centavos
    })
    public void testAdicionarMoedaNaCapacidadeMaxima(int cinco, int dez, int vinte, int cinquenta, int cem, Moeda moeda, int quantidadeEsperada) {
        dispenser = new EntradaEDispenserDeDinheiro(cinco, dez, vinte, cinquenta, cem);
        for (int i = 0; i < quantidadeEsperada; i++) {
            dispenser.simulaColocarMoeda(notificavel, moeda);
        }
        assertEquals(quantidadeEsperada, dispenser.contarMoedasEntrada(moeda));
    }

    @Test
    @Parameters({
        "CINCO_CENTAVOS, DEZ_CENTAVOS, VINTE_E_CINCO_CENTAVOS",
        "DEZ_CENTAVOS, CINCO_CENTAVOS, CINQUENTA_CENTAVOS"
    })
    public void testAdicionarDiversasMoedas(Moeda moeda1, Moeda moeda2, Moeda moeda3) {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        Moeda[] moedas = {moeda1, moeda2, moeda3};
        for (Moeda moeda : moedas) {
            dispenser.simulaColocarMoeda(notificavel, moeda);
            assertEquals(1, dispenser.contarMoedasEntrada(moeda));
        }
    }

    @Test
    @Parameters({"DEZ_CENTAVOS, 2"})
    public void testRemoverMoedasDezCentavos(Moeda moeda, int quantidadeEsperada) {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        for (int i = 0; i < quantidadeEsperada; i++) {
            dispenser.simulaColocarMoeda(notificavel, moeda);
        }
        int quantidadeRetirada = dispenser.simulaPegarMoedas(notificavel, moeda);
        assertEquals(0, quantidadeRetirada);
        assertFalse(dispenser.hasMoedasDevolvidas());
    }

    @Test
    @Parameters({"CINCO_CENTAVOS"})
    public void testDispenserVazioApósRetirada(Moeda moeda) {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        dispenser.simulaColocarMoeda(notificavel, moeda);
        dispenser.simulaPegarMoedas(notificavel, moeda);

        assertFalse(dispenser.hasMoedasDevolvidas());
    }

    @Test
    @Parameters({"CINCO_CENTAVOS, DEZ_CENTAVOS, VINTE_E_CINCO_CENTAVOS"})
    public void testDevolverDinheiro(Moeda moeda1, Moeda moeda2, Moeda moeda3) {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        Moeda[] moedas = {moeda1, moeda2, moeda3};
        for (Moeda moeda : moedas) {
            dispenser.simulaColocarMoeda(notificavel, moeda);
            assertEquals(true, dispenser.hasDinheiroColocado());

            try {
                dispenser.devolverDinheiro();
                assertEquals(true, dispenser.hasMoedasDevolvidas());
                dispenser.simulaPegarMoedas(notificavel, moeda);
                assertEquals(false, dispenser.hasMoedasDevolvidas());
            } catch (EntradaDeDinheiroEntupidaException ex) {
                Logger.getLogger(EntradaEDispenserDeDinheiroTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
