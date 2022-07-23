package person.cls.lskt.vod.service.impl;

import person.cls.lskt.model.vod.Course;
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

}
