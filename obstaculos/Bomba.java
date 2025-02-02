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
public class Bomba extends Obstaculos {
    private boolean explodiu;
    public Bomba(int id){
        super(id);
        setExplodiu(false);
    }
    @Override
    public void bater(Robo robo){
        robo.explodirRobo();
        explodirBomba();
       System.out.println("O "+ robo.getNome() + robo.getCor() + " colidiu com a bomba e explodiu!");
    }

    public void explodirBomba(){
        if(getExplodiu()){
            setExplodiu(true);
            
        }
    }
    public boolean getExplodiu() {
        return explodiu;
    }
    public void setExplodiu(boolean explodiu) {
        this.explodiu = explodiu;
    }

    
}
