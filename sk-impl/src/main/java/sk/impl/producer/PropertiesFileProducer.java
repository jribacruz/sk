package sk.impl.producer;

import java.util.Locale;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import sk.impl.Beans;
import sk.impl.util.PropertiesFileImpl;

public class PropertiesFileProducer {

	@Default
	@Produces
	public PropertiesFileImpl createDefault(InjectionPoint ip) {
		return new PropertiesFileImpl("sk", Beans.getReference(Locale.class));
	}

}
