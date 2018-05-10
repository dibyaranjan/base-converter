package com.github.dibyaranjan.infra.converter.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.github.dibyaranjan.infra.converter.Converter;
import com.github.dibyaranjan.infra.converter.vo.SourceTargetValue;

/**
 * A factory method to create and register new Type specific converters.
 *
 * @author Dibya
 */
public class ConverterFactory {
	private Map<SourceTargetValue, Class<?>> converterRegistry;

	public void setConverterRegistry(Map<SourceTargetValue, Class<?>> converterRegistry) {
		this.converterRegistry = converterRegistry;
	}

	public Converter getConverter(SourceTargetValue stv) {
		Class<?> clazz = converterRegistry.get(stv);
		if (clazz == null) {
			throw new IllegalArgumentException("Converter not registered to convert " + stv.getTarget() + " from "
					+ stv.getSource());
		}
		
		try {
			return createNewInstance(clazz);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Could not initialize" + clazz);
		}
	}

	private Converter createNewInstance(Class<?> clazz) throws NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		Constructor<?> constructor = clazz.getConstructor();
		Converter newInstance = (Converter) constructor.newInstance();
		return newInstance;
	}

}
