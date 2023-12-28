package de.turing85.prisoners.dilemma;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.engine.Engine;
import de.turing85.prisoners.dilemma.engine.GameResult;
import de.turing85.prisoners.dilemma.engine.ResponsePair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Runner {

  public static final ToIntFunction<ResponsePair> SCORE_FUNCTION = pair -> {
    if (pair.first() == Response.COOPERATE && pair.second() == Response.COOPERATE) {
      return 3;
    } else if (pair.first() == Response.COOPERATE && pair.second() == Response.DEFECT) {
      return 0;
    } else if (pair.first() == Response.DEFECT && pair.second() == Response.COOPERATE) {
      return 5;
    } else {
      return 1;
    }
  };
  public static final int ITERATIONS = 1_000;
  public static final int ROUNDS_PER_GAME = 200;

  public static void main(String... args) {
    final Engine engine = new Engine(() -> ROUNDS_PER_GAME);
    List<List<GameResult>> runResult = engine.run(ITERATIONS);
    HashMap<String, List<Integer>> pointsPerRunAndStrategy = transformResults(runResult);
    outputResults(pointsPerRunAndStrategy);
  }

  private static HashMap<String, List<Integer>> transformResults(List<List<GameResult>> runResult) {
    Set<String> strategyNames = runResult.stream().flatMap(List::stream)
        .map(gameResult -> List.of(gameResult.first(), gameResult.second())).flatMap(List::stream)
        .collect(Collectors.toSet());
    HashMap<String, List<Integer>> pointsPerRunAndStrategy = new HashMap<>();
    for (String strategyName : strategyNames) {
      List<Integer> pointsPerRound = new ArrayList<>();
      for (List<GameResult> gameResults : runResult) {
        // @formatter:off
        int score = gameResults.stream()
            .filter(result -> result.first().equals(strategyName))
            .mapToInt(result -> result.firstResult(SCORE_FUNCTION))
            .sum();
        score += gameResults.stream()
            .filter(result -> result.second().equals(strategyName))
            .mapToInt(result -> result.secondResult(SCORE_FUNCTION))
            .sum();
        // @formatter:on
        pointsPerRound.add(score);
      }
      pointsPerRunAndStrategy.put(strategyName, pointsPerRound);
    }
    return pointsPerRunAndStrategy;
  }

  private static void outputResults(HashMap<String, List<Integer>> pointsPerRunAndStrategy) {
    // @formatter:off
    pointsPerRunAndStrategy.entrySet().stream()
        .sorted(Comparator
            .comparingDouble((Map.Entry<String, List<Integer>> entry) ->
                entry.getValue().stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElseThrow())
            .reversed()
            .thenComparing(Map.Entry::getKey))
        .forEach(entry -> System.out.printf(
            "%-30s avg: %13.2f%n",
            entry.getKey() + ":",
            entry.getValue().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow()));
    // @formatter:on
  }
}
