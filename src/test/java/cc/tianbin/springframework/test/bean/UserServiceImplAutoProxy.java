package cc.tianbin.springframework.test.bean;

import lombok.Data;

import java.util.Random;

/**
 * Created by nibnait on 2022/09/18
 */
@Data
public class UserServiceImplAutoProxy implements IUserService{

    private String token;

    @Override
    public String queryUserInfo(String userId) {
        return "小傅哥，100001，深圳\n";
    }

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳\n" + token;
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

}
