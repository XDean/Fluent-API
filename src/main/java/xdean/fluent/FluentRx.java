package xdean.fluent;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface FluentRx<S extends FluentRx<S>> extends Fluent<S> {
  default Observable<S> toObservable() {
    return Observable.just(self());
  }

  default Flowable<S> toFlowable() {
    return Flowable.just(self());
  }

  default Single<S> toSingle() {
    return Single.just(self());
  }

  default Maybe<S> toMaybe() {
    return Maybe.just(self());
  }
}
