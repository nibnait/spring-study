package cc.tianbin.springframework.test.chapter.ch06_scan_property.bean;

/**
 * Created by nibnait on 2022/10/10
 */
public interface IUserService {

    String queryUserInfo(String userId);

    String queryUserInfo();

    String register(String userName);

}
