package person.cls.lskt.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.order.OrderInfo;
import person.cls.lskt.vo.order.OrderInfoQueryVo;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-31
 */
public interface OrderInfoService extends IService<OrderInfo> {

    Map<String, Object> getPageList(Long page, Long limit, OrderInfoQueryVo orderInfoQueryVo);

}
