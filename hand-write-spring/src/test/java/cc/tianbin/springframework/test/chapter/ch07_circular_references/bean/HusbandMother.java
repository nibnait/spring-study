package cc.tianbin.springframework.test.chapter.ch07_circular_references.bean;

import cc.tianbin.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 代理类
 * 婆婆，代理了媳妇原来妈妈的职责
 * Created by nibnait on 2022/10/15
 */
public class HusbandMother implements FactoryBean<IMother> {

    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class}, (proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了！" + method.getName());
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
