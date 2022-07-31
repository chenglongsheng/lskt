package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import person.cls.lskt.model.vod.Video;
import person.cls.lskt.vod.mapper.VideoMapper;
import person.cls.lskt.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import person.cls.lskt.vod.service.VodService;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodService vodService;

    @Override
    public void removeVideoByCourseId(Long id) {
        List<Video> list = super.list(Wrappers.lambdaQuery(Video.class)
                .eq(id != null, Video::getCourseId, id));
        for (Video video : list) {
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                vodService.removeVideo(videoSourceId);
            }
        }
        super.removeByIds(list);
    }

}
