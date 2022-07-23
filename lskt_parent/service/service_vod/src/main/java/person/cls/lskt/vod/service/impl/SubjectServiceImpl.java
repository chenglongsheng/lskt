package person.cls.lskt.vod.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import person.cls.lskt.model.vod.Subject;
import person.cls.lskt.vo.vod.SubjectVo;
import person.cls.lskt.vod.mapper.SubjectMapper;
import person.cls.lskt.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-07-23
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<SubjectVo> getChildren(Integer id) {
        List<SubjectVo> result = new ArrayList<>();
        List<Subject> list = super.list(Wrappers.lambdaQuery(Subject.class).eq(Subject::getParentId, id));

        for (Subject subject : list) {
            SubjectVo subjectVo = new SubjectVo();
            subjectVo.setId(subject.getId());
            subjectVo.setTitle(subject.getTitle());
            subjectVo.setCreateTime(subject.getCreateTime());
            if (isChild(subject.getId())) {
                subjectVo.setChildren(null);
            } else {
                List<SubjectVo> children = new ArrayList<>();
                List<Subject> listChild = super.list(Wrappers.lambdaQuery(Subject.class).eq(Subject::getParentId, subject.getId()));
                for (Subject subject1 : listChild) {
                    SubjectVo child = new SubjectVo();
                    child.setId(subject1.getId());
                    child.setTitle(subject1.getTitle());
                    child.setCreateTime(subject1.getCreateTime());
                    children.add(child);
                }
                subjectVo.setChildren(children);
            }
            result.add(subjectVo);
        }
        return result;
    }

    private Boolean isChild(Long id) {
        return super.count(Wrappers.lambdaQuery(Subject.class).eq(Subject::getParentId, id)) <= 0;
    }

}
