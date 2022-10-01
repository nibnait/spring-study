package cc.tianbin.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2022/10/01
 */
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

}
