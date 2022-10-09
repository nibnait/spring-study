package cc.tianbin.springframework.test.bean;

import io.github.nibnait.common.utils.DataUtils;
import lombok.Data;

/**
 * Created by nibnait on 2022/09/18
 */
@Data
public class UserServiceImplScope {

    private String userId;
    private String company;
    private String location;

    private IUserDao userDao;

    public String queryUserInfo(){
        String userName = userDao.queryUserName(userId);
        return DataUtils.format("UserService.查询用户信息: req:{}, res:{{}},\n UserService.公司: {}, UserService.地址: {}\n",
                userId, userName, company, location);
    }

}
