package robo;
import exceptions.MovimentoInvalidoException;

public class Robo {
    protected int posiX;
    protected int posiY;
    protected String cor;
    protected String nome;
    protected int movimento;
    protected int mov_vali;
    protected int mov_inv;
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

    

    // metodo mover com a string
   // recebe a direcao e pode disparar uma exceção 
        public void mover(String direcao) throws MovimentoInvalidoException {
            switch (direcao) {
                case "up": // Robo vai para o eixo Y positivo (para cima)
                    if (posiY + 1 > 3){
                        throw new MovimentoInvalidoException("up");
                    }  // Se o movimento ultrapassar o limite superior, lança exceção
                    posiY += 1;
                break;
                case "down": // Robo vai para o eixo Y negativo (para baixo)
                    if (posiY - 1 < 0) {
                        throw new MovimentoInvalidoException("down");
                    } // Se o movimento ultrapassar o limite inferior, lança exceção
                    posiY -= 1;
                break;
                case "right": // Robo vai para o eixo X positivo (para a direita)
                    if (posiX + 1 > 3){
                        throw new MovimentoInvalidoException("right");
                    }  // Se o movimento ultrapassar o limite direito, lança exceção
                    posiX += 1;
                break;
                case "left": // Robo vai para o eixo X negativo (para a esquerda)
                    if (posiX - 1 < 0){
                        throw new MovimentoInvalidoException("left");
                        } 
                    posiX-=1;
                break;
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
    public void retornarAnterior(){

    }
    public void removerRobo(){

    }
 
    public void definirPosicaoAlimento(int posiAlimentoX, int posiAlimentoY) {
        this.posiAlimentoX = posiAlimentoX;
        this.posiAlimentoY = posiAlimentoY;
        System.out.println("Posição do alimento definida: (" + posiAlimentoX + ", " + posiAlimentoY + ")");
    }

    // Método para verificar se o robô encontrou o alimento
    public boolean encontrouAlimento() {
        return this.posiX == this.posiAlimentoX && this.posiY == this.posiAlimentoY;

    }
    public void TabelaMov(){
        System.out.println("---------- QUANTIDADE DE MOVIMENTOS ----------\n");
        System.out.println(getNome() + getCor() + " realizou " + getMov_vali() + " movimentos válidos e " + getMov_inv() + " movimentos inválidos\n");
        System.out.println("----------------------------------------------\n");
    }

    //GETTERS E SETTERS
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

    // public double getMovimento() {
    //     return movimento;
    // }
    // public void setMovimento(int movimento) {
    //     this.movimento = movimento;
    // }


    public int getMov_vali() {
        return mov_vali;
    }
    public void setMov_vali(int mov_vali) {
        this.mov_vali = mov_vali;
    }


    public int getMov_inv() {
        return mov_inv;
    }
    public void setMov_inv(int mov_inv) {
        this.mov_inv = mov_inv;
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



