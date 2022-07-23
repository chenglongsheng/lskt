package person.cls.lskt.vod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import person.cls.lskt.model.vod.Subject;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.vod.SubjectVo;
import person.cls.lskt.vod.service.SubjectService;

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

}

