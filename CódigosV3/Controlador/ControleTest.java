import excecoes.SemProdutoDisponivelException;
import excecoes.SinalInvalidoException;
import io.Teclado;
import io.Saida;
import io.Entrada;
import maquinario.Dispenser;
import maquinario.Cofre;
import maquinario.EntradaEDispenserDeDinheiro;
import maquinario.Moeda;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControleTest {

    private Controle controle;
    private Dispenser bebidas;
    private Cofre cofre;
    private Saida saida;
    private Entrada entrada;
    private EntradaEDispenserDeDinheiro entradaDinheiro;
    private Teclado teclado;

    @Before
    public void setUp() {
        // Substituir mocks por instâncias reais ou simplificadas
        bebidas = new Dispenser();  // ou implemente uma versão simples da classe Dispenser
        cofre = new Cofre();        // ou implemente uma versão simples da classe Cofre
        saida = new Saida();        // ou implemente uma versão simples da classe Saida
        entrada = new Entrada();    // ou implemente uma versão simples da classe Entrada
        entradaDinheiro = new EntradaEDispenserDeDinheiro();  // ou implemente uma versão simplificada
        teclado = new Teclado();    // ou implemente uma versão simplificada

        controle = new Controle(bebidas, cofre, saida, entrada, entradaDinheiro);
    }

    @Test
    public void testInsereDinheiroDentroLimite() {
        // Simula o comportamento da contagem de moedas dentro do limite
        entradaDinheiro.simulaContagemMoedas(Moeda.CINQUENTA_CENTAVOS, 1);  // Método fictício para simular
        controle.insereDinheiro(Moeda.CINQUENTA_CENTAVOS);
        assertEquals(0.5, controle.valorAcumulado, 0.01);
        saida.mostrarMensagem("Mensagem esperada");  // Verifica a mensagem
    }

    @Test
    public void testInsereDinheiroAcimaLimite() {
        entradaDinheiro.simulaContagemMoedas(Moeda.CINQUENTA_CENTAVOS, Controle.LIMITE_MOEDAS + 1);
        boolean resultado = controle.insereDinheiro(Moeda.CINQUENTA_CENTAVOS);
        assertFalse(resultado);
    }

    @Test
    public void testInsereDinheiroVariasMoedas() {
        entradaDinheiro.simulaContagemMoedas(Moeda.CINQUENTA_CENTAVOS, 4);
        controle.insereDinheiro(Moeda.CINQUENTA_CENTAVOS);
        controle.insereDinheiro(Moeda.VINTE_E_CINCO_CENTAVOS);
        controle.insereDinheiro(Moeda.DEZ_CENTAVOS);
        controle.insereDinheiro(Moeda.UM_REAL);
        assertEquals(1.85, controle.valorAcumulado, 0.01);
    }

    @Test
    public void testInsereDinheiroMoedaInvalida() {
        entradaDinheiro.simulaErroSimulandoMoedaInvalida();  // Método fictício para simular erro
        boolean resultado = controle.insereDinheiro(Moeda.CINQUENTA_CENTAVOS);
        assertFalse(resultado);
    }

 
    

    @Test
    public void testExcedeLimiteMoedas() {
        entradaDinheiro.simulaContagemMoedas(Moeda.UM_REAL, Controle.LIMITE_MOEDAS + 1);
        boolean resultado = controle.insereDinheiro(Moeda.UM_REAL);
        assertFalse(resultado);
    }

  

    @Test
    public void testSelecionarBebida() throws SemProdutoDisponivelException, SinalInvalidoException {
        controle.insereDinheiro(Moeda.UM_REAL);
        controle.insereDinheiro(Moeda.UM_REAL);
        controle.insereDinheiro(Moeda.CINQUENTA_CENTAVOS);
        controle.insereDinheiro(Moeda.DEZ_CENTAVOS);

        assertEquals(2.60, controle.valorAcumulado, 0.01);

        controle.selecionarBebida(Teclado.BOTAO_COCA_COLA);
        assertEquals(0.00, controle.valorAcumulado, 0.01);
    }

  
}
