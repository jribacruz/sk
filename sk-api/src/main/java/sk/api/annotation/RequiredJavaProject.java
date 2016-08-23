package sk.api.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Valida se o diretório atual possui em projeto java válido (Maven ou Gradle). A annotation deve estar presente na classe Bootstrap.
 * 
 * @author jcruz
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface RequiredJavaProject {
}
