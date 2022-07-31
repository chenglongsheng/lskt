package person.cls.lskt.vod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.cls.lskt.vod.service.VodService;

@RestController
@RequestMapping("/vod")
public class VodController {

    @Autowired
    private VodService vodService;


}
