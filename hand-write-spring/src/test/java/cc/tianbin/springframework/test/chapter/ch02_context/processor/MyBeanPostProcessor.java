package cc.tianbin.springframework.test.chapter.ch02_context.processor;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.config.BeanPostProcessor;
import cc.tianbin.springframework.test.chapter.ch02_context.bean.UserService;
import cc.tianbin.springframework.test.common.CommonConstants;

/**
 * Created by nibnait on 2022/10/07
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (CommonConstants.USER_SERVICE.equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
