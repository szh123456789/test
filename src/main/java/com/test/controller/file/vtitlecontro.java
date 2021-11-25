package com.test.controller.file;


import com.test.entity.vtit;
import com.test.service.tools.vtitle.titservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ti")
public class vtitlecontro {

    @Autowired
     public titservice tse;


    //设置题目
    @RequestMapping("/set")
    @ResponseBody
    public String ti(String st,
                     String s,
                     String aa,
                     String bb,
                     String cc,
                     String dd,
                     String ke,
                     int fm){

        vtit vi = new vtit();
        vi.setA(aa);
        vi.setB(bb);
        vi.setC(cc);
        vi.setD(dd);
        vi.setAnswer(s);
        vi.setTitle(st);
        vi.setVid(ke);
        vi.setTime(fm);
        tse.save(vi);
        return "ok";
    }
}
