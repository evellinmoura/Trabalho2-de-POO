package main;

import java.util.Scanner;
import robo.Robo;
import exceptions.MovimentoInvalidoException;

/*Crie uma classe Main que instancie um robô, peça ao usuário para determinar a
posição do alimento, e peça ao usuário para ficar movendo o robô até ele encontrar o
alimento – não esqueça de tratar a exceção. */

    
public class Main {
    //deixa colorido
    private static final String resetColor = "\033[0m";
    // private static final String greenColor = "\033[32m";
    // private static final String blueColor = "\033[34m";
    private static final String redColor = "\033[31m";
    private static final String yellowColor = "\033[33m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo robo = new Robo("Vermelho");
        char [][] matriz = new char[4][4]; //iniciar um unico caractere como posiçoes da matriz
        // inicializa a matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = '-';
            }
        }

        while (true) {
            try {
                // pede ao usuário para definir a posição do alimento
                System.out.print("Digite a posição X do alimento: ");
                int posiAlimentoX = scanner.nextInt();
                System.out.print("Digite a posição Y do alimento: ");
                int posiAlimentoY = scanner.nextInt();
                if (posiAlimentoX < 0 || posiAlimentoY < 0) {
                    throw new IllegalArgumentException("A posição do alimento não pode ser negativa.");
                }
                robo.definirPosicaoAlimento(posiAlimentoX, posiAlimentoY);
                matriz[posiAlimentoY][posiAlimentoX] = 'A'; // Define o alimento na matrizX, posiAlimentoY);
                break; // sai do loop se a posição for válida
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        matriz[robo.getPosiY()][robo.getPosiX()] = 'R'; //atualiza o robo na nova posicao
        scanner.nextLine(); // consome a nova linha

        // continuar pedindo para o usuário mover o robô ate encontrar o alimento
        while (!robo.encontrouAlimento()) {
            try {

                System.out.print("Digite a direção para mover o "+robo.getNome()+ redColor+robo.getCor()+ resetColor +"(1:up, 2:down, 3:right, 4:left): ");
                String entrada = scanner.nextLine().trim().toLowerCase();

                // limpa a posição anterior do robô na matriz
                matriz[robo.getPosiY()][robo.getPosiX()] = '-';

                // tenta mover o robô com base na entrada
                if (entrada.matches("\\d+")) {

                    int direcao = Integer.parseInt(entrada);
                    robo.mover(direcao);
                } else {

                    robo.mover(entrada);
                }

                // atualiza a posição do robô na matriz
                matriz[robo.getPosiY()][robo.getPosiX()] = 'R';
                mostrarMatriz(matriz);
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("O robô pegou o alimento!");
        scanner.close();
    }

    
    public static void mostrarMatriz(char[][] matriz){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch(matriz[i][j]){
                    case 'R':
                    System.out.print(redColor + "R" + resetColor);
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

