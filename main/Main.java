package main;

import java.util.Scanner;
import robo.Robo;
import exceptions.MovimentoInvalidoException;

    
public class Main {
    //deixa colorido
    private static final String resetColor = "\033[0m";
    // private static final String greenColor = "\033[32m";
    // private static final String blueColor = "\033[34m";
    private static final String redColor = "\033[31m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo robo = new Robo("Vermelho");


        while (true) {
            try {
                // Pedir ao usuário para definir a posição do alimento
                System.out.print("Digite a posição X do alimento: ");
                int posiAlimentoX = scanner.nextInt();
                System.out.print("Digite a posição Y do alimento: ");
                int posiAlimentoY = scanner.nextInt();
                if (posiAlimentoX < 0 || posiAlimentoY < 0) {
                    throw new IllegalArgumentException("A posição do alimento não pode ser negativa.");
                }
                robo.definirPosicaoAlimento(posiAlimentoX, posiAlimentoY);
                break; // sai do loop se a posição for válida
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.nextLine(); // Consumir a nova linha

        // Continuar pedindo ao usuário para mover o robô até encontrar o alimento
        while (!robo.encontrouAlimento()) {
            try {
                System.out.print("Digite a direção para mover o " + robo.getNome() + redColor + robo.getCor()+ resetColor+"(up, down, right, left): ");
                String direcao = scanner.nextLine().trim().toLowerCase();
                robo.mover(direcao);
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("O robô encontrou o alimento!");
        scanner.close();
    }
}
