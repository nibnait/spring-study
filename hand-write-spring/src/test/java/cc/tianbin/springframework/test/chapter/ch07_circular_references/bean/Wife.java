package cc.tianbin.springframework.test.chapter.ch07_circular_references.bean;

import io.github.nibnait.common.utils.DataUtils;
import lombok.Data;

/**
 * Created by nibnait on 2022/10/15
 */
@Data
public class Wife {

    private Husband husband;

    private IMother mother;

    public String queryHusband() {
        return DataUtils.format("Wife.husband、Mother.callMother: {}", mother.callMother());
    }
}
