package person.cls.lskt.order.service.impl;

import person.cls.lskt.model.order.OrderInfo;
import person.cls.lskt.order.mapper.OrderInfoMapper;
import person.cls.lskt.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-31
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

}
