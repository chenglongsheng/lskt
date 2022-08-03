package person.cls.lskt.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.model.wechat.Menu;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.wechat.MenuVo;
import person.cls.lskt.wechat.service.MenuService;
import person.cls.lskt.wechat.util.ConstantPropertiesUtil;
import person.cls.lskt.wechat.util.HttpClientUtil;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/wechat/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("syncMenu")
    public Result createMenu() throws WxErrorException {
        menuService.syncMenu();
        return Result.ok(null);
    }

    @GetMapping("getAccessToken")
    public Result<String> getAccessToken() {
        //拼接请求地址
        StringBuffer buffer = new StringBuffer();
        buffer.append("https://api.weixin.qq.com/cgi-bin/token");
        buffer.append("?grant_type=client_credential");
        buffer.append("&appid=%s");
        buffer.append("&secret=%s");
        //请求地址设置参数
        String url = String.format(buffer.toString(),
                ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //发送http请求
        try {
            String tokenString = HttpClientUtil.get(url);
            //获取access_token
            JSONObject jsonObject = JSONObject.parseObject(tokenString);
            String access_token = jsonObject.getString("access_token");
            //返回
            return Result.ok(access_token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(null);
        }
    }

    //获取所有菜单，按照一级和二级菜单封装
    @GetMapping("findMenuInfo")
    public Result<List<MenuVo>> findMenuInfo() {
        List<MenuVo> list = menuService.findMenuInfo();
        return Result.ok(list);
    }

    //获取所有一级菜单
    @GetMapping("findOneMenuInfo")
    public Result<List<Menu>> findOneMenuInfo() {
        List<Menu> list = menuService.findMenuOneInfo();
        return Result.ok(list);
    }

    @GetMapping("get/{id}")
    public Result<Menu> get(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return Result.ok(menu);
    }

    @PostMapping("save")
    public Result<Menu> save(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.ok(null);
    }

    @PutMapping("update")
    public Result<Menu> updateById(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return Result.ok(null);
    }

    @DeleteMapping("remove/{id}")
    public Result<Menu> remove(@PathVariable Long id) {
        menuService.removeById(id);
        return Result.ok(null);
    }

    @DeleteMapping("batchRemove")
    public Result<Menu> batchRemove(@RequestBody List<Long> idList) {
        menuService.removeByIds(idList);
        return Result.ok(null);
    }

    @DeleteMapping("removeMenu")
    public Result removeMenu() {
        menuService.removeMenu();
        return Result.ok(null);
    }

}

