package person.cls.lskt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.vod.CourseDescription;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
public interface CourseDescriptionService extends IService<CourseDescription> {

    void saveCourseInfo(Long courseId, String description);

    CourseDescription getDescriptionByCourseId(Long id);

    void updateCourseInfo(Long id, String description);

    void removeDescriptionById(Long id);
}
