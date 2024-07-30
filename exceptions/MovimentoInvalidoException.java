
package exceptions;

public class MovimentoInvalidoException extends Exception {
    //construtor
    public MovimentoInvalidoException(String movimento) {
        super("Fez um movimento invalido: " + movimento);//explica qual movimento foi inválido
    }
}

