package cc.tianbin.springframework.core.io.resourceloader.impl;

import cc.tianbin.springframework.core.io.resource.impl.ClassPathResource;
import cc.tianbin.springframework.core.io.resource.impl.FileSystemResource;
import cc.tianbin.springframework.core.io.resource.Resource;
import cc.tianbin.springframework.core.io.resource.impl.UrlResource;
import cc.tianbin.springframework.core.io.resourceloader.ResourceLoader;
import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nibnait on 2022/10/06
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");

        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                return new UrlResource(new URL(location));
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
