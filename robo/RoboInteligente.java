package robo;
import exceptions.MovimentoInvalidoException;    
import java.util.Random;
    
public class RoboInteligente extends Robo{
    private String movInvalido;
    private Random aleat;
    
    public RoboInteligente(String cor){
        super(cor);
        this.nome = "Robô Inteligente ";
        setMovInvalido(null);
        setAleat(new Random());
        }
    
     @Override
    public void mover(String direcao)throws MovimentoInvalidoException{
        try{
            super.mover(direcao);
            setMovInvalido(null);
    
        } catch(MovimentoInvalidoException erroMov){
            setMovInvalido(direcao); //vai armazenar a direção que foi invalida
            replayMov(); //caso o movimento seja invalido vai acessar esse metodo
        }
    }
    @Override
    public void mover(int direcao)throws MovimentoInvalidoException{
        super.mover(direcao);
    }
        
    //"Neste caso, se o robô fez um movimento inválido, ele realiza outros
    //movimentos até que seja feito um movimento válido." 
    
    //metodo que realiza outros movimentos para obter um válido
    
    public void replayMov()throws MovimentoInvalidoException{
           
        String[] direcao ={ "up","down","right","left"};
        for (int i=0; i < direcao.length; i++){
        //vai armazenar em numSentido a quantidade de direções, que nesse caso sao 4
            int numSentido = aleat.nextInt(direcao.length);
            String mov = direcao[numSentido]; // mov recebe o elemento do array de acordo com a posição;
            if(!mov.equals(getMovInvalido())){
                //nesse if, se mov é diferente do movimento Invalido anterior, ele satisfaz essa condição:
                try{
                        //executa a logica da classe mãe
                    super.mover(mov); // é chamado para processar os movimentos validos.
                    setMovInvalido(null); //se o movimento for valido, reseta o movimento invalido
                    return;
                }catch(MovimentoInvalidoException erroMov ){
    
                }
            }
                //APENAS PARA GARANTIR CASO HAJA ALGUM PROBLEMA, E NENNHUMA SEJA UM MOVIMENTO VÁLIDO: 
        }throw new MovimentoInvalidoException("Todas as direções falharam");
}
    
        
    
        public String getMovInvalido() {
            return movInvalido;
        }
    
        public void setMovInvalido(String movInvalido) {
            this.movInvalido = movInvalido;
        }
    
        public Random getAleat() {
            return aleat;
        }
    
        public void setAleat(Random aleat) {
            this.aleat = aleat;
        }
        
} 

