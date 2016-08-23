package sk.api.reader;

import java.io.IOException;
import java.io.Serializable;

/**
 * Leitor de string do console.
 * 
 * @author jcruz
 *
 */
public interface Reader extends Serializable {

	/**
	 * 
	 * @param message
	 * @param name
	 * @return
	 * @throws IOException
	 */
	<T extends Name> T read(String message, T name) throws IOException;

	/**
	 * 
	 * @param message
	 * @param contextKey
	 * @param name
	 * @throws IOException
	 */
	<T extends Name> void read(String message, String contextKey, T name) throws IOException;
}
