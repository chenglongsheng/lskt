package person.cls.lskt.vod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.vod.ChapterVo;
import person.cls.lskt.vod.service.ChapterService;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/vod/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("getAll/{id}")
    public Result<List<ChapterVo>> getNestedTreeList(@PathVariable Long id) {
        List<ChapterVo> result = chapterService.getNestedTreeList(id);
        return Result.ok(result);
    }
}

