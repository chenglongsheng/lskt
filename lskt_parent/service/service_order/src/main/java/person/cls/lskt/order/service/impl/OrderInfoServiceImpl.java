package person.cls.lskt.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import person.cls.lskt.model.order.OrderDetail;
import person.cls.lskt.model.order.OrderInfo;
import person.cls.lskt.order.mapper.OrderInfoMapper;
import person.cls.lskt.order.service.OrderDetailService;
import person.cls.lskt.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import person.cls.lskt.vo.order.OrderInfoQueryVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public Map<String, Object> getPageList(Long page, Long limit, OrderInfoQueryVo orderInfoQueryVo) {

        Page<OrderInfo> pageParam = new Page<>(page, limit);
        String phone = orderInfoQueryVo.getPhone();
        Integer orderStatus = orderInfoQueryVo.getOrderStatus();
        String outTradeNo = orderInfoQueryVo.getOutTradeNo();
        String createTimeBegin = orderInfoQueryVo.getCreateTimeBegin();
        String createTimeEnd = orderInfoQueryVo.getCreateTimeEnd();

        IPage<OrderInfo> iPage = super.page(pageParam, Wrappers.lambdaQuery(OrderInfo.class)
                .eq(!StringUtils.isEmpty(outTradeNo), OrderInfo::getOutTradeNo, outTradeNo)
                .eq(!StringUtils.isEmpty(phone), OrderInfo::getPhone, phone)
                .eq(!StringUtils.isEmpty(orderStatus), OrderInfo::getOrderStatus, orderStatus)
                .ge(!StringUtils.isEmpty(createTimeBegin), OrderInfo::getCreateTime, createTimeBegin)
                .le(!StringUtils.isEmpty(createTimeEnd), OrderInfo::getCreateTime, createTimeEnd)
        );

        long totalCount = iPage.getTotal();
        long pageCount = iPage.getPages();
        List<OrderInfo> records = iPage.getRecords();
        //订单里面包含详情内容，封装详情数据，根据订单id查询详情
        records.forEach(this::getOrderDetail);

        //所有需要数据封装map集合，最终返回
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalCount);
        map.put("pageCount", pageCount);
        map.put("records", records);
        return map;
    }

    private void getOrderDetail(OrderInfo orderInfo) {
        //订单id
        Long id = orderInfo.getId();
        //查询订单详情
        OrderDetail orderDetail = orderDetailService.getOne(Wrappers.lambdaQuery(OrderDetail.class)
                .eq(OrderDetail::getOrderId, id));
        if (orderDetail != null) {
            String courseName = orderDetail.getCourseName();
            orderInfo.getParam().put("courseName", courseName);
        }
    }

}
