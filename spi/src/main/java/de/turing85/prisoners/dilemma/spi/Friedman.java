package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;

public class Friedman implements Strategy {
  private boolean cooperative = true;

  @Override
  public String name() {
    return "Friedman";
  }

  @Override
  public void startGame() {
    cooperative = true;
  }

  @Override
  public Response playRound() {
    return cooperative ? Response.COOPERATE : Response.DEFECT;
  }

  @Override
  public void endRound(Response enemyResponse) {
    if (enemyResponse == Response.DEFECT) {
      cooperative = false;
    }
  }
}
