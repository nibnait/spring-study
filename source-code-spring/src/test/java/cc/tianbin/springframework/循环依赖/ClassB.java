package cc.tianbin.springframework.循环依赖;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nibnait on 2023/02/21
 */
@Component
public class ClassB {

    @Autowired
    private ClassA classA;

    public ClassB() {
        System.out.println("classB init success");
    }

    public ClassB(ClassA classA) {
        this.classA = classA;
    }

    public ClassA getClassA() {
        return classA;
    }

    public void setClassA(ClassA classA) {
        this.classA = classA;
    }
}
