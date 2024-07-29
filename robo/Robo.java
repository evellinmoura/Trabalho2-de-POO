package robo;

import exceptions.MovimentoInvalidoException;

public class Robo {
    protected int posiX;
    protected int posiY;
    protected String cor;
    protected int movimento;

    // construtor
    public Robo(String cor) {
        this.posiX = 0;
        this.posiY = 0;
        this.cor = cor;
        this.movimento = 0;
    }

    // gt e stt
    public int getPosiX() {
        return posiX;
    }

    public void setPosiX(int posiX) {
        this.posiX = posiX;
    }

    public int getPosiY() {
        return posiY;
    }

    public void setPosiY(int posiY) {
        this.posiY = posiY;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getMovimento() {
        return movimento;
    }

    public void setMovimento(int movimento) {
        this.movimento = movimento;
    }

    // metodo mover com a string
    public void mover(String direcao) throws MovimentoInvalidoException {// recebe a direcao e pode disparar uma exceção 
        switch (direcao) {
            case "up":// robo vai pro eixo y
                if (posiY + 1 < 0) throw new MovimentoInvalidoException("up");// caso for negativo, ele lanca MovimentoInvalidoException
                posiY += 1;
                break;
            case "down":
                if (posiY - 1 < 0) throw new MovimentoInvalidoException("down");
                posiY -= 1;
                break;
            case "right": // o robo vai pro eixo x
                if (posiX + 1 < 0) throw new MovimentoInvalidoException("right");
                posiX += 1;
                break;
            case "left":
                if (posiX - 1 < 0) throw new MovimentoInvalidoException("left");
                posiX -= 1;
                break;
            default:
                throw new MovimentoInvalidoException(direcao);
        }
        System.out.println("Posicao do robo: (" + posiX + ", " + posiY + ")");
    }

    // sobrecarrega o metodo mover com inteiro
    public void mover(int direcao) throws MovimentoInvalidoException {
        switch (direcao) {
            case 1: // vai pra cima
                mover("up");
                break;
            case 2://vai pra baixo 
                mover("down");
                break;
            case 3: //vai pra direita 
                mover("right");
                break;
            case 4: //vai pra esquerda
                mover("left");
                break;
            default:
                throw new MovimentoInvalidoException("direcao invalida: " + direcao);
        }
    }
}
