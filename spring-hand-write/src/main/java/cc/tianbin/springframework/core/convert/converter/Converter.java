package cc.tianbin.springframework.core.convert.converter;

/**
 * 类型转换处理接口
 * Created by nibnait on 2022/10/16
 */
public interface Converter<S, T> {

    /**
     *  Convert the source object of type {@code S} to target type {@code T}.
     */
    T convert(S source);

}
