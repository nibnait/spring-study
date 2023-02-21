package cc.tianbin.springframework.test.chapter.ch08_convert.converter;

import cc.tianbin.springframework.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nibnait on 2022/10/16
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {
    @Override
    public Set<?> getObject() throws Exception {
        HashSet<Object> converters = new HashSet<>();
        StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
        converters.add(stringToLocalDateConverter);
        return converters;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
