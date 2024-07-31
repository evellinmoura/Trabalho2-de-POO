package main;

import robo.*;
import exceptions.*;
import java.util.Scanner;
import java.util.Random;

public class Main4 {
    // Deixa colorido
    private static final String resetColor = "\033[0m";
    private static final String greenColor = "\033[32m";
    private static final String blueColor = "\033[34m";
    private static final String redColor = "\033[31m";
    private static final String yellowColor = "\033[33m";
    private static final String magentaColor = "\033[35m";

    public static void main(String[] args) {
        Scanner inserir = new Scanner(System.in);
        Random direcao = new Random();
        
        Robo normal = new Robo("Vermelho");
        Robo smart = new RoboInteligente("Azul");
        char[][] matriz = new char[4][4];

        // Inicializa a matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = '-';
            }
        }

        // Define a posição do alimento
        while (true) {
            try {
                System.out.print("Digite a posição X do alimento: ");
                int posiAlimentoX = inserir.nextInt();
                System.out.print("Digite a posição Y do alimento: ");
                int posiAlimentoY = inserir.nextInt();
                if (posiAlimentoX < 0 || posiAlimentoY < 0 || posiAlimentoX > 3 || posiAlimentoY > 3) {
                    throw new IllegalArgumentException("A posição do alimento deve estar entre 0 e 3.");
                }
                normal.definirPosicaoAlimento(posiAlimentoX, posiAlimentoY);
                matriz[posiAlimentoY][posiAlimentoX] = 'A'; // Define o alimento na matriz
                break; // Sai do loop se a posição for válida
            } catch (IllegalArgumentException a) {
                System.out.println(a.getMessage());
            }
            inserir.nextLine();
        }

        // Adiciona bombas
        System.out.println("Adicione duas bombas!");
        int quantBomba = 2;
        if (quantBomba == 2) {
            
            for (int i = 0; i < quantBomba; i++) {
                while (true) {
                    try {
                        System.out.print("Digite a posição X da bomba: ");
                        int posiBombaX = inserir.nextInt();
                        System.out.print("Digite a posição Y da bomba: ");
                        int posiBombaY = inserir.nextInt();
                        if (posiBombaX < 0 || posiBombaY < 0 || posiBombaX > 3 || posiBombaY > 3) {
                            throw new IllegalArgumentException("A posição da bomba deve estar entre 0 e 3.");
                        } else if (matriz[posiBombaY][posiBombaX] == 'A') {
                            throw new IllegalArgumentException("A bomba não pode estar nas mesmas coordenadas do Alimento");
                        }
                        matriz[posiBombaY][posiBombaX] = 'B';
                        break;
                    } catch (IllegalArgumentException b) {
                        System.out.println(b.getMessage());
                    }
                }
            }
        }

        // Adiciona rochas
        System.out.println("Adicione uma rocha!");
       
        int quantRocha = 1;
        if (quantRocha ==1) {
            for (int i = 0; i < quantRocha; i++) {
                while (true) {
                    try {
                        System.out.print("Digite a posição X da Rocha: ");
                        int posiRochaX = inserir.nextInt();
                        System.out.print("Digite a posição Y da Rocha: ");
                        int posiRochaY = inserir.nextInt();
                        if (posiRochaX < 0 || posiRochaY < 0 || posiRochaX > 3 || posiRochaY > 3) {
                            throw new IllegalArgumentException("A posição da rocha deve estar entre 0 e 3.");
                        } else if (matriz[posiRochaY][posiRochaX] == 'A') {
                            throw new IllegalArgumentException("A rocha não pode estar nas mesmas coordenadas do Alimento");
                        } else if (matriz[posiRochaY][posiRochaX] == 'B') {
                            throw new IllegalArgumentException("A rocha não pode estar nas mesmas coordenadas da bomba");
                        }
                        matriz[posiRochaY][posiRochaX] = 'R';
                        break;
                    } catch (IllegalArgumentException r) {
                        System.out.println(r.getMessage());
                    }
                }
            }
        }

        // Posiciona os robôs na matriz
        matriz[normal.getPosiY()][normal.getPosiX()] = 'N';
        matriz[smart.getPosiY()][smart.getPosiX()] = 'S';
        mostrarMatriz(matriz);

        boolean encontrouAlimento = false;
        while (!encontrouAlimento) {
            // Move o robô normal
            try {
                matriz[normal.getPosiY()][normal.getPosiX()] = '-';
                encontrouAlimento = moverRoboAleatoriamente(normal, direcao, matriz);
                matriz[normal.getPosiY()][normal.getPosiX()] = 'N';
                if (encontrouAlimento) {
                    System.out.println("O " + normal.getNome() + " " + normal.getCor() + " encontrou o alimento!");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro de índice fora dos limites ao mover o " + normal.getNome() + " " + normal.getCor() + ": " + e.getMessage());
            }

            mostrarMatriz(matriz);

            // Introduz um atraso de 500 milissegundos
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Move o robô inteligente
            try {
                matriz[smart.getPosiY()][smart.getPosiX()] = '-';
                encontrouAlimento = moverRoboAleatoriamente(smart, direcao, matriz);
                matriz[smart.getPosiY()][smart.getPosiX()] = 'S';
                if (encontrouAlimento) {
                    System.out.println("O " + smart.getNome() + " " + smart.getCor() + " encontrou o alimento!");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro de índice fora dos limites ao mover o " + smart.getNome() + " " + smart.getCor() + ": " + e.getMessage());
            }

            mostrarMatriz(matriz);

            try {
                Thread.sleep(500); // O programa espera 500 milissegundos.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Exibe o número de movimentos de cada robô
        System.out.println("Número de movimentos do robô normal: " + normal.getMovimento());
        System.out.println("Número de movimentos do robô inteligente: " + smart.getMovimento());
        inserir.close();
    }

    public static boolean moverRoboAleatoriamente(Robo robo, Random coord, char[][] matriz) {
        String[] direcoes = {"up", "down", "right", "left"};
        String direcao = direcoes[coord.nextInt(direcoes.length)];
        try {
            robo.mover(direcao);

            // Verifica se o robô colidiu com uma bomba ou rocha
            int x = robo.getPosiX();
            int y = robo.getPosiY();
           
            if (matriz[y][x] == 'B') {
                robo.explodirRobo(); //robo explodiu, logo, passa a ser false
                return false; // esse true indica que encontrou uma bomba.
            } else if (matriz[y][x] == 'R') {
                robo.retornarAnterior();
                matriz[robo.getPosiY()][robo.getPosiX()] = 'N'; // Marca a posição atual do robô na matriz
                return false; // Não encontrou o alimento ainda
            } else if (matriz[y][x] == 'A') {
                System.out.println("O " + robo.getNome() + robo.getCor() + " encontrou o alimento!");
                return true; // Encontrou o alimento
            }
            
            

        } catch (MovimentoInvalidoException m) {
            System.out.println("Movimento inválido: " + m.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Movimento fora dos limites da matriz: " + e.getMessage());
        } return false;
    }
    public static void mostrarMatriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                switch (matriz[i][j]) {
                    case 'R':
                        System.out.print(magentaColor + "R" + resetColor);
                        break;
                    case 'B':
                        System.out.print(redColor + "B" + resetColor);
                        break;
                    case 'A':
                        System.out.print(yellowColor + "A" + resetColor);
                        break;
                    case 'N':
                        System.out.print(blueColor + "N" + resetColor  );
                        break;
                    case 'S':
                        System.out.print(greenColor + "S" + resetColor );
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