package person.cls.lskt.wechat.service;

import person.cls.lskt.model.wechat.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author cls
 * @since 2022-08-03
 */
public interface MenuService extends IService<Menu> {

    List<MenuVo> findMenuInfo();

    List<Menu> findMenuOneInfo();
}
