import java.util.List;
import java.util.ArrayList;
import logica.Notificavel;

public class NotificacaoNew implements Notificavel {
    private List<String> notificacoes = new ArrayList<>();
    private boolean dinheiroPressionado = false;
    private boolean botaoPressionado = false;

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public boolean isDinheiroPressionado() {
        return dinheiroPressionado;
    }

    public boolean isBotaoPressionado() {
        return botaoPressionado;
    }

    @Override
    public void notificaDinheiroInserido() {
        dinheiroPressionado = true;
        notificacoes.add("Dinheiro inserido.");
    }

    @Override
    public void notificaBotaoPressionado() {
        botaoPressionado = true;
        notificacoes.add("Botão pressionado.");
    }

    public void resetarBotao() {
        notificacoes.clear();
        dinheiroPressionado = false;
        botaoPressionado = false;
    }

    @Override
    public void notificaFechamentoDaPortinha() {
        // Implementando a notificação do fechamento da portinha
        notificacoes.add("Portinha fechada.");
    }

    @Override
    public void notificaAberturaDaPortinha() {
        // Implementando a notificação da abertura da portinha
        notificacoes.add("Portinha aberta.");
    }

    @Override
    public void notificaMoedasPegas() {
        // Implementando a notificação para as moedas retiradas
        notificacoes.add("Moedas retiradas.");
    }
}
