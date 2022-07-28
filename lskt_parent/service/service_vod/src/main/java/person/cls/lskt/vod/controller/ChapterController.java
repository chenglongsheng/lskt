package person.cls.lskt.vod.controller;


import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.model.vod.Chapter;
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

    @DeleteMapping("remove/{id}")
    public Result<Chapter> remove(@PathVariable Long id) {
        chapterService.removeChapterById(id);
        return Result.ok(null);
    }

    @PostMapping("save")
    public Result<Chapter> save(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok(null);
    }

    @GetMapping("get/{id}")
    public Result<Chapter> get(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    @PutMapping("update")
    public Result<Chapter> update(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

}

