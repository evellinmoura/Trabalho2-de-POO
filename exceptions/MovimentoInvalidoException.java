
package exceptions;

public class MovimentoInvalidoException extends Exception {
    //construtor
    public MovimentoInvalidoException(String movimento) {
        super("Movimento invalido: " + movimento);//explica qual movimento foi inválido
    }
}

