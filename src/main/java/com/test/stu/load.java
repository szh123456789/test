package com.test.stu;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.tools.token.token_util;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

@Controller
public class load {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private token_util tu;
    private List<Map<String,Object>> uid;

    private String nn;

    private String nnn;

    private List<Map<String,Object>> mapList = new ArrayList<>() ;

    private String tok;

    @RequestMapping("/sland")
    @ResponseBody
    public String x(@RequestParam("name") String name,@RequestParam("password") String password){

     uid=jdbcTemplate.queryForList("select * from student where name ='"+name+"' and password='"+password+"'");

         nn = uid.get(0).get("sid").toString();
         nnn = uid.get(0).get("name").toString();
         tok = tu.token(nn, nnn);
         System.out.println(uid);

         return tok;
    }



    @RequestMapping("/slandd")
    public String slandd(){
       return "stu/S_Land";
    }

    @RequestMapping("slanddd")
    public String tlanddd(@RequestParam("name") String name, @RequestParam("password") String password){
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        jdbcTemplate.update("insert into student(name,password,sid) values ('"+name+"','"+password+"','"+uuid+"')");
        return "stu/S_Land";
    }
    @RequestMapping("/sexam")
    public String test(){

        System.out.println(nn);
        return "stu/exam";
    }

    @RequestMapping("/sindex")
    public String sindex(){

        return "stu/index";
    }

    @RequestMapping("slogin")
    public String sce(){
        return "stu/login";
    }

    @RequestMapping("smessage")
    public String sliu(){
        System.out.println(nn);

        return "stu/message";
    }
    @RequestMapping("squshi")
    public String squ(){

        return "stu/qushi";
    }
    @RequestMapping("sting")
    public String sti()
    {
        return "stu/ting";
    }
    @RequestMapping("sxuan")
    public String sxuan()
    {
        return "stu/xuan";
    }

    @RequestMapping("sxuanke")
    @ResponseBody
    public String sxuanke(){
        String name="";
        List<Map<String,Object>> list1 = new ArrayList<>();
        List<Map<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> list2=new ArrayList<>();
        List<Map<String,Object>> list3=new ArrayList<>();

        uid = jdbcTemplate.queryForList("select tid from cla");
        list1 = jdbcTemplate.queryForList("select cid ,title from cla");
        System.out.println(list1);
        list3 = jdbcTemplate.queryForList("select count(title) as num from cla");
        for(int i=0;i<uid.size();i++){
            Map<String,Object> map =new HashMap<>();
            name =uid.get(i).get("tid").toString();
            list=jdbcTemplate.queryForList("select name from teacher where tid ='"+name+"'");
            map.put("name",list.get(0).get("name"));
            map.put("cid",list1.get(i).get("cid"));
            map.put("title",list1.get(i).get("title"));
            list2.add(map);
        }

        JSONArray json = JSONArray.fromObject(list2);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+list3.get(0).get("num")+",\"data\":"+js+"}";
        return jso;
    }

    @RequestMapping(value ="schake",method = RequestMethod.POST)
    public void schake( @RequestParam("dat") String dat) throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        final ObjectMapper mapper = new ObjectMapper();
        System.out.println(nn);
        List<Map<String,Object>> listll = mapper.readValue(dat, new TypeReference<List<Map<String,Object>>>(){});
        for(int i =0;i<listll.size();i++){
         jdbcTemplate.update("insert into choose(chid,title,sid,tid) values('"+listll.get(i).get("cid")+"','"+listll.get(i).get("title")+"','"+nnn+"','"+listll.get(i).get("name")+"') ");
        }

        System.out.println(listll);
    }

    @RequestMapping(value ="sxianke")
    @ResponseBody
    public String sxianke(){
        System.out.println(nnn);
        List<Map<String,Object>> list3=new ArrayList<>();
        List<Map<String,Object>> list = new ArrayList<>();
        list = jdbcTemplate.queryForList("select * from choose where sid='"+nnn+"'");
        list3 = jdbcTemplate.queryForList("select count(title) as num from choose where sid='"+nnn+"'");

        for(int i=0;i<list.size();i++){
            Map<String,Object> map =new HashMap<>();
            map.put("chid",list.get(i).get("chid"));
            map.put("title",list.get(i).get("title"));
            map.put("sid",list.get(i).get("sid"));
            map.put("tid",list.get(i).get("tid"));
            mapList.add(map);

        }
        JSONArray json = JSONArray.fromObject(mapList);
        String js = json.toString();
        //*****转为layui需要的json格式，必须要这一步，博主也是没写这一步，在页面上数据就是数据接口异常
        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+list3.get(0).get("num")+",\"data\":"+js+"}";
        return jso;
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }
}
