package cc.tianbin.springframework.test.chapter.ch08_convert;

import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.core.convert.converter.Converter;
import cc.tianbin.springframework.core.convert.support.StringToNumberConverterFactory;
import cc.tianbin.springframework.test.chapter.ch08_convert.bean.Husband;
import cc.tianbin.springframework.test.chapter.ch08_convert.converter.StringToIntegerConverter;
import org.junit.Test;

/**
 * Created by nibnait on 2022/10/16
 */
public class Ch08_AppTest {

    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:convert/spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("测试结果：" + husband);
    }

    @Test
    public void test_StringToIntegerConverter() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("1234");
        System.out.println("测试结果：" + num);
    }

    @Test
    public void test_StringToNumberConverterFactory() {
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

        Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
        System.out.println("测试结果：" + stringToIntegerConverter.convert("1234"));

        Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
        System.out.println("测试结果：" + stringToLongConverter.convert("1234"));
    }


}
