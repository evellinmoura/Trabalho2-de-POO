package robo;
import exceptions.MovimentoInvalidoException;
import obstaculos.Obstaculos;


public class Robo {
    protected int posiX;
    protected int posiY;
    protected int posiAntX;
    protected int posiAntY;
    protected String cor;
    protected String nome;
    protected int movimento;
    protected int posiAtual;
    private int posiAlimentoX;// posicao x do alimento
    private int posiAlimentoY;// posicao y do alimento
    protected boolean roboVivo;

    // construtor
    public Robo(String cor) {
        this.posiX = 0;
        this.posiY = 0;
        this.posiAntX = 0;
        this.posiAntY = 0;
        this.cor = cor;
        this.nome = "Robô Normal ";
        this.movimento = 0;
        this.posiAlimentoX = -1;// para evitar que o robo não encontre na primeira verificacao
        this.posiAlimentoY = -1;
        this.roboVivo=true;
    }

    

    // metodo mover com a string
   // recebe a direcao e pode disparar uma exceção 
    public void mover(String direcao) throws MovimentoInvalidoException {
        if (!roboVivo) {
            System.out.println("O robô não está vivo e não pode se mover.");
            return;
        }
       

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
        this.setMovimento(getMovimento()+1);
        System.out.println(" A posicao do "+this.getNome()+ this.getCor()+" é ( " + posiX + ", " + posiY + ")");        
    }

    // sobrecarrega o metodo mover com inteiro
    public void mover(int direcao) throws MovimentoInvalidoException {
        if( this.getRoboVivo()){
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
    public void guardarPosi(){
        this.posiAntX = this.posiX;
        this.posiAntY = this.posiY;
    }
    public void retornarAnterior(){//atualiza a posição para a antiga
        this.posiX = posiAntX;
        this.posiY = posiAntY;
    }

    public void bater(Obstaculos obstaculo) {
        obstaculo.bater(this);
    }

    

    public void explodirRobo(){
        setRoboVivo(false);
        System.out.println("O "+ this.getNome() +this.getCor()+ " explodiu com a bomba");
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

    public int getMovimento() {
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


    public int getPosiAtual() {
        return posiAtual;
    }
    public void setPosiAtual(int posiAtual) {
        this.posiAtual = posiAtual;
    }



    public boolean getRoboVivo() {
        return roboVivo;
    }
    public void setRoboVivo(boolean roboVivo) {
        this.roboVivo = roboVivo;
    }



    public int getPosiAntX() {
        return posiAntX;
    }



    public void setPosiAntX(int posiAntX) {
        this.posiAntX = posiAntX;
    }



    public int getPosiAntY() {
        return posiAntY;
    }



    public void setPosiAntY(int posiAntY) {
        this.posiAntY = posiAntY;
    }
    

    

}



