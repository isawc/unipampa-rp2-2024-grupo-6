
import io.Entrada;
import io.Saida;
import logica.*;
import maquinario.Cofre;
import maquinario.Dispenser;


public class ControladorMaquina implements Controlador, Notificavel, Operacional {

    // descrito na documentação da interface Controlador
    public ControladorMaquina (Dispenser bebidas, Cofre cofre, Saida saida, Entrada entrada){
        
    }
    
    //logica
        // mostrar pronto no display
        
        // contar moedas e mostrar resultado
        
        // receber valor do botão e liberar bebida
        
        // moedas são recolhidas
        
        // liberar bebida
        
        // destravar dispenser de bebida
        
        // calcular e liberar troco usando moedas do cofre
        
        // bebida e dinheiro são retirados pelo comprador
        
        // mostrar pronto no display
    
    @Override
    public void notificaDinheiroInserido() {
        
    }

    @Override
    public void notificaBotaoPressionado() {
        
    }

    @Override
    public boolean isEmManutencao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // Moedas de 5, 10, 25, 50 centavos e 1 real
    // Moedas do reservatorio são usadas de troco
    // maximo de 2.000 moedas de cada tipo
    // maximo 100 latas de cada tipo
    // moedas recebidas são recolhidas para o cofre apenas apos selecionar uma bebida
    // moedas recebidas não passam pelo cofre em caso de devolução
    // Cabem no maximo 30 moedas na entrada, apartir disso a maquina entope ao engolir ou devolver precisando de manutenção
    // o troco prioriza liberar moedas de maior valor
    // valores financeiros mostrados no display devem possuir R$ sem espaços, usando . para separarcentavos (sempre com 2 digitos):
    // exemplo: R$3.55
    // mensagens de interação e erro, não precisam considerar alinhamento, nem preencher todos o caracteres disponiveis; não devem usar caracteres com acento ou marcações graficas
    // exemplo: BEBIDA INDISPONIVEL
    // maximo de 25 carracteres no display, caso passe do limite o a emnsagem é truncada para 25 caracteres
    // se estiver em falta e tbm n houver dinheiro suficiente para ela, a mensagem no display é bebida indisponivel e não dinheiro insuficiente
    // situações de informação no display: pronta para uso, contagem do dinheiro colocado, bebida indisponivel, dinheiro insuficiente, e mensagens de erro. em outras casso havera uma mensagem sem caracteres.
    
}
