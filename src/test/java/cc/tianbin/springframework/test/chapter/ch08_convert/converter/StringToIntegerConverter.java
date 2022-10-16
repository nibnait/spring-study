package cc.tianbin.springframework.test.chapter.ch08_convert.converter;

import cc.tianbin.springframework.core.convert.converter.Converter;

/**
 * Created by nibnait on 2022/10/16
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }

}
