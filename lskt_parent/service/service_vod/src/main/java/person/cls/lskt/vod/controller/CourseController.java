package person.cls.lskt.vod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.model.vod.Course;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.vod.CourseFormVo;
import person.cls.lskt.vo.vod.CoursePublishVo;
import person.cls.lskt.vo.vod.CourseQueryVo;
import person.cls.lskt.vod.service.CourseService;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/vod/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("page/{current}/{limit}")
    public Result<Map<String, Object>> page(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) CourseQueryVo courseQueryVo) {
        Map<String, Object> page = courseService.pageCourse(current, limit, courseQueryVo);
        return Result.ok(page);
    }

    @DeleteMapping("remove/{id}")
    public Result<Course> remove(@PathVariable Long id) {
        courseService.removeCourseById(id);
        return Result.ok(null);
    }

    @PostMapping("save")
    public Result<Long> save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    @GetMapping("get/{id}")
    public Result<CourseFormVo> get(@PathVariable Long id) {
        CourseFormVo courseInfo = courseService.getCourseInfoById(id);
        return Result.ok(courseInfo);
    }

    @PostMapping("update")
    public Result<Long> update(@RequestBody CourseFormVo courseFormVo) {
        Course course = courseService.updateCourseInfo(courseFormVo);
        return Result.ok(course.getId());
    }

    @GetMapping("getCoursePublishVo/{id}")
    public Result<CoursePublishVo> getCoursePublishById(@PathVariable Long id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishById(id);
        return Result.ok(coursePublishVo);
    }

    @PutMapping("publishCourseById/{id}")
    public Result<CoursePublishVo> publishCourseById(@PathVariable Long id) {
        courseService.publishCourseById(id);
        return Result.ok(null);
    }

}

