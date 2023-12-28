import de.turing85.prisoners.dilemma.api.Strategy;
import de.turing85.prisoners.dilemma.spi.AlwaysCooperate;
import de.turing85.prisoners.dilemma.spi.AlwaysDefect;
import de.turing85.prisoners.dilemma.spi.Equalizer;
import de.turing85.prisoners.dilemma.spi.FlipFlop;
import de.turing85.prisoners.dilemma.spi.FlopFlip;
import de.turing85.prisoners.dilemma.spi.Friedman;
import de.turing85.prisoners.dilemma.spi.Crazy;
import de.turing85.prisoners.dilemma.spi.NTitsForMTats;
import de.turing85.prisoners.dilemma.spi.Prober;
import de.turing85.prisoners.dilemma.spi.Random;

// @formatter:off
module prisoners.dilemma.spi {
  requires static lombok;
  requires transitive prisoners.dilemma.api;

  provides Strategy with
      AlwaysCooperate,
      AlwaysDefect,
      Crazy,
      Equalizer.Evil,
      Equalizer.Friendly,
      Equalizer.Random,
      FlipFlop,
      FlopFlip,
      Friedman,
      Prober.Evil,
      Prober.Friendly,
      Random,
      NTitsForMTats.TitForTat,
      NTitsForMTats.TitForTwoTats,
      NTitsForMTats.TitForThreeTats,
      NTitsForMTats.TwoTitsForTat,
      NTitsForMTats.TwoTitsForTwoTats,
      NTitsForMTats.ThreeTitsForTat,
      NTitsForMTats.FourTitsForTat,
      NTitsForMTats.FiveTitsForTat,
      NTitsForMTats.TenTitsForTat,
      NTitsForMTats.TwentyTitsForTat;
}
// @formatter:on
