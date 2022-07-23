package person.cls.lskt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.vod.Subject;
import person.cls.lskt.vo.vod.SubjectVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
public interface SubjectService extends IService<Subject> {

    List<SubjectVo> getChildren(Integer id);

    /**
     * 导出
     *
     * @param response
     */
    void exportData(HttpServletResponse response);

}
