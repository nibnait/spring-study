package cc.tianbin.springframework.test.bean.circle;

import lombok.Data;

/**
 * Created by nibnait on 2022/10/15
 */
@Data
public class Husband {

    private Wife wife;

    public String queryWife() {
        return "Husband.wife";
    }

}
