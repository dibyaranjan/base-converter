package com.github.dibyaranjan.infra.converter;


/**
 * An alternative to {code}AbstractConverter{code} which retains the object passed as argument.
 *
 * @author Dibya Ranjan
 */
public abstract class SimpleObjectConverter implements NestedConverter {
    protected Converter converter;
    @Override
    public void setConverter(Converter baseConverter) {
        this.converter = converter;
    }

    @Override
    public <T, S> T convert(T target, S source) {
        if (target == null || source == null) {
            return target;
        }

        return doConvert(target, source);
    }

    /**
     * Abstract method for the user of this class to provide concrete
     * implementation. The concrete implementation would provide the details of
     * source and target type.
     *
     * @param targetObject The targetObject to set data
     * @param sourceObject The sourceObject to get the data
     * @return converted object of type T
     */
    protected abstract <T, S> T doConvert(T targetObject, S sourceObject);
}
