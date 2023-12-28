package de.turing85.prisoners.dilemma.engine;

import de.turing85.prisoners.dilemma.api.Response;
import de.turing85.prisoners.dilemma.api.ResponsePair;

import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;

public record GameResult(
    String first,
    List<Response> firstResponses,
    String second,
    List<Response> secondResponses) {

  public GameResult(
      String first,
      List<Response> firstResponses,
      String second,
      List<Response> secondResponses) {
    this.first = first;
    this.firstResponses = Collections.unmodifiableList(firstResponses);
    this.second = second;
    this.secondResponses = Collections.unmodifiableList(secondResponses);
  }

  public int firstResult(ToIntFunction<ResponsePair> scoreFunction) {
    return result(first(), scoreFunction);
  }

  public int secondResult(ToIntFunction<ResponsePair> scoreFunction) {
    return result(second(), scoreFunction);
  }

  private int result(String strategyName, ToIntFunction<ResponsePair> scoreFunction) {
    if (!strategyName.equals(first) && !strategyName.equals(second)) {
      throw new IllegalArgumentException("strategy must be either %s or %s, but is %s".formatted(
          first,
          second,
          strategyName));
    }

    int score = 0;
    for (int index = 0; index < firstResponses().size(); ++index) {
      Response firstResponse = firstResponses().get(index);
      Response secondResponse = secondResponses().get(index);
      if (strategyName.equals(first)) {
        score += scoreFunction.applyAsInt(new ResponsePair(firstResponse, secondResponse));
      } else {
        score += scoreFunction.applyAsInt(new ResponsePair(secondResponse, firstResponse));
      }
    }
    return score;
  }
}