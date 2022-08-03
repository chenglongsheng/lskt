package person.cls.lskt.wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.SneakyThrows;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WxMpService wxMpService;

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

    /**
     * 说明：
     * 自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
     * 一级菜单最多4个汉字，二级菜单最多8个汉字，多出来的部分将会以“...”代替。
     * 创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，
     * 如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。
     * 测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
     */
    @SneakyThrows
    @Override
    public void syncMenu() {
        List<MenuVo> menuVoList = this.findMenuInfo();
        //菜单
        JSONArray buttonList = new JSONArray();
        for (MenuVo oneMenuVo : menuVoList) {
            JSONObject one = new JSONObject();
            one.put("name", oneMenuVo.getName());
            JSONArray subButton = new JSONArray();
            for (MenuVo twoMenuVo : oneMenuVo.getChildren()) {
                JSONObject view = new JSONObject();
                view.put("type", twoMenuVo.getType());
                if (twoMenuVo.getType().equals("view")) {
                    view.put("name", twoMenuVo.getName());
                    view.put("url", "http://ggkt2.vipgz1.91tunnel.com/#"
                            + twoMenuVo.getUrl());
                } else {
                    view.put("name", twoMenuVo.getName());
                    view.put("key", twoMenuVo.getMeunKey());
                }
                subButton.add(view);
            }
            one.put("sub_button", subButton);
            buttonList.add(one);
        }
        //菜单
        JSONObject button = new JSONObject();
        button.put("button", buttonList);
        this.wxMpService.getMenuService().menuCreate(button.toJSONString());
    }

    @SneakyThrows
    @Override
    public void removeMenu() {
        wxMpService.getMenuService().menuDelete();
    }
}
