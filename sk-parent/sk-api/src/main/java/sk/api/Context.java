package sk.api;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * Contexto do sistema.
 * 
 * @author jcruz
 *
 */
public interface Context extends Serializable {

	/**
	 * Adiciona um item no contexto.
	 * 
	 * @param key
	 *            Chave do item.
	 * @param value
	 *            Valor do item.
	 */
	void put(String key, Object value);

	/**
	 * Retorna um item do contexto.
	 * 
	 * @param key
	 *            Chave do item.
	 * @return Valor do item.
	 */
	Object get(String key);

	/**
	 * Retorna o mapa do contexto.
	 * 
	 * @return Mapa do contexto.
	 */
	Map<String, Object> getContext();

	/**
	 * Substitui os itens do contexto com expressões jtwig.
	 * 
	 * @param value
	 *            String a ser substituida.
	 * @return String substituida.
	 */
	String replace(String value);

}
