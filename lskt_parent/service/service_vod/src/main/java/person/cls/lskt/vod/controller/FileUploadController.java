package person.cls.lskt.vod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vod.service.FileService;

import java.io.File;

@RestController
@RequestMapping("/vod/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Result<String> upload(@RequestParam MultipartFile file) {
        String upload = fileService.upload(file);
        return Result.ok(upload).message("文件上传成功！");
    }

}
