package cc.tianbin.springframework.循环依赖;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nibnait on 2023/02/21
 */
@Component
public class ClassA implements IClassA {

    @Autowired
    private ClassB classB;

    public ClassA() {
        System.out.println("classA init success");
    }

    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public ClassB getClassB() {
        return classB;
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    @Override
    public void execute() {
        System.out.println("i am classA");
    }
}
