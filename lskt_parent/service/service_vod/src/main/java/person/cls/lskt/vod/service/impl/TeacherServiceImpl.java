package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import person.cls.lskt.model.vod.Teacher;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.vod.TeacherQueryVo;
import person.cls.lskt.vod.mapper.TeacherMapper;
import person.cls.lskt.vod.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-20
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Result<IPage<Teacher>> pageTeacher(Long current, Long limit, TeacherQueryVo teacherQueryVo) {
        Page<Teacher> page = new Page<>(current, limit);
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        LambdaQueryWrapper<Teacher> wrapper = Wrappers.lambdaQuery(Teacher.class)
                .like(!StringUtils.isEmpty(name), Teacher::getName, name)
                .eq(level != null, Teacher::getLevel, level)
                .ge(!StringUtils.isEmpty(joinDateBegin), Teacher::getJoinDate, joinDateBegin)
                .le(!StringUtils.isEmpty(joinDateEnd), Teacher::getJoinDate, joinDateEnd);
        IPage<Teacher> iPage = super.page(page, wrapper);
        return Result.ok(iPage);
    }
}
