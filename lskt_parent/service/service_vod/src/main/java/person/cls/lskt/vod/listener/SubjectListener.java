package person.cls.lskt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import person.cls.lskt.model.vod.Subject;
import person.cls.lskt.vo.vod.SubjectEeVo;
import person.cls.lskt.vod.mapper.SubjectMapper;

import javax.annotation.Resource;

@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEeVo, subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
