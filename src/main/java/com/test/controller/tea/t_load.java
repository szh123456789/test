package com.test.controller.tea;

import com.test.service.tools.short_msg.short_message;
import com.test.service.tools.token.token_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class t_load {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sid;

    private String sname;

    @Autowired
    private token_util tokenUtil;

    @Autowired
    private short_message shortMessage;

    private List<Map<String,Object>> list;

    private String toke;


    @RequestMapping("/tland")
    @ResponseBody
    public String x(@RequestParam(value ="name") String name, @RequestParam(value = "password") String password){
        list = jdbcTemplate.queryForList("select * from teacher where name ='"+name+"' and password='"+password+"'");
        if (list  != null) {
            sid = list.get(0).get("tid").toString();
            sname = list.get(0).get("name").toString();
            toke = tokenUtil.token(sid, sname);

            return toke;
        }else{
            return "a2";
        }
    }

    @RequestMapping("/tyann")
    @ResponseBody
    public String yannn(String name,String ya) {
        List<Map<String, Object>> li = jdbcTemplate.queryForList("select tem from teacher where name='" + name + "'");
        String s = null;
        if (li != null) {
            s = shortMessage.bl(li.get(0).get("tem").toString());
        }
        if (ya.equals(s)) {
            return "a1";
        } else {
            return "b2";
        }

    }

    @RequestMapping("tlandd")
    public String tlandd(){
        return "tea/T_Land";
    }

    @RequestMapping("tlanddd")
    public String tlanddd(@RequestParam("name") String name, @RequestParam("password") String password,@RequestParam("tem") String tem){
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        jdbcTemplate.update("insert into teacher(name,password,tid,tem) values ('"+name+"','"+password+"','"+uuid+"','"+tem+"')");
        return "tea/T_Land";
    }

    @RequestMapping("/texam")
    public String test(){

        return "tea/exam_t";
    }

    @RequestMapping("/tindex")
    public String x(){

        return "tea/index_t";
    }

    @RequestMapping("tlogin")
    public String sce(){

        return "tea/login_t";
    }

    @RequestMapping("tsendmess")
    public String sliu(){

        return "tea/sendmess";
    }

    @RequestMapping("tpingjun")
    public String squ()
    {
        return "tea/pingjun";
    }

    @RequestMapping("tsend")
    public String sting(){

        return "tea/send";
    }

    @RequestMapping("ttea")
    public String sxuan(){

        return "tea/tea";
    }

    @RequestMapping(value = "ttitle",method = RequestMethod.POST)
    public void ttitle( @RequestParam("title") String title,@RequestParam("cid") String cid){
        System.out.println(sid);
        jdbcTemplate.update("insert into cla(cid,title,tid) values('"+cid+"','"+title+"','"+ sid +"') ");
//      return "tea/send";
    }

    @RequestMapping("/tyan")
    @ResponseBody
    public void yann(String name) throws Exception{
        List<Map<String,Object>>  li=jdbcTemplate.queryForList("select tem from teacher where name='"+name+"'");
        System.out.println(li);
        if (li!=null){
            shortMessage.shor(li.get(0).get("tem").toString());
        }
    }

    @RequestMapping("vtitle")
    public String vt(){
        return "ce/vtitle";
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
