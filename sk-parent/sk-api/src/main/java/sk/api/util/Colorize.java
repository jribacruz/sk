package sk.api.util;

public class Colorize {
	/**
	 * Coloriza a String com a cor branca.
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em branco.
	 */
	public static String white(String value) {
		return String.format("\033[0m%s\033[0m", value);
	}

	/**
	 * Coloriza a String com a cor azul.
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em azul.
	 */
	public static String blue(String value) {
		return String.format("\033[94m%s\033[0m", value);
	}

	/**
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em amarelo.
	 */
	public static String yellow(String value) {
		return String.format("\033[33m%s\033[0m", value);
	}

	/**
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em magenta.
	 */
	public static String magenta(String value) {
		return String.format("\033[95m%s\033[0m", value);
	}

	public static String magenta(Integer value) {
		return String.format("\033[95m%s\033[0m", String.valueOf(value));
	}

	/**
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em ciano.
	 */
	public static String cyan(String value) {
		return String.format("\033[96m%s\033[0m", value);
	}

	/**
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em vermelho.
	 */
	public static String red(String value) {
		return String.format("\033[91m%s\033[0m", value);
	}

	/**
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em cinza.
	 */
	public static String gray(String value) {
		return String.format("\033[90m%s\033[0m", value);
	}

	/**
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String colorizada em verde.
	 */
	public static String green(String value) {
		return String.format("\033[32m%s\033[0m", value);
	}

	/**
	 * 
	 * @param value
	 *            String a ser colorizada.
	 * @return String em bold.
	 */
	public static String bold(String value) {
		return String.format("\033[1m%s\033[0m", value);
	}

	public static String bgGreen(String value) {
		return String.format("\033[42m%s\033[0m", value);
	}

}
