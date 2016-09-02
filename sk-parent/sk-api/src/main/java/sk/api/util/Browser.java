package sk.api.util;

import java.io.Serializable;

public interface Browser extends Serializable {
	void open(String url);
}
