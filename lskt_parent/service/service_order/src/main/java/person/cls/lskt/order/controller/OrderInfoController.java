package person.cls.lskt.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.order.service.OrderInfoService;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.order.OrderInfoQueryVo;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService service;

    @PostMapping("{page}/{limit}")
    public Result<Map<String, Object>> getPageList(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) OrderInfoQueryVo orderInfoQueryVo) {
        Map<String, Object> map = service.getPageList(page, limit, orderInfoQueryVo);
        return Result.ok(map);
    }

}

