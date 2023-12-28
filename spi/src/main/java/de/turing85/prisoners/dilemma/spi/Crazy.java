package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;

import java.util.Objects;

public class Crazy implements Strategy {
  private Response lastEnemyResponse = null;

  @Override
  public String name() {
    return "Crazy";
  }

  @Override
  public void startGame() {
    lastEnemyResponse = null;
  }

  @Override
  public Response playRound() {
    if (Objects.isNull(lastEnemyResponse)) {
      return Response.DEFECT;
    } else if (lastEnemyResponse == Response.COOPERATE) {
      return Response.DEFECT;
    } else {
      return Response.COOPERATE;
    }
  }

  @Override
  public void endRound(Response enemyResponse) {
    lastEnemyResponse = enemyResponse;
  }
}
