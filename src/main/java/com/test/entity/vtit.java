package com.test.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "vtitle")
public class vtit {

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 题目
     */
    private String title;

    /**
     *答案
     */
    private String answer;

    /**
     *视频号
     */
    private String vid;

    /**
     *选项a
     */
    private String a;

    private String b;

    private String c;

    private String d;
    /**
     *正确率
     */
    private String accuracy;
    //题目出现时间
    private int time;
}
