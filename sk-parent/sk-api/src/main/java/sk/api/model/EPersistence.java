package sk.api.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface EPersistence extends Serializable {

	String getPersistenceUnitName();

	String getPersistenceUnitTransactionType();

	Set<String> getClasses();

	Map<String, String> getProperties();

}
