package cc.tianbin.springframework.test.bean;

/**
 * Created by nibnait on 2022/09/18
 */
public class UserService {

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息: " + name);
    }
}
