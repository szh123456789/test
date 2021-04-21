package com.test.stu;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("schu")
public class chuli {

    @RequestMapping("/spload")
    public String spl(){
        return "stu/get_cla";
    }


    @RequestMapping("/vid")
    public String vid(){return "ce/video";}
}
