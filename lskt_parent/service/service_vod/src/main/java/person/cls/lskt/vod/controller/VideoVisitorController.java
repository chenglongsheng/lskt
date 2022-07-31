package person.cls.lskt.vod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vod.service.VideoVisitorService;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-30
 */
@RestController
@RequestMapping("/vod/videoVisitor")
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService service;

    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result<Map<String, Object>> findCount(@PathVariable Long courseId, @PathVariable String startDate, @PathVariable String endDate) {
        Map<String, Object> map = service.findCount(courseId, startDate, endDate);
        return Result.ok(map);
    }

}

