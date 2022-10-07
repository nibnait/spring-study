package cc.tianbin.springframework.test.bean;

import io.github.nibnait.common.utils.DataUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/09/18
 */
@Slf4j
@Data
public class UserService {

    private String userId;
    private String company;
    private String location;

    private UserDao userDao;

    public String queryUserInfo(){
        String userName = userDao.queryUserName(userId);
        return DataUtils.format("查询用户信息\n {} {}, 公司: {}, 地址: {}",
                userId, userName, company, location);
    }
}
