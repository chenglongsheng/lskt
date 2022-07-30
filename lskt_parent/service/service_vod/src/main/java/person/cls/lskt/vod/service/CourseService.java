package person.cls.lskt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.vod.Course;
import person.cls.lskt.vo.vod.CourseFormVo;
import person.cls.lskt.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
public interface CourseService extends IService<Course> {

    Map<String, Object> pageCourse(Long current, Long limit, CourseQueryVo courseQueryVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

    CourseFormVo getCourseInfoById(Long id);

    Course updateCourseInfo(CourseFormVo courseFormVo);
}
