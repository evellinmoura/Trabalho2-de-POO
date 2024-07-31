package obstaculos;
import robo.Robo;
/* Crie uma classe abstrata Osbtaculo que possui como atributo um id e o método
abstrato bater. Crie pelo menos duas classes concretas de obstáculos, Bomba e Rocha,
sendo que na primeira o método bater implica em o robô que encostou nela explode
(desaparece do jogo ou não anda mais), enquanto na segunda o método bater faz o robô
voltar para a posição anterior. Ao explodir a bomba desaparece do tabuleiro.
Crie uma classe Main que instancie um robô normal e outro inteligente, peça ao usuário
para entrar com a posição do alimento e inserir algumas bombas e rochas no tabuleiro, e
faça os dois robôs se moverem randomicamente, um de cada vez, até que um deles
encontre o alimento ou ambos explodam. Ao final, mostre o número de movimentos que
cada robô fez para encontrar o alimento ou até explodir. */
public class Rocha extends Obstaculos {
    private int posiAntX;
    private int posiAntY;
    public Rocha(int id){
        super(id);
    }

    
    @Override
    public void bater(Robo robo){
        robo.setPosiX(posiAntX);
        robo.setPosiY(posiAntY);
        System.out.println("O " + robo.getNome() + robo.getCor() + "bateu na rocha! retornou à posição anterior.");
    }

    public void posiAnterior(int x, int y){
        this.setPosiAntX(x);
        this.setPosiAntY(y);
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

    

