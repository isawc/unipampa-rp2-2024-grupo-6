import excecoes.DispenserEmperradoException;
import io.Teclado;
import io.Saida;
import maquinario.Dispenser;
import maquinario.Cofre;
import io.Entrada;
import maquinario.EntradaEDispenserDeDinheiro;
import maquinario.Moeda;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Controlador;
import logica.Notificavel;
import logica.Operacional;

public class Controle implements Controlador, Notificavel, Operacional{
    private Dispenser bebidas;
    private Cofre cofre;
    private Saida saida;
    private Entrada entrada;
    //public EntradaEDispenserDeDinheiro entradaDinheiro;
    public boolean emManutencao = false;
    public double valorAcumulado = 0.0;
    public static final int LIMITE_MOEDAS = 30;

    private static final double PRECO_COCA_COLA = 2.60;
    private static final double PRECO_COCA_LIGTH = 2.95;
    private static final double PRECO_COCA_ZERO = 3.10;
    private static final double PRECO_FANTA_LARANJA = 1.95;
    private static final double PRECO_FANTA_UVA = 1.95;
    private static final double PRECO_KUAT_GUARANA = 1.75;
    private static final double PRECO_SCHWEPPES = 2.75;
    private static final double PRECO_SCHWEPPES_CITRUS = 2.85;
    private static final double PRECO_SPRITE = 2.25;
    private static final double PRECO_SPRITE_ZERO = 2.90;
    

    public Controle(Dispenser bebidas, Cofre cofre, Saida saida, Entrada entrada) {
        this.bebidas = bebidas;
        this.cofre = cofre;
        this.saida = saida;
        this.entrada = entrada;
        //this.entradaDinheiro = entradaDinheiro;
        
        saida.mostrarMensagem("PRONTO");
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

    @Override
    public void notificaDinheiroInserido() {
        valorAcumulado = this.cofre.contarMoedasEntrada(Moeda.UM_REAL) * 1
                + this.cofre.contarMoedasEntrada(Moeda.CINCO_CENTAVOS) * 0.05 
                + this.cofre.contarMoedasEntrada(Moeda.CINQUENTA_CENTAVOS) * 0.50
                + this.cofre.contarMoedasEntrada(Moeda.DEZ_CENTAVOS) * 0.10
                + this.cofre.contarMoedasEntrada(Moeda.VINTE_E_CINCO_CENTAVOS) * 0.25;
        String mensagem = (valorAcumulado == 0) ? "PRONTO" : "R$" + String.format(Locale.US, "%.2f", valorAcumulado);
        saida.mostrarMensagem(mensagem);
    }

    @Override
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

    @Override
    public void notificaFechamentoDaPortinha() {
        
    }

    @Override
    public void notificaAberturaDaPortinha() {
        
    }

    @Override
    public void notificaMoedasPegas() {
        
    }

    @Override
    public boolean isEmManutencao() {
        if (this.emManutencao) {
            saida.mostrarMensagem("EM MANUTENÇÃO");
            return true;
        } else{
            return false;
        }
    }
}
