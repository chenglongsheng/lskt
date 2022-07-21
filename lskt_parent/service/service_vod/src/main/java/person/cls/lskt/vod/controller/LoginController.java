package person.cls.lskt.vod.controller;

import org.springframework.web.bind.annotation.*;
import person.cls.lskt.utils.result.Result;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/admin/user")
public class LoginController {

    @PostMapping("login")
    public Result<Map<String, Object>> login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map).code(20000);
    }

    @GetMapping("info")
    public Result<Map<String, Object>> info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "admin");
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin");
        return Result.ok(map).code(20000);
    }

    @PostMapping("logout")
    public Result<String> logout() {
        return Result.ok("success").code(20000);
    }

}
