参考资料：
 - [https://bugstack.cn/md/spring/develop-spring](https://bugstack.cn/md/spring/develop-spring/2021-05-16-第1章：开篇介绍，手写Spring能给你带来什么？.html)

## Spring 各模块关系图
![](../docs/img/spring.jpg)

## 一些todo
### 基于Cglib实现含构造函数的类实例化策略
AbstractAutowireCapableBeanFactory.createBeanInstance  
中没有考虑构造函数入参数量相同，类型不同的情况

### 为Bean对象注入属性和依赖对象
暂时先不考虑循环依赖的情况

### 资源加载器解析文件 注册对象
XmlBeanDefinitionReader 中的 doLoadBeanDefinitions 与实际的Spring相差很大

### 把AOP动态代理，融入到Bean的生命周期
feature/aop_after_advice  
加个AfterAdvice