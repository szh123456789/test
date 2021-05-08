package com.test.tools.vtitle.service;

import com.test.tools.vtitle.dao.TitMapper;
import com.test.tools.vtitle.entity.vtit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class titservice {

    @Autowired
    private TitMapper tp;

    public void save(vtit vi){
       tp.insert(vi);
    }
}
