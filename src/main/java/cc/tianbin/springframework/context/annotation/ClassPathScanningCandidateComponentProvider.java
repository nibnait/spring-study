package cc.tianbin.springframework.context.annotation;

import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.stereotype.Component;
import cn.hutool.core.util.ClassUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by nibnait on 2022/10/11
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 扫描到所有 @Component 注解的 Bean 对象
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
