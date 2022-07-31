package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import person.cls.lskt.model.vod.*;
import person.cls.lskt.vo.vod.CourseFormVo;
import person.cls.lskt.vo.vod.CoursePublishVo;
import person.cls.lskt.vo.vod.CourseQueryVo;
import person.cls.lskt.vod.mapper.CourseMapper;
import person.cls.lskt.vod.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

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
        records.forEach(this::getTeacherOrSubjectName);
        long totalPage = iPage.getPages();//总页数

        map.put("records", records);
        map.put("totalPage", totalPage);
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
    public Course updateCourseInfo(CourseFormVo courseFormVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        super.updateById(course);
        courseDescriptionService.updateCourseInfo(courseFormVo.getId(), courseFormVo.getDescription());
        return course;
    }

    @Override
    public CoursePublishVo getCoursePublishById(Long id) {
        return baseMapper.selectCoursePublishVo(id);
    }

    @Override
    public void publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(1);
        course.setPublishTime(new Date());
        super.updateById(course);
    }

    @Override
    public void removeCourseById(Long id) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据课程id删除描述
        courseDescriptionService.removeDescriptionById(id);
        //根据课程id删除课程
        super.removeById(id);
    }

    //获取讲师和分类名称
    private void getTeacherOrSubjectName(Course course) {
        //查询讲师名称
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if (teacher != null) {
            course.getParam().put("teacherName", teacher.getName());
        }
        //查询分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if (subjectOne != null) {
            course.getParam().put("subjectParentTitle", subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if (subjectTwo != null) {
            course.getParam().put("subjectTitle", subjectTwo.getTitle());
        }
    }

}
