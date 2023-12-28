package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;

public class AlwaysCooperate implements Strategy {
  @Override
  public String name() {
    return "Always Cooperate";
  }

  @Override
  public void startGame() {
    // NOOP
  }

  @Override
  public Response playRound() {
    return Response.COOPERATE;
  }

  @Override
  public void endRound(Response enemyResponse) {
    // NOOP
  }
}
