package person.cls.lskt.wechat.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import person.cls.lskt.model.wechat.Menu;
import person.cls.lskt.vo.wechat.MenuVo;
import person.cls.lskt.wechat.mapper.MenuMapper;
import person.cls.lskt.wechat.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-08-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuVo> findMenuInfo() {
        List<MenuVo> menuVoList = new ArrayList<>();
        List<Menu> list = super.list();
        list.forEach(one -> {
            if (one.getParentId() == 0) {
                MenuVo menuVoOne = new MenuVo();
                BeanUtils.copyProperties(one, menuVoOne);
                List<MenuVo> children = new ArrayList<>();
                list.forEach(two -> {
                    if (two.getParentId().equals(one.getId())) {
                        MenuVo menuVoOTwo = new MenuVo();
                        BeanUtils.copyProperties(two, menuVoOTwo);
                        children.add(menuVoOTwo);
                    }
                });
                menuVoOne.setChildren(children);
                menuVoList.add(menuVoOne);
            }
        });
        return menuVoList;
    }

    @Override
    public List<Menu> findMenuOneInfo() {
        return super.list(Wrappers.lambdaQuery(Menu.class).eq(Menu::getParentId, 0));
    }
}
