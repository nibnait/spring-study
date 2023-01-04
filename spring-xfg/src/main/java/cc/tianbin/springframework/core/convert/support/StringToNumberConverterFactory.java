package cc.tianbin.springframework.core.convert.support;

import cc.tianbin.springframework.core.convert.converter.Converter;
import cc.tianbin.springframework.core.convert.converter.ConverterFactory;
import cc.tianbin.springframework.core.lang.Nullable;
import cc.tianbin.springframework.util.NumberUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by nibnait on 2022/10/16
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {
        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
