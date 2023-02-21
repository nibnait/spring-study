package cc.tianbin.springframework.context;

import cc.tianbin.springframework.beans.factory.FactoryBean;
import cc.tianbin.springframework.beans.factory.InitializingBean;
import cc.tianbin.springframework.core.convert.ConversionService;
import cc.tianbin.springframework.core.convert.converter.Converter;
import cc.tianbin.springframework.core.convert.converter.ConverterFactory;
import cc.tianbin.springframework.core.convert.converter.ConverterRegistry;
import cc.tianbin.springframework.core.convert.converter.GenericConverter;
import cc.tianbin.springframework.core.convert.support.DefaultConversionService;
import cc.tianbin.springframework.core.convert.support.GenericConversionService;
import cc.tianbin.springframework.core.lang.Nullable;

import java.util.Set;

/**
 * 类型转换工厂
 * Created by nibnait on 2022/10/16
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
