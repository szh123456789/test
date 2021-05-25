package com.test.min;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.BinaryClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/in")
public class ind {

    @Autowired
    private JdbcTemplate jt;

    @ResponseBody
    @RequestMapping("/de")
    public Map<String,List<Map<String,Object>>> inn(){
        List<Map<String,Object>> li= jt.queryForList("select name as na,file_key as fk from file");
//        System.out.println(li);
        List<Map<String,Object>> lii =jt.queryForList("select count(file_key) as fkn from file");
        Map<String,List<Map<String,Object>>> mp=new HashMap<>();
        mp.put("num",lii);
        mp.put("mem",li);
        System.out.println(mp);
        return mp;
    }

    @ResponseBody
    @RequestMapping("/gett")
    public List<Map<String,Object>>  gett(String fk){
        System.out.println(fk);
        List<Map<String,Object>> li=jt.queryForList("select title,a,b,c,d,time,answer from vtitle where vid ='"+fk+"'");
        return li;
    }

    @RequestMapping("/gtl")
    public String gtt(){
        return "ce/gtle";
    }

    @ResponseBody
    @RequestMapping("/sou")
    public Map<String,List<Map<String,Object>>> souu(String name){
        List<Map<String,Object>> li= jt.queryForList("select name as na,file_key as fk from file where name like '%"+name+"%'");
//        System.out.println(li);
        List<Map<String,Object>> lii =jt.queryForList("select count(file_key) as fkn from file where name like '%"+name+"%'");
        Map<String,List<Map<String,Object>>> mp=new HashMap<>();
        mp.put("num",lii);
        mp.put("mem",li);
        return mp;
    }
}
