package cc.tianbin.springframework.test.chapter.ch01_BeanFactory;

import cc.tianbin.springframework.core.io.resource.Resource;
import cc.tianbin.springframework.core.io.resourceloader.impl.DefaultResourceLoader;
import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nibnait on 2022/10/06
 */
public class ResourceReaderTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testClassPathResourceLoader() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:application.properties");
        readResource(resource);
    }

    @Test
    public void testFileSystemResourceLoader() throws IOException {
        Resource resource = resourceLoader.getResource("src/main/resources/application.properties");
        readResource(resource);
    }

    @Test
    public void testUrlResource() throws IOException {
        Resource resource = resourceLoader.getResource("https://tianbin.cc/application.properties");
        readResource(resource);
    }

    private void readResource(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
