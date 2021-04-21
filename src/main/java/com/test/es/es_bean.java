package com.test.es;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.io.Serializable;

@Document(indexName = "suguu",type="st")
public class es_bean implements Serializable {
    @Id
    public String sid;

    @Field(type = FieldType.Auto)
    public String name;
    @Field(type = FieldType.Auto)
    public String password;

    public es_bean(String sid, String name, String password) {
        this.sid = sid;
        this.name = name;
        this.password = password;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "es_bean{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
