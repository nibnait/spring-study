package cc.tianbin.springframework.core.io.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nibnait on 2022/10/06
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    String getDescription();
}
