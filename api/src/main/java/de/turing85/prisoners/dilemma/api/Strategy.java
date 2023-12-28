package de.turing85.prisoners.dilemma.api;

public interface Strategy {
  String name();

  void startGame();

  Response playRound();

  void endRound(Response enemyResponse);
}
