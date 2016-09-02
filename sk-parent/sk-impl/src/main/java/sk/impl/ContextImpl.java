package sk.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import sk.api.Context;

@Singleton
public class ContextImpl implements Context {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> ctx = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Context#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, Object value) {
		if (StringUtils.isNotBlank(key) && value != null) {
			this.ctx.put(key, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Context#get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		Object value = this.ctx.get(key);
		if (value instanceof String) {
			return replace((String) value);
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Context#replace(java.lang.String)
	 */
	@Override
	public String replace(String value) {
		if (StringUtils.isNotBlank(value)) {
			JtwigTemplate template = JtwigTemplate.inlineTemplate(value);
			JtwigModel model = JtwigModel.newModel();
			ctx.forEach((k, v) -> model.with(k, v));
			return template.render(model);
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk4j.core.Context#getContext()
	 */
	@Override
	public Map<String, Object> getContext() {
		return this.ctx;
	}

	@Override
	public String toString() {
		return this.ctx.toString();
	}

}
