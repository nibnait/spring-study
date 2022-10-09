package cc.tianbin.springframework.beans.factory.xml;

import cc.tianbin.springframework.beans.PropertyValue;
import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.config.BeanReference;
import cc.tianbin.springframework.beans.factory.support.reader.impl.AbstractBeanDefinitionReader;
import cc.tianbin.springframework.beans.factory.support.registry.BeanDefinitionRegistry;
import cc.tianbin.springframework.core.io.resource.Resource;
import cc.tianbin.springframework.core.io.resourceloader.ResourceLoader;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
        } catch (ClassNotFoundException e) {
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

    private int doLoadBeanDefinitions(Resource resource) throws ClassNotFoundException {
        try {
            InputStream inputStream = resource.getInputStream();
            Document doc = XmlUtil.readXML(inputStream);

            int count = registerBeanDefinitions(doc);
            log.debug("Loaded {} bean definitions from {}", count, resource);
            return count;
        } catch (IOException e) {
            throw new BeansException(e,
                    "IOException parsing XML document from {}", resource.getDescription());
        }
    }

    private int registerBeanDefinitions(Document doc) throws ClassNotFoundException {
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        int registerCount = 0;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node beanNode = childNodes.item(i);
            if (!(beanNode instanceof Element) || !"bean".equals(beanNode.getNodeName())) {
                continue;
            }

            // 解析标签，定义 Bean
            Element bean = (Element) beanNode;
            EnumMap<BeanRegisterInfo, Object> beanDefinitionMap = getBeanDefinition(bean);
            BeanDefinition beanDefinition = (BeanDefinition) beanDefinitionMap.get(BeanRegisterInfo.BEAN_DEFINITION);
            String beanName = (String) beanDefinitionMap.get(BeanRegisterInfo.BEAN_NAME);
            // 读取属性并填充
            readPropertyValue(beanDefinition, bean);
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }

        return registerCount;
    }

    private void readPropertyValue(BeanDefinition beanDefinition, Element bean) {
        for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
            Node propertyNode = bean.getChildNodes().item(j);
            if (!(propertyNode instanceof Element) || !"property".equals(propertyNode.getNodeName())) {
                continue;
            }
            // 解析标签: property
            Element property = (Element) propertyNode;
            String attrName = property.getAttribute("name");
            String attrValue = property.getAttribute("value");
            String attrRef = property.getAttribute("ref");
            // 获取属性值：引入对象、值对象
            Object value = StringUtils.isNotBlank(attrRef) ? new BeanReference(attrRef) : attrValue;
            // 创建属性信息
            PropertyValue propertyValue = new PropertyValue(attrName, value);
            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
        }
    }

    private EnumMap<BeanRegisterInfo, Object> getBeanDefinition(Element bean) throws ClassNotFoundException {
        String id = bean.getAttribute("id");
        String name = bean.getAttribute("name");
        String className = bean.getAttribute("class");
        String initMethod = bean.getAttribute("init-method");
        String destroyMethodName = bean.getAttribute("destroy-method");
        String beanScope = bean.getAttribute("scope");

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

    enum BeanRegisterInfo {
        BEAN_NAME, BEAN_DEFINITION;
    }
}
