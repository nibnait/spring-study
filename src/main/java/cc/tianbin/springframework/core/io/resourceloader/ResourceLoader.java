package cc.tianbin.springframework.core.io.resourceloader;

import cc.tianbin.springframework.core.io.resource.Resource;
import cc.tianbin.springframework.core.util.ResourceUtils;

/**
 * Created by nibnait on 2022/10/06
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

    Resource getResource(String location);

}
