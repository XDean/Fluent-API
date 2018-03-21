package xdean.fluent;

import java.util.function.UnaryOperator;

public interface Fluent<T extends Fluent<T>> extends Self<T> {
  default T compose(UnaryOperator<T> transformer) {
    return transformer.apply(self());
  }

  default T composeIf(boolean condition, UnaryOperator<T> transformer) {
    if (condition) {
      return compose(transformer);
    } else {
      return self();
    }
  }

}
