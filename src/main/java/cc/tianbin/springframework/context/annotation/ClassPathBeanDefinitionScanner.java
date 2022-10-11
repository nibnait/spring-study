package cc.tianbin.springframework.context.annotation;

import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.support.registry.BeanDefinitionRegistry;
import cc.tianbin.springframework.stereotype.Component;
import cn.hutool.core.util.StrUtil;
import io.github.nibnait.common.constants.CommonConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * Created by nibnait on 2022/10/11
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域 singleton、prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StringUtils.isNotBlank(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                // 注册 BeanDefinition
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        return scope != null ? scope.value() : CommonConstants.EMPTY_STRING;
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return StrUtil.lowerFirst(beanClass.getSimpleName());
    }
}
