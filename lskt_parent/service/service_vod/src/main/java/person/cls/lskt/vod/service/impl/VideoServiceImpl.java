package person.cls.lskt.vod.service.impl;

import person.cls.lskt.model.vod.Video;
import person.cls.lskt.vod.mapper.VideoMapper;
import person.cls.lskt.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
