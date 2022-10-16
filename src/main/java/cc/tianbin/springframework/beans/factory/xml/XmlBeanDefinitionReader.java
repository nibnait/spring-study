package cc.tianbin.springframework.beans.factory.xml;

import cc.tianbin.springframework.beans.PropertyValue;
import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.config.BeanReference;
import cc.tianbin.springframework.beans.factory.support.reader.impl.AbstractBeanDefinitionReader;
import cc.tianbin.springframework.beans.factory.support.registry.BeanDefinitionRegistry;
import cc.tianbin.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import cc.tianbin.springframework.core.io.resource.Resource;
import cc.tianbin.springframework.core.io.resourceloader.ResourceLoader;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;

/**
 * Created by nibnait on 2022/10/06
 */
@Slf4j
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            return doLoadBeanDefinitions(resource);
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException(e, "IOException parsing XML document from {}", resource.getDescription());
        }
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) throws BeansException {
        int registerCount = 0;
        for (Resource resource : resources) {
            registerCount += loadBeanDefinitions(resource);
        }
        return registerCount;
    }

    @Override
    public int loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        return loadBeanDefinitions(resource);
    }

    @Override
    public int loadBeanDefinitions(String... locations) throws BeansException {
        int registerCount = 0;
        for (String location : locations) {
            registerCount += loadBeanDefinitions(location);
        }
        return registerCount;
    }

    private int doLoadBeanDefinitions(Resource resource) throws ClassNotFoundException, DocumentException, IOException {
        InputStream inputStream = resource.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        int count = registerBeanDefinitions(document.getRootElement());
        log.debug("Loaded {} bean definitions from {}", count, resource);
        return count;
    }

    private int registerBeanDefinitions(Element root) throws ClassNotFoundException {
        // 解析 context:component-scan 标签，扫描报中的类并提取相关信息，用于组装 BeanDefinition
        Element componentScan = root.element("component-scan");
        if (componentScan != null) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StringUtils.isBlank(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        int registerCount = 0;
        for (Element bean : root.elements("bean")) {
            // 解析标签，定义 Bean
            EnumMap<BeanRegisterInfo, Object> beanDefinitionMap = getBeanDefinition(bean);
            BeanDefinition beanDefinition = (BeanDefinition) beanDefinitionMap.get(BeanRegisterInfo.BEAN_DEFINITION);
            String beanName = (String) beanDefinitionMap.get(BeanRegisterInfo.BEAN_NAME);
            // 读取属性并填充
            readPropertyValue(beanDefinition, bean);
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);

            registerCount ++;
        }

        return registerCount;
    }

    private void readPropertyValue(BeanDefinition beanDefinition, Element bean) {
        for (Element property : bean.elements("property")) {
            // 解析标签: property
            String attrName = property.attributeValue("name");
            String attrValue = property.attributeValue("value");
            String attrRef = property.attributeValue("ref");
            // 获取属性值：引入对象、值对象
            Object value = StringUtils.isNotBlank(attrRef) ? new BeanReference(attrRef) : attrValue;
            // 创建属性信息
            PropertyValue propertyValue = new PropertyValue(attrName, value);
            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
        }
    }

    private EnumMap<BeanRegisterInfo, Object> getBeanDefinition(Element bean) throws ClassNotFoundException {
        String id = bean.attributeValue("id");
        String name = bean.attributeValue("name");
        String className = bean.attributeValue("class");
        String initMethod = bean.attributeValue("init-method");
        String destroyMethodName = bean.attributeValue("destroy-method");
        String beanScope = bean.attributeValue("scope");

        // 获取 Class，方便获取类中的名称
        Class<?> clazz = Class.forName(className);
        BeanDefinition beanDefinition = new BeanDefinition(clazz);
        beanDefinition.setInitMethodName(initMethod);
        beanDefinition.setDestroyMethodName(destroyMethodName);
        if (StringUtils.isNotBlank(beanScope)) {
            beanDefinition.setScope(beanScope);
        }

        String beanName = StringUtils.isNotBlank(id) ? id : name;
        if (StringUtils.isBlank(beanName)) {
            beanName = CharSequenceUtil.lowerFirst(clazz.getSimpleName());
        }
        if (getRegistry().containsBeanDefinition(beanName)) {
            throw new BeansException("Duplicate beanName[{}] is not allowed", beanName);
        }

        EnumMap<BeanRegisterInfo, Object> enumMap = new EnumMap<>(BeanRegisterInfo.class);
        enumMap.put(BeanRegisterInfo.BEAN_NAME, beanName);
        enumMap.put(BeanRegisterInfo.BEAN_DEFINITION, beanDefinition);
        return enumMap;
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }

    enum BeanRegisterInfo {
        BEAN_NAME, BEAN_DEFINITION;
    }
}
