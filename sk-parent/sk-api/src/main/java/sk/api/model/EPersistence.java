package sk.api.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 
 * Representação de um arquivo persistence.xml
 * 
 * @author jcruz
 *
 */
public interface EPersistence extends Serializable {

	/**
	 * 
	 * @return
	 */
	Optional<String> getPersistenceUnitName();

	/**
	 * 
	 * @return
	 */
	Optional<String> getPersistenceUnitTransactionType();

	/**
	 * 
	 * @return
	 */
	Set<String> getClasses();

	/**
	 * 
	 * @return
	 */
	Map<String, String> getProperties();

}
