import excecoes.DispenserEmperradoException;
import excecoes.EntradaDeDinheiroEntupidaException;
import excecoes.SemMoedasSuficientesException;
import excecoes.SemProdutoDisponivelException;
import excecoes.SinalInvalidoException;
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
import maquinario.DispenserDeBebidas;

public class Controle implements Controlador, Notificavel, Operacional{
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
    
    Dispenser bebidas;
    Cofre cofre;
    Saida saida;
    Teclado entrada;
    //

    public Controle(Dispenser bebidas, Cofre cofre, Saida saida, Teclado entrada) {
        this.bebidas = bebidas;
        this.cofre = cofre;
        this.saida = saida;
        this.entrada = entrada;
        
        saida.mostrarMensagem("PRONTO");
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
        
        try {
            bebidas.travar();
        } catch (DispenserEmperradoException ex) {
            saida.mostrarMensagem("PROBLEMA NO DISPENSER");
        }
        
        try {
            cofre.engolirDinheiro();
        } catch (EntradaDeDinheiroEntupidaException ex) {
            emManutencao = true;
        }
        
        int bebida;
        switch (entrada.getUltimoBotaoPressionado()) {
            case 17:
                bebida = 211;
                break;
            case 37:
                bebida = 199;
                break;
            
            default:
                throw new AssertionError();
        }
        
        try {
            bebidas.liberarProduto(bebida);
        } catch (SemProdutoDisponivelException ex) {
            saida.mostrarMensagem("BEBIDA INDISPONIVEL");
        } catch (SinalInvalidoException ex) {
            saida.mostrarMensagem("ESCOLHA UMA BEBIDA");
        }
        
        try {
            bebidas.destravar();
        } catch (DispenserEmperradoException ex) {
            saida.mostrarMensagem("PROBLEMA NO DISPENSER");
        }
        
        double troco;
        switch (entrada.getUltimoBotaoPressionado()) {
            case DispenserDeBebidas.COCA_COLA:
                troco = valorAcumulado - PRECO_COCA_COLA;
                break;
            case DispenserDeBebidas.COCA_LIGHT:
                troco = valorAcumulado - PRECO_COCA_LIGTH;
                break;
            case DispenserDeBebidas.COCA_ZERO:
                troco = valorAcumulado - PRECO_COCA_ZERO;
                break;
            case DispenserDeBebidas.FANTA_LARANJA:
                troco = valorAcumulado - PRECO_FANTA_LARANJA;
                break;
            case DispenserDeBebidas.FANTA_UVA:
                troco = valorAcumulado - PRECO_FANTA_UVA;
                break;
            case DispenserDeBebidas.KUAT_GUARANA:
                troco = valorAcumulado - PRECO_KUAT_GUARANA;
                break;
            case DispenserDeBebidas.SCHWEPPES:
                troco = valorAcumulado - PRECO_SCHWEPPES;
                break;
            case DispenserDeBebidas.SCHWEPPES_CITRUS:
                troco = valorAcumulado - PRECO_SCHWEPPES_CITRUS;
                break;
            case DispenserDeBebidas.SPRITE:
                troco = valorAcumulado - PRECO_SPRITE;
                break;
            case DispenserDeBebidas.SPRITE_ZERO:
                troco = valorAcumulado - PRECO_SPRITE_ZERO;
                break;
            case DispenserDeBebidas.NENHUMA_BEBIDA:
                troco = valorAcumulado;
            default:
                troco = valorAcumulado;
        }
        
        double i = troco;
        while (i > 0) { // > n roda, < roda
            if(i >= 1 ){
                try {
                    cofre.darTroco(Moeda.UM_REAL, 1);
                    i = i - 1;
                } catch (SemMoedasSuficientesException ex) {
                    saida.mostrarMensagem("");
                }
            }else if(i >= 0.50){
                try {
                    cofre.darTroco(Moeda.CINQUENTA_CENTAVOS, 1);
                    i = i - 0.50;
                } catch (SemMoedasSuficientesException ex) {
                    saida.mostrarMensagem("");
                }
            }else if(i >= 0.25){
                try {
                    cofre.darTroco(Moeda.VINTE_E_CINCO_CENTAVOS, 1);
                    i = i - 0.25;
                } catch (SemMoedasSuficientesException ex) {
                    saida.mostrarMensagem("");
                }
            }else if(i >= 0.10){
                try {
                    cofre.darTroco(Moeda.DEZ_CENTAVOS, 1);
                    i = i - 0.10;
                } catch (SemMoedasSuficientesException ex) {
                    saida.mostrarMensagem("");
                }
            }else if(i >= 0.05){
                try {
                    cofre.darTroco(Moeda.CINCO_CENTAVOS, 1);
                    i = i - 0.05;
                } catch (SemMoedasSuficientesException ex) {
                    saida.mostrarMensagem("");
                }
            }else{
                i = 0;
            }
        }
    }

    @Override
    public void notificaFechamentoDaPortinha() {
        try {
            bebidas.travar();
        } catch (DispenserEmperradoException ex) {
            emManutencao = true;
        }
    }

    @Override
    public void notificaAberturaDaPortinha() {
        //bebidas.destravar();
        if(bebidas.hasProdutoLiberado()){
            
        }else{
            
        }
        
    }

    @Override
    public void notificaMoedasPegas() {
        saida.mostrarMensagem("PRONTO");
    }

    @Override
    public boolean isEmManutencao() {
        if (this.emManutencao) {
            saida.mostrarMensagem("PROBLEMA NO DISPENSER");
            return true;
        } else{
            return false;
        }
    }
}
