package person.cls.lskt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import person.cls.lskt.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(Long id);
}
