package cc.tianbin.springframework.test.bean;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2022/10/01
 */
@Slf4j
public class UserDao {

    private static Map<String, String> dataMap = new HashMap<>();

    static {
        dataMap.put("1001", "小明");
        dataMap.put("1002", "小红");
        dataMap.put("1003", "小白");
    }

    public String queryUserName(String userId) {
        return dataMap.get(userId);
    }

    public void initDataMethod() {
        log.info("执行：UserDao.init-method");
        dataMap.put("10001", "小傅哥");
        dataMap.put("10002", "八杯水");
        dataMap.put("10003", "阿毛");
    }

    public void destroyDataMethod() {
        log.info("执行：UserDao.destroy-method");
        dataMap.clear();
    }
}
