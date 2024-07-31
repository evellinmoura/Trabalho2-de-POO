
/*Crie uma subclasse RoboInteligente que sobrescreve o método mover de forma que
se robô fez um movimento inválido, garanta que o próximo movimento não será o
mesmo. Neste caso, se o robô fez um movimento inválido, ele realiza outros
movimentos até que seja feito um movimento válido.

Crie uma classe Main que instancie um robô normal e outro inteligente, peça ao usuário
para entrar com a posição do alimento, e faça os dois robôs se moverem
randomicamente, um de cada vez, até que ambos encontrem o alimento. Ao final,
mostre o número de movimentos que cada robô fez para encontrar o alimento.
Para todos os itens anteriores, mostre os robôs se movendo na em uma matriz que
representa a área de locomoção. Mostre também o alimento na posição indicada pelo
usuário. Considere a área fixa de um quadrado com 4 unidades de lado. */
package main;
import java.util.Random;
import java.util.Scanner;
import robo.Robo;
import robo.RoboInteligente;
import exceptions.MovimentoInvalidoException;

public class Main3 {
       // Deixa colorido
       private static final String resetColor = "\033[0m";
       //private static final String greenColor = "\033[32m";
       private static final String blueColor = "\033[34m";
       private static final String redColor = "\033[31m";
       private static final String yellowColor = "\033[33m";
       //private static final String magentaColor = "\033[35m";
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // instancia um robô normal e um robô inteligente
        Robo roboNormal = new Robo("Vermelho");
        RoboInteligente roboInteligente = new RoboInteligente("Azul");

        // inicializa variáveis para contar movimentos válidos
        int movimentosRoboNormal = 0;
        int movimentosRoboInteligente = 0;

        // inicializa a matriz com espaços vazios
        char[][] matriz = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = '-';
            }
        }

        // pede ao usuário para definir a posição do alimento
        while (true) {
            try {
                System.out.print("Digite a posição X do alimento: ");
                int posiAlimentoX = scanner.nextInt();
                System.out.print("Digite a posição Y do alimento: ");
                int posiAlimentoY = scanner.nextInt();
                if (posiAlimentoX < 0 || posiAlimentoY < 0 || posiAlimentoX > 3 || posiAlimentoY > 3) {
                    throw new IllegalArgumentException("A posição do alimento deve estar entre 0 e 3.");
                }
                roboNormal.definirPosicaoAlimento(posiAlimentoX, posiAlimentoY);
                roboInteligente.definirPosicaoAlimento(posiAlimentoX, posiAlimentoY);
                matriz[posiAlimentoY][posiAlimentoX] = 'A'; // define o alimento na matriz
                break; // sai do loop se a posição for válida
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.nextLine(); // Consumir a nova linha

        // atualiza a posição inicial dos robôs na matriz
        matriz[roboNormal.getPosiY()][roboNormal.getPosiX()] = 'R';
        matriz[roboInteligente.getPosiY()][roboInteligente.getPosiX()] = 'B';
        mostrarMatriz(matriz);

        // continua movendo os robôs aleatoriamente até que ambos encontrem o alimento
        boolean encontrouAlimentoRoboNormal = false;
        boolean encontrouAlimentoRoboInteligente = false;

        while (!encontrouAlimentoRoboNormal || !encontrouAlimentoRoboInteligente) {// false || true = true ele vai continuar 
            if (!encontrouAlimentoRoboNormal) {
                matriz[roboNormal.getPosiY()][roboNormal.getPosiX()] = '-';
                encontrouAlimentoRoboNormal = moverRoboAleatoriamente(roboNormal, random);// move o robo
                matriz[roboNormal.getPosiY()][roboNormal.getPosiX()] = 'R';
                movimentosRoboNormal++;//contator de movimento
                if (encontrouAlimentoRoboNormal) {
                    System.out.println("O robô 1 (Vermelho) encontrou o alimento!");
                }
            }

            mostrarMatriz(matriz);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!encontrouAlimentoRoboInteligente) {
                matriz[roboInteligente.getPosiY()][roboInteligente.getPosiX()] = '-';
                encontrouAlimentoRoboInteligente = moverRoboAleatoriamente(roboInteligente, random);// atualiza a variavel com true se o robô encontrou
                matriz[roboInteligente.getPosiY()][roboInteligente.getPosiX()] = 'B';
                movimentosRoboInteligente++; //contator de movimento
                if (encontrouAlimentoRoboInteligente) {
                    System.out.println("O robô 2 (Azul) encontrou o alimento!");
                }
            }

            mostrarMatriz(matriz);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Movimentos do robô 1 (Normal): " + movimentosRoboNormal);
        System.out.println("Movimentos do robô 2 (Inteligente): " + movimentosRoboInteligente);

        scanner.close();
    }

    public static boolean moverRoboAleatoriamente(Robo robo, Random random) {
        String[] direcoes = {"up", "down", "right", "left"};
        String direcao = direcoes[random.nextInt(direcoes.length)];
        try {
            robo.mover(direcao);
            return robo.encontrouAlimento();
        } catch (MovimentoInvalidoException e) {
            System.out.println("Movimento inválido: " + direcao + " do robô " + robo.getCor());
            return false;
        }
    }

    public static void mostrarMatriz(char[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                switch(matriz[i][j]){
                    case 'R':
                    System.out.print(redColor + "R" + resetColor);
                    break;

                    case 'B':
                    System.out.print(blueColor + "B" + resetColor);
                    break;
                    case 'A':
                    System.out.print(yellowColor + "A" + resetColor);
                    break;
                    default: 
                    System.out.print(matriz[i][j] + " ");
                    break;
                }
            }
            System.out.println();
        }
    }
}