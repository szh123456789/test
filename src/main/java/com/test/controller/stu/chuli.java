package com.test.controller.stu;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class chuli {

    @RequestMapping("/spload")
    public String spl(){
        return "stu/get_cla";
    }


    @RequestMapping("/vid")
    public String vid(){return "ce/video";}

    @RequestMapping("/svid")
    public String vids(){return "ce/video_s";}
}
