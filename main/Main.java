package main;

import exceptions.MovimentoInvalidoException;
import robo.Robo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MovimentoInvalidoException {
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
                break; // Sai do loop se a posição for válida
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.nextLine(); // Consumir a nova linha

        // vai continuar para o usuário mover o robô até achar o alimento
        while (!robo.encontrouAlimento()) {
            System.out.print("Digite a direção para mover o robô -> up (cima) || -> down (baixo) || -> right (direita) || ->left (esquerda): ");
            String direcao = scanner.nextLine().trim().toLowerCase();
            if (direcao.equals("up") || direcao.equals("down") || direcao.equals("right") || direcao.equals("left")) {
                robo.mover(direcao);
            } else {
                System.out.println("Direção inválida. Tente novamente.");
            }
        }

        System.out.println("O robô encontrou o alimento!");
        scanner.close();
    }
}
