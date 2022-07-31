package person.cls.lskt.vod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vod.service.VodService;
import person.cls.lskt.vod.util.ConstantPropertiesUtil;
import person.cls.lskt.vod.util.Signature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@RestController
@RequestMapping("/vod")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public Result<String> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String video = vodService.uploadVideo(is, originalFilename);
        return Result.ok(video);
    }

    @DeleteMapping("remove/{videoSourceId}")
    public Result<String> removeVideo(@PathVariable String videoSourceId) {
        vodService.removeVideo(videoSourceId);
        return Result.ok(null);
    }

}
