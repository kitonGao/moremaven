package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/5/0005 10:58
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "index";
    }


    @RequestMapping(value = "/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }




}
