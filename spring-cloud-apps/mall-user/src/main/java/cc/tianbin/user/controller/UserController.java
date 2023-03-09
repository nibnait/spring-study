package cc.tianbin.user.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author <a href="mailto:hiourearth@gmail.com">Jack</a>
 * @since
 */
@RestController
@RequestMapping("/user")
@RefreshScope    // 如果这个bean中的属性名称的值更新了  就destroy(bean)    instantiate(bean)
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring Cloud.";
    }

}








