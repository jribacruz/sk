package sk.impl.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;

import sk.api.util.PropertiesFile;

public class PropertiesFileImpl implements PropertiesFile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String baseName;

	private transient java.util.ResourceBundle delegate;

	private final Locale locale;

	private java.util.ResourceBundle getDelegate() {
		if (delegate == null) {
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				delegate = ResourceBundle.getBundle(baseName, locale, classLoader);

			} catch (MissingResourceException mre) {
				delegate = ResourceBundle.getBundle(baseName, locale);
			}
		}

		return delegate;
	}

	public PropertiesFileImpl(String baseName, Locale locale) {
		this.baseName = baseName;
		this.locale = locale;
	}

	@Override
	public boolean containsKey(String key) {
		return getDelegate().containsKey(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return getDelegate().getKeys();
	}

	@Override
	public Locale getLocale() {
		return getDelegate().getLocale();
	}

	@Override
	public Set<String> keySet() {
		return getDelegate().keySet();
	}

	@Override
	public String getString(String key, Object... params) {
		return replaceString(getString(key), params);
	}

	protected String replaceString(final String string, final Object... params) {
		String result = null;

		if (string != null) {
			result = new String(string);
		}

		if (params != null && string != null) {
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null) {
					result = result.replaceAll("\\{" + i + "\\}", Matcher.quoteReplacement(params[i].toString()));
				}
			}
		}

		return result;
	}

}
