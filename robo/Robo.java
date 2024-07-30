package robo;
import exceptions.MovimentoInvalidoException;

public class Robo {
    protected int posiX;
    protected int posiY;
    protected String cor;
    protected String nome;
    protected int movimento;
    private int posiAlimentoX;// posicao x do alimento
    private int posiAlimentoY;// posicao y do alimento

    // construtor
    public Robo(String cor) {
        this.posiX = 0;
        this.posiY = 0;
        this.cor = cor;
        this.nome = "Robô Normal ";
        this.movimento = 0;
        this.posiAlimentoX = -1;// para evitar que o robo não encontre na primeira verificacao
        this.posiAlimentoY = -1;
    }

    // gt e stt
    

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
        System.out.println(" A posicao do "+this.getNome()+ this.getCor()+" é ( " + posiX + ", " + posiY + ")");
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

   /* //define a posicao do alimento
    public void definirPosicaoAlimento(int posiAlimentoX, int poisAlimentoY) throws PosicaoInvalidaException {
        if (posiAlimentoX < 0 || posiAlimentoY < 0) {
            throw new PosicaoInvalidaException("A posição do alimento nao pode ser negativa: (" + posiAlimentoX + ", " + posiAlimentoY + ")");
        }
        this.posiAlimentoX = posiAlimentoX;
        this.posiAlimentoY = posiAlimentoY;
    }

    // Método para verificar se o robô encontrou o alimento
    public boolean encontrouAlimento() {
        if (this.posiAlimentoX == -1 || this.posiAlimentoY == -1) {
            // A posição do alimento não foi definida
            return false;
        }
        return this.posiX == this.posiAlimentoX && this.posiY == posiAlimentoY;
    }*/

    public void definirPosicaoAlimento(int posiAlimentoX, int posiAlimentoY) {
        this.posiAlimentoX = posiAlimentoX;
        this.posiAlimentoY = posiAlimentoY;
        System.out.println("Posição do alimento definida: (" + posiAlimentoX + ", " + posiAlimentoY + ")");
    }

    // Método para verificar se o robô encontrou o alimento
    public boolean encontrouAlimento() {
        return this.posiX == this.posiAlimentoX && this.posiY == this.posiAlimentoY;

    }

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


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMovimento() {
        return movimento;
    }
    public void setMovimento(int movimento) {
        this.movimento = movimento;
    }


    public int getPosiAlimentoX() {
        return posiAlimentoX;
    }
    public void setPosiAlimentoX(int posiAlimentoX) {
        this.posiAlimentoX = posiAlimentoX;
    }

    
    public int getPosiAlimentoY() {
        return posiAlimentoY;
    }
    public void setPosiAlimentoY(int posiAlimentoY) {
        this.posiAlimentoY = posiAlimentoY;
    }


}



