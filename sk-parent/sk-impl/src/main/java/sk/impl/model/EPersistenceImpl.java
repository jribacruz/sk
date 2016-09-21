package sk.impl.model;

import java.util.Map;
import java.util.Set;

import sk.api.model.EPersistence;

public class EPersistenceImpl implements EPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getPersistenceUnitName() {
		return null;
	}

	@Override
	public String getPersistenceUnitTransactionType() {
		return null;
	}

	@Override
	public Set<String> getClasses() {
		return null;
	}

	@Override
	public Map<String, String> getProperties() {
		return null;
	}

}
