package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class Prober implements Strategy {
  private final boolean cooperateOnFirstRound;

  private Boolean firstWasCooperate = null;

  @Override
  public void startGame() {
    firstWasCooperate = null;
  }

  @Override
  public Response playRound() {
    return Optional.ofNullable(firstWasCooperate).orElse(cooperateOnFirstRound) ? Response.COOPERATE
        : Response.DEFECT;
  }

  @Override
  public void endRound(Response enemyResponse) {
    if (Objects.isNull(firstWasCooperate)) {
      firstWasCooperate = enemyResponse == Response.COOPERATE;
    }
  }

  public static class Evil extends Prober {
    public Evil() {
      super(false);
    }

    @Override
    public String name() {
      return "Evil Prober";
    }
  }

  public static class Friendly extends Prober {
    public Friendly() {
      super(true);
    }

    @Override
    public String name() {
      return "Friendly Prober";
    }
  }
}
