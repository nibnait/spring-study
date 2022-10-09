package cc.tianbin.springframework.test.bean;

import cc.tianbin.springframework.beans.factory.DisposableBean;
import cc.tianbin.springframework.beans.factory.InitializingBean;
import io.github.nibnait.common.utils.DataUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/10/08
 */
@Slf4j
@Data
public class UserServiceImplInitAndDestroy implements InitializingBean, DisposableBean {

    private String userId;
    private String company;
    private String location;

    private UserDao userDao;

    public String queryUserInfo(){
        String userName = userDao.queryUserName(userId);
        return DataUtils.format("查询用户信息 {} {}, 公司: {}, 地址: {}",
                userId, userName, company, location);
    }

    @Override
    public void destroy() throws Exception {
        log.info("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("执行：UserService.afterPropertiesSet");
    }
}
