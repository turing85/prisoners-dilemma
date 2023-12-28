package de.turing85.prisoners.dilemma.engine;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.Strategy;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class Engine {
  private static final ServiceLoader<Strategy> STRATEGY_LOADER = ServiceLoader.load(Strategy.class);

  private final IntSupplier roundsSupplier;

  @Getter(AccessLevel.PRIVATE)
  private final List<Strategy> strategies;

  public Engine(IntSupplier roundsSupplier) {
    this(STRATEGY_LOADER.stream().map(ServiceLoader.Provider::get).toList(), roundsSupplier);
  }

  Engine(List<Strategy> strategies, IntSupplier roundsSupplier) {
    this.strategies = Collections.unmodifiableList(strategies);
    this.roundsSupplier = roundsSupplier;
    verifyConsistency();
  }

  private void verifyConsistency() {
    HashSet<String> names = new HashSet<>();
    HashSet<String> duplicates = new HashSet<>();
    for (String name : strategies().stream().map(Strategy::name).toList()) {
      if (!names.contains(name)) {
        names.add(name);
      } else {
        duplicates.add(name);
      }
    }
    if (!duplicates.isEmpty()) {
      // @formatter:off
      throw new IllegalArgumentException(
          "The following duplicate %s in the provided strategies %s detected: %s"
              .formatted(
                  duplicates.size() == 1 ? "name" : "names",
                  duplicates.size() == 1 ? "was" : "were",
                  String.join(", ", duplicates)));
      // @formatter:on
    }
  }

  public List<List<GameResult>> run(int iterations) {
    if (iterations <= 0) {
      throw new IllegalArgumentException(
          "parameter \"iterations\" (%d) must be > 0".formatted(iterations));
    }
    List<List<GameResult>> runResults = new ArrayList<>();
    for (int iteration = 0; iteration < iterations; ++iteration) {
      List<GameResult> runResult = new ArrayList<>();
      for (int firstIndex = 0; firstIndex < strategies.size(); ++firstIndex) {
        for (int secondIndex = firstIndex + 1; secondIndex < strategies.size(); ++secondIndex) {
          runResult.add(playGame(firstIndex, secondIndex));
        }
      }
      runResults.add(runResult);
    }
    return runResults;
  }

  private GameResult playGame(int firstIndex, int secondIndex) {
    List<Response> firstResponses = new ArrayList<>();
    List<Response> secondResponses = new ArrayList<>();
    Strategy first = strategies.get(firstIndex);
    Strategy second = strategies.get(secondIndex);
    final int rounds = roundsSupplier.getAsInt();
    first.startGame();
    second.startGame();
    for (int round = 0; round < rounds; ++round) {
      Response firstResponse = first.playRound();
      Response secondResponse = second.playRound();
      firstResponses.add(firstResponse);
      secondResponses.add(secondResponse);
      first.endRound(secondResponse);
      second.endRound(firstResponse);
    }
    return new GameResult(first.name(), firstResponses, second.name(), secondResponses);
  }

  public Set<String> strategyNames() {
    return strategies().stream().map(Strategy::name).collect(Collectors.toSet());
  }
}
