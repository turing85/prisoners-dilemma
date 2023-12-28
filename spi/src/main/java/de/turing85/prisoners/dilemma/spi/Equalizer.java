package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Equalizer implements Strategy {
  private final Supplier<Response> onEqual;

  private int lost = 0;
  private int won = 0;
  private Response lastResponse = null;

  @Override
  public void startGame() {
    lost = 0;
    won = 0;
    lastResponse = null;
  }

  @Override
  public Response playRound() {
    if (won == lost) {
      lastResponse = onEqual.get();
    } else if (won > lost) {
      lastResponse = Response.COOPERATE;
    } else {
      lastResponse = Response.DEFECT;
    }
    return lastResponse;
  }

  @Override
  public void endRound(Response enemyResponse) {
    if (lastResponse == Response.COOPERATE && enemyResponse == Response.DEFECT) {
      ++lost;
    } else if (lastResponse == Response.DEFECT && enemyResponse == Response.COOPERATE) {
      ++won;
    }
  }

  public static class Evil extends Equalizer {
    public Evil() {
      super(() -> Response.DEFECT);
    }

    @Override
    public String name() {
      return "Evil Equalizer";
    }
  }

  public static class Friendly extends Equalizer {
    public Friendly() {
      super(() -> Response.COOPERATE);
    }

    @Override
    public String name() {
      return "Friendly Equalizer";
    }
  }

  public static class Random extends Equalizer {
    private static final java.util.Random RNG = new java.util.Random();

    public Random() {
      super(() -> RNG.nextBoolean() ? Response.COOPERATE : Response.DEFECT);
    }

    @Override
    public String name() {
      return "Random Equalizer";
    }
  }
}
