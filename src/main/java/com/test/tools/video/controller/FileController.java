package com.test.tools.video.controller;


import com.test.tools.video.entity.FileDTO;
import com.test.tools.video.service.FileService;
import com.test.tools.video.untils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingDeque;

/**
 * 文件分片上传
 */
@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    public static final String BUSINESS_NAME = "普通分片上传";

    @Value("${file.basepath}")
    private String basePath;

    @RequestMapping("/show")
    public String show(){
        return "ce/file_test";
    }

    /**
     * 上传
     * @param file
     * @param suffix
     * @param shardIndex
     * @param shardSize
     * @param shardTotal
     * @param size
     * @param key
     * @param prefix
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file,
                         String suffix,
                         Long shardIndex,
                         Long shardSize,
                         Long shardTotal,
                         Long size,
                         String key,
                         String prefix
                         ) throws Exception{
        log.info("文件上传开始");
        //文件名称
        String name= prefix;
        log.info(prefix);
        log.info(name);
        //获取文件扩展名
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        //分片名字
        String localfileName = new StringBuffer(name)
                .append(".")
                .append(shardIndex)
                .toString();
        //绝对路径保存重命名图片
        File targeFile = new File(basePath,localfileName);
        //上传图片
        file.transferTo(targeFile);
        //数据库持久化
        FileDTO file1 = new FileDTO();
        file1.setPath(basePath+localfileName);
        file1.setSuffix(suffix);
        file1.setName(name);
        file1.setSuffix(ext);
        file1.setSize(size);
        file1.setCreatedAt(System.currentTimeMillis());
        file1.setUpdatedAt(System.currentTimeMillis());
        file1.setShardIndex(shardIndex);
        file1.setShardSize(shardSize);
        file1.setShardTotal(shardTotal);
        file1.setFileKey(key);
        //插入数据库，保存处理
        fileService.save(file1);
        //判断是否为最后片段
        if (shardIndex.equals(shardTotal)){
            file1.setPath(basePath+name);
            this.merge(file1);
        }
        return "上传成功";
    }

    @RequestMapping("/check")
    @ResponseBody
    public Result check(String key){
        List<FileDTO> check = fileService.check(key);
        if (check.size()!=0){
            return Result.ok("查询成功",check.get(0));
        }
        return Result.fail("查询失败,可以添加");
    }
    private void merge(FileDTO fileDTO) throws Exception{
        //分段和并开始
        log.info("分段和并开始");
        //获取路径
        String path = fileDTO.getPath();
        //截取视频所在路径
        path = path.replace(basePath,"");
        Long shardTotal = fileDTO.getShardTotal();
        File newFile = new File(basePath + path);
        //文件追加写入
        FileOutputStream outputStream = new FileOutputStream(newFile,true);
        //分片文件
        FileInputStream fileInputStream = null;
        byte[] byt = new byte[10*1024*1024];
        int len;
        try{
            for (int i = 0; i < shardTotal; i++){
                //读取第i个分片
                fileInputStream = new FileInputStream(new File(basePath + path + "."+(i+1)));
                while ((len = fileInputStream.read(byt)) != -1) {
                    outputStream.write(byt,0,len);
                }
            }
        }catch (IOException e){
            log.error("分片合并异常",e);
        } finally {
            try{
                if(fileInputStream !=null){
                    fileInputStream.close();
                }
                outputStream.close();
                log.info("IO流关闭");
            } catch (Exception e){
                log.error("IO流关闭",e);
            }
        }
        log.info("分片结束了");
        System.gc();
        Thread.sleep(100);
        log.info("删除分片开始");
        for (int i =0; i< shardTotal; i++){
            String filePath = basePath + path + "." + (i+1);
            File file = new File(filePath);
            boolean result = file.delete();
            log.info("删除{}，{}",filePath,result ? "成功":"失败");
        }
        log.info("删除分片结束");
    }
}
