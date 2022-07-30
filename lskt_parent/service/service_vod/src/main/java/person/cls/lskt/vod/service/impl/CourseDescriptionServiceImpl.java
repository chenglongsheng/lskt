package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import person.cls.lskt.model.vod.CourseDescription;
import person.cls.lskt.vod.mapper.CourseDescriptionMapper;
import person.cls.lskt.vod.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

    @Override
    public void saveCourseInfo(Long courseId, String description) {
        CourseDescription courseDescription = new CourseDescription(courseId, description);
        super.save(courseDescription);
    }

    @Override
    public CourseDescription getDescriptionByCourseId(Long id) {
        return super.getOne(Wrappers.lambdaQuery(CourseDescription.class).eq(CourseDescription::getCourseId, id));
    }

    @Override
    public void updateCourseInfo(Long id, String description) {
        super.update(new CourseDescription(id, description), Wrappers.lambdaQuery(CourseDescription.class)
                .eq(id != null, CourseDescription::getCourseId, id)
        );

    }

    @Override
    public void removeDescriptionById(Long id) {
        super.remove(Wrappers.lambdaQuery(CourseDescription.class)
                .eq(id != null, CourseDescription::getCourseId, id));
    }
}
