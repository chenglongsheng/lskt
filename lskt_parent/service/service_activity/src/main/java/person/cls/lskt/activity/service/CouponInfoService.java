package person.cls.lskt.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import person.cls.lskt.model.activity.CouponInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.activity.CouponUse;
import person.cls.lskt.vo.activity.CouponUseQueryVo;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-31
 */
public interface CouponInfoService extends IService<CouponInfo> {

    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);
}
