package com.test.ttest;


import com.test.service.tools.es.search.es_search;
import com.test.service.tools.token.token_util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Transactional
public class tte {

    @Autowired
    private token_util tu;

    @Autowired
    private es_search ess;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate ;

    @RequestMapping("/ceec")
    @ResponseBody
    public boolean spl(@RequestParam("token") String token){

            return tu.verify(token);
    }

    @RequestMapping("/ce")
    public String s(){
        return "ce/cee";
    }

    @RequestMapping("/comp")
    @ResponseBody
    public List<String> l(@Param("term")String term){

      List<String> li=  ess.listSuggestCompletion(term,elasticsearchTemplate);
        System.out.println(li);
      return li;
    }
}
