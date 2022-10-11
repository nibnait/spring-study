package cc.tianbin.springframework.test.bean;

import cc.tianbin.springframework.beans.factory.annotation.Autowired;
import cc.tianbin.springframework.beans.factory.annotation.Value;
import cc.tianbin.springframework.stereotype.Component;
import lombok.Data;

import java.util.Random;

/**
 * Created by nibnait on 2022/10/11
 */
@Component("userService")
@Data
public class UserServiceImpl implements IUserService{

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    @Override
    public String queryUserInfo(String userId) {
        return userDao.queryUserName(userId) + ", " + token;
    }

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳\n";
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
