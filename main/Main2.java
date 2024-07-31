package main;
import java.util.Random;
import java.util.Scanner;
import robo.Robo;
import exceptions.MovimentoInvalidoException;

public class Main2 {
        //deixa colorido
        private static final String resetColor = "\033[0m";
        // private static final String greenColor = "\033[32m";
        private static final String blueColor = "\033[34m";
        private static final String redColor = "\033[31m";
        private static final String yellowColor = "\033[33m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // instancia dois robôs
        Robo robo1 = new Robo("Vermelho");
        Robo robo2 = new Robo("Azul");

        // inicializar variáveis para contar movimentos válidos e inválidos
        int[] movimentosValidosRobo1 = {0};
        int[] movimentosInvalidosRobo1 = {0};
        int[] movimentosValidosRobo2 = {0};
        int[] movimentosInvalidosRobo2 = {0};

        // inicializa a matriz com espaços vazios
        char[][] matriz = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = '-';
            }
        }

        // pede para o usuário para definir a posição do alimento
        while (true) {
            try {
                System.out.print("Digite a posição X do alimento: ");
                int posiAlimentoX = scanner.nextInt();
                System.out.print("Digite a posição Y do alimento: ");
                int posiAlimentoY = scanner.nextInt();
                if (posiAlimentoX < 0 || posiAlimentoY < 0 || posiAlimentoX > 3 || posiAlimentoY > 3) {
                    throw new IllegalArgumentException("A posição do alimento deve estar entre 0 e 3.");
                }
                robo1.definirPosicaoAlimento(posiAlimentoX, posiAlimentoY);
                robo2.definirPosicaoAlimento(posiAlimentoX, posiAlimentoY);
                matriz[posiAlimentoY][posiAlimentoX] = 'A'; // Define o alimento na matriz
                break; // sai do loop se a posição for válida
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.nextLine(); // Consumir a nova linha

        // atualiza a posição inicial dos robôs na matriz
        matriz[robo1.getPosiY()][robo1.getPosiX()] = 'R';
        matriz[robo2.getPosiY()][robo2.getPosiX()] = 'B';
        mostrarMatriz(matriz);

        // Continuar movendo os robôs aleatoriamente até que um encontre o alimento
        boolean encontrouAlimento = false;
        while (!encontrouAlimento) {
            //move o robo 1
            try {
                matriz[robo1.getPosiY()][robo1.getPosiX()] = '-';
                encontrouAlimento = moverRoboAleatoriamente(robo1, random, movimentosValidosRobo1, movimentosInvalidosRobo1);
                matriz[robo1.getPosiY()][robo1.getPosiX()] = 'R';
                if (encontrouAlimento) {
                    System.out.println("O robô 1 (Vermelho) encontrou o alimento!");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro de índice fora dos limites ao mover robô 1: " + e.getMessage());
            }

            mostrarMatriz(matriz);

            // introduz um atraso de 500 milissegundos
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // move o robo 2
            try {
                matriz[robo2.getPosiY()][robo2.getPosiX()] = '-';
                encontrouAlimento = moverRoboAleatoriamente(robo2, random, movimentosValidosRobo2, movimentosInvalidosRobo2);
                matriz[robo2.getPosiY()][robo2.getPosiX()] = 'B';
                if (encontrouAlimento) {
                    System.out.println("O robô 2 (Azul) encontrou o alimento!");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro de índice fora dos limites ao mover robô 2: " + e.getMessage());
            }

            mostrarMatriz(matriz);


            try {
                Thread.sleep(500); // o programa espera 500 milissegundos.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Movimentos válidos do robô 1: " + movimentosValidosRobo1[0]);
        System.out.println("Movimentos inválidos do robô 1: " + movimentosInvalidosRobo1[0]);
        System.out.println("Movimentos válidos do robô 2: " + movimentosValidosRobo2[0]);
        System.out.println("Movimentos inválidos do robô 2: " + movimentosInvalidosRobo2[0]);

        scanner.close();
    }

    // Método para mover o robô aleatoriamente
    public static boolean moverRoboAleatoriamente(Robo robo, Random random, int[] validos, int[] invalidos) {
        String[] direcoes = {"up", "down", "right", "left"};
        String direcao = direcoes[random.nextInt(direcoes.length)];
        try {
            robo.mover(direcao);
            validos[0]++;
            return robo.encontrouAlimento();
        } catch (MovimentoInvalidoException e) {
            System.out.println("Movimento inválido: " + direcao + " para o robô " + robo.getCor());
            invalidos[0]++;
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