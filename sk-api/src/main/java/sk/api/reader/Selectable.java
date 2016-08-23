package sk.api.reader;

public interface Selectable<T> extends Comparable<T> {
	String getConsoleLabel();
}
