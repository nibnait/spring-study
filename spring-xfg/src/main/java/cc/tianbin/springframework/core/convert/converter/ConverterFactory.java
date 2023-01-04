package cc.tianbin.springframework.core.convert.converter;

/**
 * 类型转换工厂
 * Created by nibnait on 2022/10/16
 */
public interface ConverterFactory<S, R> {

    /**
     * Get the converter to convert from S to target type T, where T is also an instance of R.
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
