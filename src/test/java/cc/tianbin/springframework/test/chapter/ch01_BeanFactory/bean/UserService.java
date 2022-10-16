package cc.tianbin.springframework.test.chapter.ch01_BeanFactory.bean;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/09/18
 */
@Slf4j
public class UserService {

    private String userId;

    private UserDao userDao;

    public void queryUserInfo(){
        log.info("查询用户信息 {} {}", userId, userDao.queryUserName(userId));
    }
}
