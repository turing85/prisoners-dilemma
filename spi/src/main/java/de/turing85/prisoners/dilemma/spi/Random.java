package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;

public class Random implements Strategy {
  private static final java.util.Random RNG = new java.util.Random();

  @Override
  public String name() {
    return "Random";
  }

  @Override
  public void startGame() {
    // NOOP
  }

  @Override
  public Response playRound() {
    return RNG.nextBoolean() ? Response.COOPERATE : Response.DEFECT;
  }

  @Override
  public void endRound(Response enemyResponse) {
    // NOOP
  }
}
