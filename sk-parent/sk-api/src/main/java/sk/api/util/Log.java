package sk.api.util;

/**
 * Classe de saida de log.
 * 
 * @author jcruz
 *
 */
public interface Log {

	/**
	 * Saide em cinza.
	 * 
	 * @param message
	 *            Mensagem de log.
	 */
	void debug(String message);

	/**
	 * Saida em cinza.
	 * 
	 * @param message
	 *            Mensagem de log.
	 * @param params
	 */
	void debug(String message, Object... params);

	void info(String message);

	void warn(String message);

	void error(String message);

	void error(String message, Object... params);

	void format(String format, Object... params);

}
