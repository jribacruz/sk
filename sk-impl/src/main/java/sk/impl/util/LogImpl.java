package sk.impl.util;

import java.util.Arrays;

import sk.api.util.Colorize;
import sk.api.util.Log;

public class LogImpl implements Log {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Log#debug(java.lang.String)
	 */
	@Override
	public void debug(String message) {
		System.out.println(Colorize.gray(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Log#debug(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void debug(String message, Object... params) {
		System.out.println(String.format(Colorize.gray(message), Arrays.asList(params).stream().map(String::valueOf).toArray()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Log#info(java.lang.String)
	 */
	@Override
	public void info(String message) {
		System.out.println(Colorize.blue(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Log#warn(java.lang.String)
	 */
	@Override
	public void warn(String message) {
		System.out.println(Colorize.yellow(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Log#error(java.lang.String)
	 */
	@Override
	public void error(String message) {
		System.out.println(Colorize.red(message));
	}

	@Override
	public void format(String format, Object... params) {
		System.out.println(String.format(format, params));
	}

	@Override
	public void error(String message, Object... params) {
		System.out.println(String.format(Colorize.red(message), Arrays.asList(params).stream().map(String::valueOf).toArray()));
	}
}
