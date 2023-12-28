package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;

public class FlopFlip implements Strategy {
  private boolean cooperate = false;

  @Override
  public String name() {
    return "Flop Flip";
  }

  @Override
  public void startGame() {
    cooperate = false;
  }

  @Override
  public Response playRound() {
    return cooperate ? Response.COOPERATE : Response.DEFECT;
  }

  @Override
  public void endRound(Response enemyResponse) {
    cooperate = !cooperate;
  }
}
