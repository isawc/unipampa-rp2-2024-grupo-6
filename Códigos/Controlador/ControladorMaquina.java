import io.Teclado;
import io.Saida;
import maquinario.Dispenser;
import maquinario.Cofre;
import io.Entrada;
import maquinario.EntradaEDispenserDeDinheiro;
import maquinario.Moeda;
import java.util.Locale;

public class Controle {
    private Dispenser bebidas;
    private Cofre cofre;
    private Saida saida;
    private Entrada entrada;
    public EntradaEDispenserDeDinheiro entradaDinheiro;
    public boolean emManutencao = false;
    public double valorAcumulado = 0.0;
    public static final int LIMITE_MOEDAS = 30;

    private static final double PRECO_COCA_COLA = 2.60;

    public Controle(Dispenser bebidas, Cofre cofre, Saida saida, Entrada entrada, EntradaEDispenserDeDinheiro entradaDinheiro) {
        this.bebidas = bebidas;
        this.cofre = cofre;
        this.saida = saida;
        this.entrada = entrada;
        this.entradaDinheiro = entradaDinheiro;
    }

    private double obterValorMoeda(Moeda tipo) {
        switch (tipo) {
            case CINQUENTA_CENTAVOS:
                return 0.50;
            case VINTE_E_CINCO_CENTAVOS:
                return 0.25;
            case DEZ_CENTAVOS:
                return 0.10;
            case UM_REAL:
                return 1.00;
            default:
                throw new IllegalArgumentException("Moeda desconhecida: " + tipo);
        }
    }

    private boolean dentroLimiteDeMoedas(Moeda tipo) {
        return entradaDinheiro.contarMoedasEntrada(tipo) <= LIMITE_MOEDAS;
    }

    public boolean insereDinheiro(Moeda tipo) {
        if (emManutencao) {
            saida.mostrarMensagem("PROBLEMA NO DISPENSER");
            return false;
        }
        try {
            entradaDinheiro.simulaColocarMoeda(this, tipo);

            if (!dentroLimiteDeMoedas(tipo)) {
                saida.mostrarMensagem("ENTRADA DE $ ENTUPIDA");
                return false;
            }
            valorAcumulado += obterValorMoeda(tipo);
            notificaDinheiroInserido();
            return true;
        } catch (Exception e) {
            saida.mostrarMensagem("PROBLEMA NO DISPENSER");
            return false;
        }
    }

    public void notificaDinheiroInserido() {
        String mensagem = (valorAcumulado == 0) ? "PRONTO" : "R$" + String.format(Locale.US, "%.2f", valorAcumulado);
        saida.mostrarMensagem(mensagem);
    }

    public void notificaBotaoPressionado() {
        String mensagem = (emManutencao) ? "PROBLEMA NO DISPENSER" : "ESCOLHA UMA BEBIDA";
        saida.mostrarMensagem(mensagem);
    }

    public void iniciarManutencao() {
        emManutencao = true;
        saida.mostrarMensagem("PROBLEMA NO DISPENSER");
    }

    public void encerrarManutencao() {
        emManutencao = false;
        saida.mostrarMensagem("PRONTO");
    }

    public void selecionarBebida(int codigo) {
        double preco = obterValorMoeda(Moeda.CINQUENTA_CENTAVOS);  // Exemplo simplificado, normalmente pega do botão.

        if (valorAcumulado < preco) {
            saida.mostrarMensagem("DINHEIRO INSUFICIENTE");
            return;
        }

        try {
            bebidas.liberarProduto(codigo);
            double troco = valorAcumulado - preco;
            darTroco(troco);
            valorAcumulado = 0.0;
            saida.mostrarMensagem("BEBIDA LIBERADA. TROCO DE R$" + String.format("%.2f", troco) + " entregue.");
        } catch (Exception e) {
            saida.mostrarMensagem("PROBLEMA NO DISPENSER: " + e.getMessage());
        }
    }

    public void darTroco(double troco) {
        if (troco <= 0) {
            return;
        }

        Moeda[] moedas = Moeda.values();
        for (Moeda moeda : moedas) {
            double valorMoeda = obterValorMoeda(moeda);
            int quantidade = (int) (troco / valorMoeda);

            while (quantidade > 0 && dentroLimiteDeMoedas(moeda)) {
                saida.mostrarMensagem("TROCO: " + moeda + " R$" + valorMoeda);
                troco -= valorMoeda;
                quantidade--;
            }
        }

        if (troco > 0) {
            saida.mostrarMensagem("FALTA R$" + String.format("%.2f", troco));
        } else {
            saida.mostrarMensagem("TROCO FINALIZADO");
        }
    }
}
