package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import person.cls.lskt.model.vod.Course;
import person.cls.lskt.model.vod.CourseDescription;
import person.cls.lskt.vo.vod.CourseFormVo;
import person.cls.lskt.vo.vod.CourseQueryVo;
import person.cls.lskt.vod.mapper.CourseMapper;
import person.cls.lskt.vod.service.CourseDescriptionService;
import person.cls.lskt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import person.cls.lskt.vod.service.SubjectService;
import person.cls.lskt.vod.service.TeacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Override
    public Map<String, Object> pageCourse(Long current, Long limit, CourseQueryVo courseQueryVo) {
        Page<Course> page = new Page<>(current, limit);
        Map<String, Object> map = new HashMap<>();

        String title = courseQueryVo.getTitle();
        Long teacherId = courseQueryVo.getTeacherId();
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        IPage<Course> iPage = super.page(
                page,
                Wrappers.lambdaQuery(Course.class)
                        .like(!StringUtils.isEmpty(title), Course::getTitle, title)
                        .eq(teacherId != null, Course::getTeacherId, teacherId)
                        .eq(subjectId != null, Course::getSubjectId, subjectId)
                        .eq(subjectParentId != null, Course::getSubjectParentId, subjectParentId));

        List<Course> records = iPage.getRecords();
        records.forEach(this::getSubjectAndTeacherName);

        map.put("records", records);
        map.put("totalCount", iPage.getTotal());

        return map;
    }

    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        super.save(course);
        String description = courseFormVo.getDescription();
        courseDescriptionService.saveCourseInfo(course.getId(), description);
        return course.getId();
    }

    @Override
    public CourseFormVo getCourseInfoById(Long id) {
        Course course = super.getById(id);
        CourseFormVo courseInfo = new CourseFormVo();
        BeanUtils.copyProperties(course, courseInfo);

        CourseDescription courseDescription = courseDescriptionService.getDescriptionByCourseId(course.getId());
        courseInfo.setDescription(courseDescription.getDescription());

        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseFormVo courseFormVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        super.updateById(course);
        courseDescriptionService.updateCourseInfo(courseFormVo.getId(), courseFormVo.getDescription());
    }

    private void getSubjectAndTeacherName(Course course) {
        course.getParam().put("teacherName", teacherService.getById(course.getTeacherId()).getName());
        course.getParam().put("subjectTitle", subjectService.getById(course.getSubjectId()).getTitle());
        course.getParam().put("subjectParentTitle", subjectService.getById(course.getSubjectParentId()).getTitle());
    }

}
