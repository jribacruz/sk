package sk.api.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface CommandConf {

	/**
	 * Identificador único de comando SK.
	 * 
	 * @return
	 */
	String uuid();

	/**
	 * Descrição curta da finalidade do comando SK.
	 * 
	 * @return
	 */
	String description();
}
