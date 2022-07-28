package person.cls.lskt.vod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.model.vod.Chapter;
import person.cls.lskt.model.vod.Video;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vod.service.VideoService;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/vod/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @DeleteMapping("remove/{id}")
    public Result<Video> remove(@PathVariable Long id) {
        videoService.removeById(id);
        return Result.ok(null);
    }

    @PostMapping("save")
    public Result<Video> save(@RequestBody Video video) {
        videoService.save(video);
        return Result.ok(null);
    }

    @GetMapping("get/{id}")
    public Result<Video> get(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.ok(video);
    }

    @PutMapping("update")
    public Result<Video> update(@RequestBody Video video) {
        videoService.updateById(video);
        return Result.ok(null);
    }

}

