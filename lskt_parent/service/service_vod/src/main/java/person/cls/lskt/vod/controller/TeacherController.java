package person.cls.lskt.vod.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import person.cls.lskt.model.vod.Teacher;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.vod.TeacherQueryVo;
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
@Api("讲师管理接口")
@RestController
@RequestMapping("/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查找所有讲师")
    @GetMapping("list")
    public Result<List<Teacher>> listTeacher() {
        int i = 10 / 0;
        return Result.ok(teacherService.list());
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result<Teacher> removeById(@PathVariable Long id) {
        boolean isDeleted = teacherService.removeById(id);
        if (isDeleted) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    @ApiOperation("分页查找讲师")
    @PostMapping("page/{current}/{limit}")
    public Result<IPage<Teacher>> pageTeacher(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        return teacherService.pageTeacher(current, limit, teacherQueryVo);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result<Teacher> save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.ok(null);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<Teacher> getById(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    @PostMapping("update")
    public Result<Teacher> update(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.ok(null);
    }

    @DeleteMapping("batchRemove")
    public Result<Teacher> del(@RequestBody List<Long> ids) {
        teacherService.removeByIds(ids);
        return Result.ok(null);
    }

}

