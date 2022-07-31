package person.cls.lskt.order.service.impl;

import person.cls.lskt.model.order.OrderDetail;
import person.cls.lskt.order.mapper.OrderDetailMapper;
import person.cls.lskt.order.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-31
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
