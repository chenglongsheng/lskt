package person.cls.lskt.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import person.cls.lskt.activity.mapper.CouponInfoMapper;
import person.cls.lskt.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import person.cls.lskt.activity.service.CouponUseService;
import person.cls.lskt.model.activity.CouponInfo;
import person.cls.lskt.model.activity.CouponUse;
import person.cls.lskt.model.user.UserInfo;
import person.cls.lskt.userclient.UserInfoFeignClient;
import person.cls.lskt.vo.activity.CouponUseQueryVo;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-31
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponUseService couponUseService;

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {
        //获取条件
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();
        //封装条件
        QueryWrapper<CouponUse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(couponId)) {
            wrapper.eq("coupon_id", couponId);
        }
        if (!StringUtils.isEmpty(couponStatus)) {
            wrapper.eq("coupon_status", couponStatus);
        }
        if (!StringUtils.isEmpty(getTimeBegin)) {
            wrapper.ge("get_time", getTimeBegin);
        }
        if (!StringUtils.isEmpty(getTimeEnd)) {
            wrapper.le("get_time", getTimeEnd);
        }
        //调用方法查询
        IPage<CouponUse> page = couponUseService.page(pageParam, wrapper);
        //封装用户昵称和手机号
        List<CouponUse> couponUseList = page.getRecords();
        couponUseList.forEach(this::getUserInfoBycouponUse);
        return page;
    }

    /**
     * 封装用户昵称和手机号
     *
     * @param couponUse 优惠券使用状态
     */
    private void getUserInfoBycouponUse(CouponUse couponUse) {
        Long userId = couponUse.getUserId();
        if (!StringUtils.isEmpty(userId)) {
            UserInfo userInfo = userInfoFeignClient.getById(userId);
            if (userInfo != null) {
                couponUse.getParam().put("nickName", userInfo.getNickName());
                couponUse.getParam().put("phone", userInfo.getPhone());
            }
        }
    }
}
