package person.cls.lskt.vod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.vod.Course;
import person.cls.lskt.vo.vod.CourseQueryVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
public interface CourseService extends IService<Course> {

    IPage<Course> pageCourse(Long current, Long limit, CourseQueryVo courseQueryVo);
}
