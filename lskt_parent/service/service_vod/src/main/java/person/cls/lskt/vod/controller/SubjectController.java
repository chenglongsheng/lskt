package person.cls.lskt.vod.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import person.cls.lskt.model.vod.Subject;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.vod.SubjectVo;
import person.cls.lskt.vod.service.SubjectService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/vod/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("list/{id}")
    public Result<List<SubjectVo>> getChildren(@PathVariable Integer id) {
        List<SubjectVo> subjects = subjectService.getChildren(id);
        return Result.ok(subjects);
    }

    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    @PostMapping("importData")
    public void importData(MultipartFile file) {
        subjectService.importDictData(file);
    }
}

