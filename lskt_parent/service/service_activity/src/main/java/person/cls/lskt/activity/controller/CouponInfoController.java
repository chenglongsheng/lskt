package person.cls.lskt.activity.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.activity.service.CouponInfoService;
import person.cls.lskt.model.activity.CouponInfo;
import person.cls.lskt.utils.result.Result;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/activity/couponInfo")
public class CouponInfoController {

    @Autowired
    private CouponInfoService service;

    @GetMapping("{page}/{limit}")
    public Result<IPage<CouponInfo>> index(@PathVariable Long page, @PathVariable Long limit) {
        IPage<CouponInfo> pageModel = service.page(new Page<>(page, limit));
        return Result.ok(pageModel);
    }

    @GetMapping("get/{id}")
    public Result<CouponInfo> get(@PathVariable Long id) {
        CouponInfo couponInfo = service.getById(id);
        return Result.ok(couponInfo);
    }

    @PutMapping("update")
    public Result<CouponInfo> updateById(@RequestBody CouponInfo couponInfo) {
        service.updateById(couponInfo);
        return Result.ok(null);
    }

    @DeleteMapping("remove/{id}")
    public Result<CouponInfo> remove(@PathVariable String id) {
        service.removeById(id);
        return Result.ok(null);
    }

    @DeleteMapping("batchRemove")
    public Result<CouponInfo> batchRemove(@RequestBody List<String> idList) {
        service.removeByIds(idList);
        return Result.ok(null);
    }

}

