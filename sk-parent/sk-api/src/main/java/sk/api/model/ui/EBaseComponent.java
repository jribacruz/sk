package sk.api.model.ui;

import java.io.Serializable;

/**
 * 
 * Base para todos os componentes.
 * 
 * @author jcruz
 *
 */
public interface EBaseComponent extends Serializable {

	/**
	 * Id do componente.
	 * 
	 * @return
	 */
	String getId();
}
