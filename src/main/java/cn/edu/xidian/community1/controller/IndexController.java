package cn.edu.xidian.community1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
