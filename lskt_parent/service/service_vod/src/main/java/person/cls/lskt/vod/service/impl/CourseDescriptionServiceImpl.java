package person.cls.lskt.vod.service.impl;

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
}
