package person.cls.lskt.user.service.impl;

import person.cls.lskt.model.user.UserInfo;
import person.cls.lskt.user.mapper.UserInfoMapper;
import person.cls.lskt.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cls
 * @since 2022-08-03
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
