package cc.tianbin.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Created by nibnait on 2022/10/11
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
