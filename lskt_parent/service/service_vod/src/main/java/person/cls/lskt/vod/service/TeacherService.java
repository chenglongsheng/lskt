package person.cls.lskt.vod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.vod.Teacher;
import person.cls.lskt.utils.result.Result;
import person.cls.lskt.vo.vod.TeacherQueryVo;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-20
 */
public interface TeacherService extends IService<Teacher> {

    Result<IPage<Teacher>> pageTeacher(Long current, Long limit, TeacherQueryVo teacherQueryVo);
}
