package com.test.es.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class es_search {

    public List<String> listSuggestCompletion(String suggestValue , ElasticsearchTemplate elasticsearchTemplate__) {

       /* String suggestField="name";//指定在哪个字段搜索
        String suggestValue="王二";//输入的信息
        Integer suggestMaxCount=10;//获得最大suggest条数*/

        CompletionSuggestionBuilder suggestionBuilderDistrict = new CompletionSuggestionBuilder("name.suggest").prefix(suggestValue).size(10);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        //添加suggest
        suggestBuilder.addSuggestion("student_suggest", suggestionBuilderDistrict);

        //设置查询builder的index,type,以及建议
        if(elasticsearchTemplate__==null)
            System.out.println( "this is Template null  ***************************************************");
        SearchRequestBuilder requestBuilder =elasticsearchTemplate__.getClient().prepareSearch("suguu").setTypes("st").suggest(suggestBuilder);
        System.out.println(requestBuilder.toString());

        SearchResponse response = requestBuilder.get();
        //suggest实体
        Suggest suggest = response.getSuggest();
         //set
        Set<String> suggestSet = new HashSet<>();
        int maxSuggest = 0;
        if (suggest != null) {
            //获取suggest,name任意string
            Suggest.Suggestion result = suggest.getSuggestion("student_suggest");
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
                if (maxSuggest >= 10) {
                    break;
                }
            }
        }

        List<String> suggests = Arrays.asList(suggestSet.toArray(new String[]{}));

        suggests.forEach((s)->{
            System.out.println(s);
        });

        return	 suggests;

    }
}
