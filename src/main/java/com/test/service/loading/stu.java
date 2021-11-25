package com.test.service.loading;

import com.test.service.tools.es.es_bean;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface stu extends ElasticsearchCrudRepository<es_bean,String> {
}
