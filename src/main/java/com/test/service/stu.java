package com.test.service;

import com.test.es.es_bean;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface stu extends ElasticsearchCrudRepository<es_bean,String> {
}
