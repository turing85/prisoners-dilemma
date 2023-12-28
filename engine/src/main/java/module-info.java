import de.turing85.prisoners.dilemma.api.Strategy;

// @formatter:off
module prisoners.dilemma.engine {
  requires static lombok;
  requires transitive prisoners.dilemma.api;

  uses Strategy;

  exports de.turing85.prisoners.dilemma.engine;
}
// @formatter:on
