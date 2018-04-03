package xdean.fluent;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface Fluent<S extends Fluent<S>> extends Self<S> {

  default S let(Consumer<S> action) {
    action.accept(self());
    return self();
  }

  default S letIf(Predicate<S> test, Consumer<S> action) {
    if (test.test(self())) {
      return let(action);
    } else {
      return self();
    }
  }

  default <T> T map(Function<S, T> transformer) {
    return transformer.apply(self());
  }

  default S mapIf(Predicate<S> test, UnaryOperator<S> transformer) {
    if (test.test(self())) {
      return map(transformer);
    } else {
      return self();
    }
  }

  default <T> T mapIfOrElse(Predicate<S> test, Function<S, T> transformer, Function<S, T> elseTransformer) {
    if (test.test(self())) {
      return map(transformer);
    } else {
      return map(elseTransformer);
    }
  }

  default Optional<S> filter(Predicate<S> p) {
    if (p.test(self())) {
      return Optional.of(self());
    } else {
      return Optional.empty();
    }
  }

  default Optional<S> toOptional() {
    return Optional.of(self());
  }

  default Stream<S> toStream() {
    return Stream.of(self());
  }
}
