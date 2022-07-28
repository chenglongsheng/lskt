package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import person.cls.lskt.model.vod.Chapter;
import person.cls.lskt.model.vod.Video;
import person.cls.lskt.vo.vod.ChapterVo;
import person.cls.lskt.vo.vod.VideoVo;
import person.cls.lskt.vod.mapper.ChapterMapper;
import person.cls.lskt.vod.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import person.cls.lskt.vod.service.VideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getNestedTreeList(Long id) {
        List<ChapterVo> chapterVoList = new ArrayList<>();
        List<Chapter> chapters = super.list(Wrappers.lambdaQuery(Chapter.class).eq(id != null, Chapter::getCourseId, id));

        for (Chapter chapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            chapterVo.setId(chapter.getId());
            chapterVo.setTitle(chapter.getTitle());
            chapterVo.setSort(chapter.getSort());
            List<VideoVo> videoList = getNestedVideoList(id, chapter.getId());
            chapterVo.setChildren(videoList);
            chapterVoList.add(chapterVo);
        }

        return chapterVoList;
    }

    @Override
    public void removeChapterById(Long id) {
        super.removeById(id);
        videoService.remove(Wrappers.lambdaQuery(Video.class).eq(Video::getChapterId, id));
    }

    public List<VideoVo> getNestedVideoList(Long courseId, Long chapterId) {
        List<VideoVo> result = new ArrayList<>();

        List<Video> videoList = videoService.list(
                Wrappers.lambdaQuery(Video.class)
                        .eq(Video::getCourseId, courseId)
                        .eq(Video::getChapterId, chapterId));

        for (Video video : videoList) {
            VideoVo videoVo = new VideoVo();
            videoVo.setId(video.getId());
            videoVo.setTitle(video.getTitle());
            videoVo.setVideoSourceId(video.getVideoSourceId());
            videoVo.setSort(video.getSort());
            videoVo.setIsFree(video.getIsFree());
            result.add(videoVo);
        }

        return result;
    }

}
