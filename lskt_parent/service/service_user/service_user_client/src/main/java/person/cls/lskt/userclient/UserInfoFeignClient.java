package person.cls.lskt.userclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import person.cls.lskt.model.user.UserInfo;

@FeignClient
public interface UserInfoFeignClient {

    @GetMapping("/user/userInfo/inner/getById/{id}")
    UserInfo getById(@PathVariable Long id);

}
