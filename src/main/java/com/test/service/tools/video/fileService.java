package com.test.service.tools.video;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.dao.fileMapper;
import com.test.entity.file;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class fileService {

    @Autowired
    private fileMapper fileMapper;


    //根据情况 保存
    public void save(file file1){
        //条件构造
        LambdaQueryWrapper<file> lambda = new QueryWrapper<file>().lambda();
        lambda.eq(file::getFileKey,file1.getFileKey());
        //查询结果
        List<file> files = fileMapper.selectList(lambda);

        if (files.size()!=0){
            LambdaQueryWrapper<file> lambda1 = new QueryWrapper<file>().lambda();
            lambda1.eq(file::getFileKey,file1.getFileKey());
            fileMapper.update(file1,lambda1);
        }else {
            fileMapper.insert(file1);
        }
    }

    // 查询是否插入
    public List<file> check(String key){
        LambdaQueryWrapper<file> lambda = new QueryWrapper<file>().lambda();
        lambda.eq(file::getFileKey,key);
        List<file> fi = fileMapper.selectList(lambda);
        return fi;
    }
}
