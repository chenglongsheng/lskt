package person.cls.lskt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import person.cls.lskt.model.vod.VideoVisitor;
import person.cls.lskt.vo.vod.VideoVisitorCountVo;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author cls
 * @since 2022-07-30
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> findCount(Long courseId, String startDate, String endDate);
}
