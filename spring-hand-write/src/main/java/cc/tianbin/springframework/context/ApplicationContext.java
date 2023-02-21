package cc.tianbin.springframework.context;

import cc.tianbin.springframework.beans.factory.HierarchicalBeanFactory;
import cc.tianbin.springframework.beans.factory.ListableBeanFactory;
import cc.tianbin.springframework.core.io.resourceloader.ResourceLoader;

/**
 * Created by nibnait on 2022/10/07
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
