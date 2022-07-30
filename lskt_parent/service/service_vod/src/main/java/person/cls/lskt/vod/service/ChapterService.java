package person.cls.lskt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.vod.Chapter;
import person.cls.lskt.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getNestedTreeList(Long id);

    void removeChapterById(Long id);

    void removeChapterByCourseId(Long id);
}
