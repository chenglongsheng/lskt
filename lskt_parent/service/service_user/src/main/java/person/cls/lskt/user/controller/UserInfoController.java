package person.cls.lskt.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import person.cls.lskt.model.user.UserInfo;
import person.cls.lskt.user.service.UserInfoService;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cls
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    @GetMapping("inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id) {
        return service.getById(id);
    }

}

