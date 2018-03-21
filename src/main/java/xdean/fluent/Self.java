package xdean.fluent;

public interface Self<T extends Self<T>> {
  @SuppressWarnings("unchecked")
  default T self() {
    return (T) this;
  }
}
