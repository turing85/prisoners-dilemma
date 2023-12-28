package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;

public class FlipFlop implements Strategy {
  private boolean cooperate = true;

  @Override
  public String name() {
    return "Flip Flop";
  }

  @Override
  public void startGame() {
    cooperate = true;
  }

  @Override
  public Response playRound() {
    return cooperate ? Response.COOPERATE : Response.DEFECT;
  }

  @Override
  public void endRound(Response ignored) {
    cooperate = !cooperate;
  }
}
