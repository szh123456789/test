package com.test.service.tools.vtitle;

import com.test.dao.titMapper;
import com.test.entity.vtit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class titservice {

    @Autowired
    private titMapper titMapper;

    public void save(vtit vi){
       titMapper.insert(vi);
    }
}
