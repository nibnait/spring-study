package cc.tianbin.springframework.core.convert.support;

import cc.tianbin.springframework.core.convert.converter.ConverterRegistry;

/**
 * Created by nibnait on 2022/10/16
 */
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
    }

    public static void addDefaultConverter(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
