package person.cls.lskt.vod.service.impl;

import person.cls.lskt.model.vod.VideoVisitor;
import person.cls.lskt.vo.vod.VideoVisitorCountVo;
import person.cls.lskt.vod.mapper.VideoVisitorMapper;
import person.cls.lskt.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-30
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {
        List<VideoVisitorCountVo> videoVisitorCountVoList = baseMapper.findCount(courseId, startDate, endDate);

        Map<String, Object> map = new HashMap<>();

        //封装数据  代表所有日期
        List<String> dateList = new ArrayList<>();
        for (VideoVisitorCountVo videoVisitorCountVo : videoVisitorCountVoList) {
            dateList.add(String.valueOf(videoVisitorCountVo.getJoinTime()));
        }
        //代表日期对应数量
        List<Integer> countList = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getUserCount)
                .collect(Collectors.toList());

        //放到map集合
        map.put("xData", dateList);
        map.put("yData", countList);
        return map;
    }

}
