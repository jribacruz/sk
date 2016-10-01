package sk.api;

import java.util.function.Consumer;

public interface Updatable<T> {

	public void update(Consumer<T> e);
}
