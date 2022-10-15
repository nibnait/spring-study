package cc.tianbin.springframework.test.bean;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.*;
import cc.tianbin.springframework.beans.factory.aware.ApplicationContextAware;
import cc.tianbin.springframework.beans.factory.aware.BeanClassLoaderAware;
import cc.tianbin.springframework.beans.factory.aware.BeanFactoryAware;
import cc.tianbin.springframework.beans.factory.aware.BeanNameAware;
import cc.tianbin.springframework.context.ApplicationContext;
import io.github.nibnait.common.utils.DataUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/10/08
 */
@Slf4j
@Data
public class UserServiceImplAware implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private String userId;
    private String company;
    private String location;

    private UserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public String queryUserInfo(){
        String userName = userDao.queryUserName(userId);
        return DataUtils.format("查询用户信息 {} {}, 公司: {}, 地址: {}",
                userId, userName, company, location);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("ClassLoader: {}", classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        log.info("Bean Name is: {}", name);
    }
}
