package cc.tianbin.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * 类型转换抽象接口
 * Created by nibnait on 2022/10/16
 */
public interface ConversionService {

    /**
     * Return {@code true} if objects of {@code sourceType} can be converted to the {@code targetType}.
     */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /**
     * Convert the given {@code source} to the specified {@code targetType}.
     */
    <T> T convert(Object source, Class<T> targetType);

}
