package cc.tianbin.trade.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nibnait on 2023/03/09
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @RequestMapping("/query")
    public String query(){
        log.info("visited...");
        return "order query...";
    }
}
