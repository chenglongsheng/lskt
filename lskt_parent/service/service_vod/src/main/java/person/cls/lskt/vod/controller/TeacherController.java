package person.cls.lskt.vod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.model.vod.Teacher;
import person.cls.lskt.vod.service.TeacherService;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-20
 */
@RestController
@RequestMapping("/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("getAll")
    public List<Teacher> getAllTeacher() {
        return teacherService.list();
    }

    @DeleteMapping("remove/{id}")
    public boolean removeById(@PathVariable String id) {
        return teacherService.removeById(id);
    }

}

