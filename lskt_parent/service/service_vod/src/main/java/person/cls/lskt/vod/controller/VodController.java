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

    @GetMapping("sign")
    public Result<String> sign() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return Result.ok(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            return Result.fail("").message("获取签名失败");
        }
    }

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
