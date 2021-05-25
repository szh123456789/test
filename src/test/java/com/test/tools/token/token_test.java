package com.test.tools.token;


import com.test.es.es_bean;
import com.test.service.stu;
import com.test.tools.short_msg.short_message;
//import com.test.ttest.est;
import io.searchbox.client.JestClient;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class token_test {

    public final  String TOKEN_SECRET = "shuizhidaoshibushimiyao";

    @Autowired
    public token_util tl;

    @Autowired
    public token_verification tv;

    @Autowired
    JestClient  jestClient;

    @Autowired
    public short_message sm;
//     @Test
//    public void aa(){
//         String a = "111";
//         String b = "qwe";
//       String sa= tl.token(a,b,TOKEN_SECRET);
//        System.out.println(sa);
//        boolean ss = tv.verify(sa,TOKEN_SECRET);
//        System.out.println(ss);
//    }


    @Test
    public void es() throws IOException {
//        est  e = new est();
//        e.setId(1);
//        e.setCont("lisi");
//        e.setName("wulala");
//
//        Index index = new Index.Builder(e).index("ceshi").type("news").build();
//        jestClient.execute(index);
    }
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private stu s;
    @Test
    public void contextLoads() {
        s.save(new es_bean(UUID.randomUUID().toString(),"王五","123654"));
        s.save(new es_bean(UUID.randomUUID().toString(),"朱六","3566787"));
        s.save(new es_bean(UUID.randomUUID().toString(),"胜七","4654864"));
        s.save(new es_bean(UUID.randomUUID().toString(),"老八","789513"));
        s.save(new es_bean(UUID.randomUUID().toString(),"王五铁蛋","123654"));
        s.save(new es_bean(UUID.randomUUID().toString(),"王五二丫","123654"));
        s.save(new es_bean(UUID.randomUUID().toString(),"王五春燕","123654"));
    }
    @Test
    public void testSuggestCompletionProc() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String suggestField="name";//指定在哪个字段搜索
        String suggestValue="王五";//输入的信息
        Integer suggestMaxCount=10;//获得最大suggest条数

        CompletionSuggestionBuilder suggestionBuilderDistrict = new CompletionSuggestionBuilder(suggestField).prefix(suggestValue).size(suggestMaxCount);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("student_suggest", suggestionBuilderDistrict);//添加suggest

        //设置查询builder的index,type,以及建议
//        SearchRequestBuilder requestBuilder = new Search.Builder(searchSourceBuilder.toString()).addIndex("索引名").addType("type名");
        SearchRequestBuilder requestBuilder = this.elasticsearchTemplate.getClient().prepareSearch("stuuu").setTypes("stu_3").suggest(suggestBuilder);
        System.out.println(requestBuilder.toString());
//        SearchResult
        SearchResponse response = requestBuilder.get();
        Suggest suggest = response.getSuggest();//suggest实体

        Set<String> suggestSet = new HashSet<>();//set
        int maxSuggest = 0;
        if (suggest != null) {
            Suggest.Suggestion result = suggest.getSuggestion("student_suggest");//获取suggest,name任意string
            for (Object term : result.getEntries()) {

                if (term instanceof CompletionSuggestion.Entry) {
                    CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
                    if (!item.getOptions().isEmpty()) {
                        //若item的option不为空,循环遍历
                        for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
                            String tip = option.getText().toString();
                            if (!suggestSet.contains(tip)) {
                                suggestSet.add(tip);
                                ++maxSuggest;
                            }
                        }
                    }
                }
                if (maxSuggest >= suggestMaxCount) {
                    break;
                }
            }
        }

        List<String> suggests = Arrays.asList(suggestSet.toArray(new String[]{}));

        suggests.forEach((s)->{
            System.out.println(s);
        });

//		return	 suggests;

//        //构建搜索对象
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        //bool
//
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//
//        //filter
//
//        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("skuName","小米");
//
//        boolQueryBuilder.filter(termQueryBuilder);
//
//
//
//        //must
//
//        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuDesc","移动联通电信4G手机");
//
//        boolQueryBuilder.must(matchQueryBuilder);
//
//        searchSourceBuilder.query(boolQueryBuilder);
//
//        //分页
//
//        searchSourceBuilder.from(0);
//
//        searchSourceBuilder.size(20);
//
//        //高亮
//
//        searchSourceBuilder.highlight(null);
//
//        String dslStr = searchSourceBuilder.toString();
//
////        List<PmsSearchSkuInfo> pmsSearchSkuInfoList = new ArrayList<>();
//
//        Search search = new Search.Builder(dslStr).addIndex("bookindex").addType("PmsSkuInfo").build();
//
//        SearchResult result = jestClient.execute(search);
//
//        List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hits = result.getHits(PmsSearchSkuInfo.class);
//
//        for (SearchResult.Hit<PmsSearchSkuInfo, Void> hit : hits) {
//
//            PmsSearchSkuInfo source = hit.source;
//
//            pmsSearchSkuInfoList.add(source);
//
//        }
//
//        System.out.println(pmsSearchSkuInfoList);
    }


//    @Test
//    public void ds() throws Exception{
//    sm.shor();
//    }
}
