package sk.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;

public class Beans {
	private static BeanManager beanManager = null;

	private Beans() {
	}

	public static void setBeanManager(BeanManager manager) {
		beanManager = manager;
	}

	public static BeanManager getBeanManager() {
		return beanManager;
	}

	public static <T> T getReference(final Class<T> beanClass) {
		T instance = null;

		try {
			instance = getReference(getBeanManager().getBeans(beanClass), beanClass);
		} catch (NoSuchElementException cause) {
			cause.printStackTrace();
		}

		return instance;
	}

	@SuppressWarnings("unchecked")
	private static <T> T getReference(Set<Bean<?>> beans, Class<T> beanClass, Annotation... qualifiers) {
		if (beans.size() > 1) {
			System.out.println("Erro");
		}

		Bean<?> bean = beans.iterator().next();
		CreationalContext<?> context = getBeanManager().createCreationalContext(bean);
		Type beanType = beanClass == null ? bean.getBeanClass() : beanClass;
		InjectionPoint injectionPoint = new CustomInjectionPoint(bean, beanType, qualifiers);

		return (T) getBeanManager().getInjectableReference(injectionPoint, context);
	}

	static class CustomInjectionPoint implements InjectionPoint {

		private final Bean<?> bean;

		private final Type beanType;

		private final Set<Annotation> qualifiers;

		public CustomInjectionPoint(Bean<?> bean, Type beanType, Annotation... qualifiers) {
			this.bean = bean;
			this.beanType = beanType;
			this.qualifiers = new HashSet<Annotation>(Arrays.asList(qualifiers));
		}

		@Override
		public Type getType() {
			return this.beanType;
		}

		@Override
		public Set<Annotation> getQualifiers() {
			return this.qualifiers;
		}

		@Override
		public Bean<?> getBean() {
			return this.bean;
		}

		@Override
		public Member getMember() {
			return null;
		}

		@Override
		public boolean isDelegate() {
			return false;
		}

		@Override
		public boolean isTransient() {
			return false;
		}

		@Override
		public Annotated getAnnotated() {
			return new Annotated() {

				@Override
				public Type getBaseType() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Set<Type> getTypeClosure() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				@SuppressWarnings("unchecked")
				public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
					T result = null;

					for (Annotation annotation : getAnnotations()) {
						if (annotation.annotationType() == annotationType) {
							result = (T) annotation;
							break;
						}
					}

					return result;
				}

				@Override
				public Set<Annotation> getAnnotations() {
					return qualifiers;
				}

				@Override
				public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
					return qualifiers.contains(annotationType);
				}
			};
		}
	}
}
