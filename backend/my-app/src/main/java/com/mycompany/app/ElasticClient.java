package com.mycompany.app;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch._types.analysis.*;
import co.elastic.clients.json.*;
import co.elastic.clients.elasticsearch.core.UpdateRequest;

import co.elastic.clients.elasticsearch.core.termvectors.*;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.UpdateAction;

import org.json.simple.*;
import java.text.*;

import com.google.common.collect.Multiset.Entry;


import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.termvectors.FieldStatistics;
import co.elastic.clients.elasticsearch.core.termvectors.TermVector;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.*;

/**
 * Hello world!
 *
 */

import org.json.simple.*;

public class ElasticClient {

        RestClient restClient;
        ElasticsearchClient client;

        public ElasticClient() {
                this.restClient = RestClient.builder(
                                new HttpHost("localhost", 9200)).build();

                // Create the transport with a Jackson mapper
                ElasticsearchTransport transport = new RestClientTransport(
                                restClient, new JacksonJsonpMapper());

                // And create the API client
                this.client = new ElasticsearchClient(transport);
        }

        public List<Page> query(String keyword,Integer size, String field,Integer min,Integer max ) {

                List<String> fields = new ArrayList<>();
                fields.add("affaireName");
                fields.add("filePathDecomposed");

                fields.add("fileNameDecomposed");
                fields.add("filePath");
                if (field.equals("Tous")) {

                        fields.add("wordList");
                }

                return queryStringRangeAll(keyword,fields,"home2",size,min,max);
               
        }


        public List<Page> queryStringRangeAll(String s, List<String> fields,String index , int size, int echelleMin, int echelleMax) {
                List<Page> pageList = new ArrayList();
                try {


                        QueryStringQuery queryString = QueryBuilders.queryString()
                                        .query(s)
                                        .fields(fields)
                                        .fuzziness("2")
                                        .build();

                        RangeQuery range = new RangeQuery.Builder().field("echelle").gte(JsonData.of(echelleMin))
                                        .lt(JsonData.of(echelleMax))
                                        .build();

                                        BoolQuery boolQuery = new BoolQuery.Builder()
                                        .filter(new Query.Builder().range(range).build())
                                        .must(new Query.Builder().queryString(queryString).build())
                                        .build();

                        SearchRequest request = SearchRequest.of(a -> a.index(index)
                                        .size(size)

                                        .query(new Query.Builder().bool(boolQuery).build()));

                        SearchResponse<Page> search = client.search(
                                        request,

                                        Page.class);

                        for (Hit<Page> p : search.hits().hits()) {
                                File f = new File("/home/dorianbernasconi/CCHSearch/cche-engine-frontend/pdfJpg/" + p.source().getJpgName());
                                if(f.exists()) {
                                        Page page = p.source();
                                        page.setScore(p.score());
                                        //page.setWordPerTfIdf(this.documentInformationQuery(p.id()));
                                        page.setId(p.id());
                                        pageList.add(page);
                                }


                        }


                } catch (Exception e) {
                        System.out.println(e);
                }
                return pageList;

        }

        public List<Page> queryStringRangeTitle(String s, String index, int size, int echelleMin, int echelleMax) {
                List<Page> pageList = new ArrayList();
                try {

                        List<String> fields = new ArrayList<>();
                        fields.add("affaireName");
                        // fields.add("wordList");
                        fields.add("fileNameDecomposed");
                        fields.add("filePath");

                        QueryStringQuery queryString = QueryBuilders.queryString()
                                        .query(s)
                                        .fields(fields)
                                        .fuzziness("2")
                                        .build();

                        RangeQuery range = new RangeQuery.Builder().field("echelle").gte(JsonData.of(echelleMin))
                                        .lt(JsonData.of(echelleMax))
                                        .build();

                                        BoolQuery boolQuery = new BoolQuery.Builder()
                                        .filter(new Query.Builder().range(range).build())
                                        .must(new Query.Builder().queryString(queryString).build())
                                        .build();

                        SearchRequest request = SearchRequest.of(a -> a.index(index)
                                        .size(size)

                                        .query(new Query.Builder().bool(boolQuery).build()));

                        SearchResponse<Page> search = client.search(
                                        request,

                                        Page.class);

                        for (Hit<Page> p : search.hits().hits()) {
                                Page page = p.source();
                                page.setScore(p.score());
                                //page.setWordPerTfIdf(this.documentInformationQuery(p.id()));
                                page.setId(p.id());

                                pageList.add(page);

                        }

                        System.out.println("queryStringRangeTitle : " + index);

                } catch (Exception e) {
                        System.out.println(e);
                }
                return pageList;

        }

        public void updateIndexFavorisTrue(String id, String index) {
                try {
                        GetResponse<Page> p = this.client.get(request -> request.index(index).id(id),
                                        Page.class);

                        Page page = p.source();

                        page.setIsFavoris(true);
                        UpdateResponse<Page> o = this.client.update(
                                        request -> request.index("home2").id(id).doc(page),
                                        Page.class);

                } catch (Exception e) {
                        System.out.println(e);
                }

        }

        // Tous les documents ne contienne pas le champs isFavoris car a été rajouté
        // après l'indexation des documents
        // A modifier dans le futur quand l'update aura été réalisé sur tous les
        // documents
        public Boolean isDocumentFavoris(String id, String index) {

                boolean isDocumentFav = false;
                try {
                        GetResponse<Page> p = this.client.get(request -> request.index(index).id(id),
                                        Page.class);
                        isDocumentFav = p.source().getIsFavoris();
                        if (p.source().getIsFavoris() == null) {
                                isDocumentFav = false;
                        }

                } catch (Exception e) {
                        System.out.println(e);
                }

                return isDocumentFav;

        }

        public void addPageFavoris(String id) {

                // create a french steemer for the analzyer
                try {

                        GetResponse<Page> resp = this.client.get(request -> request.index("home2").id(id),
                                        Page.class);
                        System.out.println("ADD PAGE FAVORIS");
                        this.client.index(builder -> builder

                                        .index("favoris")
                                        .id(resp.source().getId())

                                        .document(resp.source()));
                } catch (Exception e) {
                        System.out.println(e);
                }

        }

        public List<String> documentInformationQuery(String id) {
                List<String> stringList = new ArrayList<>();
                DecimalFormat decF = new DecimalFormat("0.00");
                try {
                        List<String> a = new ArrayList<>();
                        a.add("wordList");
                        TermvectorsResponse t = client.termvectors(
                                        builder -> builder.index("home2").id(id)
                                                        .termStatistics(true).fields(a));

                        double N = t.termVectors().get("wordList").fieldStatistics()
                                        .docCount();

                        Map<String, Double> map = new HashMap<>();
                        for (Map.Entry<String, Term> entry : t.termVectors().get("wordList").terms().entrySet()) {
                                int tf = entry.getValue().ttf();
                                int df = entry.getValue().docFreq();
                                double idf = Math.log(N / df);
                                map.put(entry.getKey(), tf * idf);
                        }
                        // sort the map by reverse value
                        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
                        list.sort(Map.Entry.comparingByValue());
                        Collections.reverse(list);

                        // List map to String list

                        for (Map.Entry<String, Double> entry : list) {
                                stringList.add(entry.getKey() + " => " + decF.format(entry.getValue()) + " ");
                        }

                } catch (Exception e) {
                        System.out.println(e);
                }
                return stringList;

        }

        /*
         * public List<Page> queryString(String s, int size, int echelleMin, int
         * echelleMax) {
         * List<Page> pageList = new ArrayList();
         * try {
         * 
         * StandardAnalyzer a = AnalyzerBuilders.standard().build();
         * 
         * QueryStringQuery mq = QueryBuilders.queryString()
         * .query(s)
         * .defaultField("affaireName")
         * 
         * // .fuzziness("50")
         * 
         * .build();
         * 
         * SearchRequest request = new SearchRequest.Builder()
         * .index("home2")
         * 
         * .size(size)
         * .query(mq._toQuery())
         * 
         * .build();
         * 
         * SearchResponse<Page> search = client.search(
         * request,
         * Page.class);
         * 
         * for (Hit<Page> p : search.hits().hits()) {
         * Page page = p.source();
         * page.setScore(p.score());
         * pageList.add(page);
         * 
         * }
         * System.out.println("queryString");
         * 
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         * return pageList;
         * 
         * }
         * 
         * public List<Page> queryString(String s, int size) {
         * List<Page> pageList = new ArrayList();
         * try {
         * 
         * var queryString = QueryBuilders.queryString()
         * .query(s)
         * .fuzziness("2")
         * .build();
         * 
         * var queryMatch = QueryBuilders.queryString()
         * .fields("affaireName")
         * .query("2003BEAUREGARD")
         * .build();
         * 
         * var boolQuery = new BoolQuery.Builder()
         * .must(new Query.Builder().queryString(queryMatch).build())
         * // .must(new Query.Builder().queryString(queryString).build())
         * .build();
         * 
         * SearchRequest request = SearchRequest.of(a -> a.index("home2")
         * .size(size)
         * 
         * .query(new Query.Builder().bool(boolQuery).build()));
         * 
         * SearchResponse<Page> search = client.search(
         * request,
         * 
         * Page.class);
         * 
         * for (Hit<Page> p : search.hits().hits()) {
         * Page page = p.source();
         * page.setScore(p.score());
         * page.setId(p.id());
         * System.out.println("OOOOOOOOh : " + p.id());
         * pageList.add(page);
         * 
         * }
         * 
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         * return pageList;
         * 
         * }
         * 
         * public List<Page> matchQueryRange(String s, int size, int echelleMin, int
         * echelleMax) {
         * List<Page> pageList = new ArrayList();
         * try {
         * 
         * var st = new StandardAnalyzer.Builder();
         * var matchQuery = QueryBuilders.multiMatch().query(s).fuzziness("2").build();
         * 
         * RangeQuery range = new
         * RangeQuery.Builder().field("echelle").gte(JsonData.of(echelleMin))
         * .lt(JsonData.of(echelleMax))
         * .build();
         * 
         * var boolQuery = new BoolQuery.Builder()
         * .filter(new Query.Builder().range(range).build())
         * .must(new Query.Builder().multiMatch(matchQuery).build())
         * .build();
         * 
         * SearchRequest request = SearchRequest.of(a -> a.index("home2")
         * .size(size)
         * .query(new Query.Builder().bool(boolQuery).build()));
         * 
         * SearchResponse<Page> search = client.search(
         * request,
         * Page.class);
         * 
         * for (Hit<Page> p : search.hits().hits()) {
         * Page page = p.source();
         * page.setScore(p.score());
         * pageList.add(page);
         * 
         * }
         * 
         * System.out.println("queryString");
         * 
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         * return pageList;
         * 
         * }
         *
         * 
         * public List<Page> matchPhrase(String s, int size, int echelleMin, int
         * echelleMax, double minScore) {
         * List<Page> pageList = new ArrayList();
         * try {
         * 
         * var queryString = QueryBuilders
         * .matchPhrase()
         * .query(s)
         * .build();
         * 
         * var boolQuery = new BoolQuery.Builder()
         * .must(new Query.Builder().matchPhrase(queryString).build())
         * .build();
         * 
         * SearchRequest request = SearchRequest.of(a -> a.index("home2")
         * .size(size)
         * 
         * .query(new Query.Builder().bool(boolQuery).build()));
         * 
         * SearchResponse<Page> search = client.search(
         * request,
         * 
         * Page.class);
         * 
         * for (Hit<Page> p : search.hits().hits()) {
         * 
         * if (p.score() > minScore) {
         * Page page = p.source();
         * page.setScore(p.score());
         * pageList.add(page);
         * }
         * }
         * System.out.println("queryString");
         * 
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         * return pageList;
         * 
         * }
         * 
         * public List<Page> matchQuery(String s, int size) {
         * List<Page> pageList = new ArrayList();
         * try {
         * 
         * var queryString = QueryBuilders.match()
         * .field("affaireName")
         * .query(s)
         * .analyzer("standard")
         * .fuzziness("2")
         * .build();
         * 
         * var boolQuery = new BoolQuery.Builder()
         * 
         * .must(new Query.Builder().match(queryString).build())
         * .build();
         * 
         * SearchRequest request = SearchRequest.of(a -> a.index("home2")
         * .size(size)
         * 
         * .query(new Query.Builder().bool(boolQuery).build()));
         * 
         * SearchResponse<Page> search = client.search(
         * request,
         * 
         * Page.class);
         * 
         * System.out.println("matchQuery");
         * 
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         * return pageList;
         * 
         * }
         */

}
