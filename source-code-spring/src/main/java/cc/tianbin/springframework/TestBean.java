package cc.tianbin.springframework;

import org.springframework.stereotype.Component;

/**
 * Created by nibnait on 2023/02/21
 */
@Component
public class TestBean {

    private String name = "hello world";

    public String getName() {
        return name;
    }
}
