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

    @Test
    public void testRemoverMoedasDezCentavos() {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        dispenser.simulaColocarMoeda(notificavel, Moeda.DEZ_CENTAVOS);
        dispenser.simulaColocarMoeda(notificavel, Moeda.DEZ_CENTAVOS);
        int quantidadeRetirada = dispenser.simulaPegarMoedas(Moeda.DEZ_CENTAVOS);

        assertEquals(0, quantidadeRetirada);
        assertFalse(dispenser.hasMoedasDevolvidas());
    }

    @Test
    public void testDispenserVazioApósRetirada() {
        dispenser = new EntradaEDispenserDeDinheiro(10, 10, 10, 10, 10);
        dispenser.simulaColocarMoeda(notificavel, Moeda.CINCO_CENTAVOS);
        dispenser.simulaPegarMoedas(Moeda.CINCO_CENTAVOS);

        assertFalse(dispenser.hasMoedasDevolvidas());
    }

}
