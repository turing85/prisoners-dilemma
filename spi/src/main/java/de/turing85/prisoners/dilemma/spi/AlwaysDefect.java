package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;

public class AlwaysDefect implements Strategy {
  @Override
  public String name() {
    return "Always Defect";
  }

  @Override
  public void startGame() {
    // NOOP
  }

  @Override
  public Response playRound() {
    return Response.DEFECT;
  }

  @Override
  public void endRound(Response enemyResponse) {
    // NOOP
  }
}
