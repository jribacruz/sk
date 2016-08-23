package sk.api.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;

public interface PropertiesFile extends Serializable {

	public boolean containsKey(String key);

	public Enumeration<String> getKeys();

	public Locale getLocale();

	public Set<String> keySet();

	public String getString(String key, Object... params);

}
