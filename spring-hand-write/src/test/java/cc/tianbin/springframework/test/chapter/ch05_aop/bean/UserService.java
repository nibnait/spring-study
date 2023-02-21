package cc.tianbin.springframework.test.chapter.ch05_aop.bean;

import cc.tianbin.springframework.test.chapter.ch02_context.bean.UserDao;
import io.github.nibnait.common.utils.DataUtils;
import lombok.Data;

/**
 * Created by nibnait on 2022/09/18
 */
@Data
public class UserService {

    private String userId;
    private String company;
    private String location;

    private UserDao userDao;

    public String queryUserInfo() {
        String userName = userDao.queryUserName(userId);
        return DataUtils.format("查询用户信息 {} {}, 公司: {}, 地址: {}",
                userId, userName, company, location);
    }

}
