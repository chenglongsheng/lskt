package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import person.cls.lskt.model.vod.Course;
import person.cls.lskt.vo.vod.CourseQueryVo;
import person.cls.lskt.vod.mapper.CourseMapper;
import person.cls.lskt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public IPage<Course> pageCourse(Long current, Long limit, CourseQueryVo courseQueryVo) {
        Page<Course> page = new Page<>(current, limit);

        String title = courseQueryVo.getTitle();
        Long teacherId = courseQueryVo.getTeacherId();
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();

        return super.page(
                page,
                Wrappers.lambdaQuery(Course.class)
                        .like(!StringUtils.isEmpty(title), Course::getTitle, title)
                        .eq(teacherId != null, Course::getTeacherId, teacherId)
                        .eq(subjectId != null, Course::getSubjectId, subjectId)
                        .eq(subjectParentId != null, Course::getSubjectParentId, subjectParentId));
    }
}
