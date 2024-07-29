package main;
import exceptions.MovimentoInvalidoException;
import robo.Robo;
public class Main {
  public static void main(String[] args) {
      Robo robo = new Robo("verde");

      try {
        robo.mover("down");
        robo.mover("right");

      } catch (MovimentoInvalidoException e) {
          System.out.println(e.getMessage());
      }
  }
}