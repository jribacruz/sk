package sk.api.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation de configuração de Applications SK.
 * 
 * @author jcruz
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ApplicationConf {
	/**
	 * Identificador único da app SK.
	 * 
	 * @return
	 */
	String uuid();

	/**
	 * Descrição curta da finalizada da app SK.
	 * 
	 * @return
	 */
	String description();
}
