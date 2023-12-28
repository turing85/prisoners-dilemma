package de.turing85.prisoners.dilemma.spi;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class NTitsForMTats implements Strategy {
  private final int breakPoint;
  private final int retaliate;

  private int retaliationCounter = 0;
  private int defectsInSuccession = 0;

  @Override
  public void startGame() {
    defectsInSuccession = 0;
    retaliationCounter = 0;
  }

  @Override
  public Response playRound() {
    return retaliationCounter <= 0 ? Response.COOPERATE : Response.DEFECT;
  }

  @Override
  public void endRound(Response enemyResponse) {
    if (enemyResponse == Response.DEFECT) {
      ++defectsInSuccession;
      if (retaliationCounter > 0 || defectsInSuccession > breakPoint) {
        retaliationCounter += retaliate;
      }
    } else {
      defectsInSuccession = 0;
      retaliationCounter = Math.max(0, retaliationCounter - 1);
    }
  }

  public static class TitForTat extends NTitsForMTats {
    public TitForTat() {
      super(1, 1);
    }

    @Override
    public String name() {
      return "Tit For Tat";
    }
  }

  public static class TitForTwoTats extends NTitsForMTats {
    public TitForTwoTats() {
      super(2, 1);
    }

    @Override
    public String name() {
      return "Tit For Two Tats";
    }
  }

  public static class TitForThreeTats extends NTitsForMTats {
    public TitForThreeTats() {
      super(3, 1);
    }

    @Override
    public String name() {
      return "Tit For Three Tats";
    }
  }

  public static class TwoTitsForTat extends NTitsForMTats {
    public TwoTitsForTat() {
      super(1, 2);
    }

    @Override
    public String name() {
      return "Two Tits For Tat";
    }
  }

  public static class TwoTitsForTwoTats extends NTitsForMTats {
    public TwoTitsForTwoTats() {
      super(2, 2);
    }

    @Override
    public String name() {
      return "Two Tits For Two Tats";
    }
  }

  public static class ThreeTitsForTat extends NTitsForMTats {
    public ThreeTitsForTat() {
      super(1, 3);
    }

    @Override
    public String name() {
      return "Three Tits For Tat";
    }
  }

  public static class FourTitsForTat extends NTitsForMTats {
    public FourTitsForTat() {
      super(1, 4);
    }

    @Override
    public String name() {
      return "Four Tits For Tat";
    }
  }

  public static class FiveTitsForTat extends NTitsForMTats {
    public FiveTitsForTat() {
      super(1, 5);
    }

    @Override
    public String name() {
      return "Five Tits For Tat";
    }
  }

  public static class TenTitsForTat extends NTitsForMTats {
    public TenTitsForTat() {
      super(1, 10);
    }

    @Override
    public String name() {
      return "Ten Tits For Tat";
    }
  }

  public static class TwentyTitsForTat extends NTitsForMTats {
    public TwentyTitsForTat() {
      super(1, 20);
    }

    @Override
    public String name() {
      return "Twenty Tits For Tat";
    }
  }
}
