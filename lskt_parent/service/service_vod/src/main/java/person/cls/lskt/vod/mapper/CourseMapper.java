package person.cls.lskt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import person.cls.lskt.model.vod.Course;
import person.cls.lskt.vo.vod.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCoursePublishVo(Long id);
}
