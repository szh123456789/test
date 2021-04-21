package com.test.tools.video.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.tools.video.dao.FileMapper;
import com.test.tools.video.entity.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public void save(FileDTO file1){
        LambdaQueryWrapper<FileDTO> lambda = new QueryWrapper<FileDTO>().lambda();
        lambda.eq(FileDTO::getFileKey,file1.getFileKey());
        List<FileDTO> fileDTOS = fileMapper.selectList(lambda);

        if (fileDTOS.size()!=0){
            LambdaQueryWrapper<FileDTO> lambda1 = new QueryWrapper<FileDTO>().lambda();
            lambda1.eq(FileDTO::getFileKey,file1.getFileKey());
            fileMapper.update(file1,lambda1);
        }else {
            fileMapper.insert(file1);
        }
    }

    public List<FileDTO> check(String key){
        LambdaQueryWrapper<FileDTO> lambda = new QueryWrapper<FileDTO>().lambda();
        lambda.eq(FileDTO::getFileKey,key);
        List<FileDTO> dto = fileMapper.selectList(lambda);
        return dto;
    }
}
