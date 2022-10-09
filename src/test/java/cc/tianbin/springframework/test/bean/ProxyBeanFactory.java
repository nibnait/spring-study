package cc.tianbin.springframework.test.bean;

import cc.tianbin.springframework.beans.factory.FactoryBean;
import io.github.nibnait.common.utils.DataUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2022/10/09
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            if (!"queryUserName".equals(method.getName())) {
                return this.toString();
            }

            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("1001", "小明");
            dataMap.put("1002", "小红");
            dataMap.put("1003", "小白");
            return DataUtils.format("你被代理了 methodName:{}, req:{}, res:{}", method.getName(),
                    args[0].toString(), dataMap.get(args[0].toString()));
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
