package com.test.tools.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.tools.video.entity.FileDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper extends BaseMapper<FileDTO> {
}