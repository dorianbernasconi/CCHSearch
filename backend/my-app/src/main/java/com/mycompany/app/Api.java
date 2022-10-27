package com.mycompany.app;

import static spark.Spark.*;

import spark.Filter;

import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.util.List;
import java.util.Optional;
import com.mycompany.app.*;

/**
 * Api
 * Permet au frontend de réaliser des requête à elasticSearch
 * Contient toutes les routes mise à disposition
 */
public class Api {

    public static void main(String[] args) {

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        post("query/solr/:query:q:core:start?:rows?", (req, res) -> {
            System.out.println("EZ");

            String core = req.queryParams("core");

            Optional<String> keyword = Optional.ofNullable(req.queryParams("q"));
            Optional<String> start = Optional.ofNullable(req.queryParams("start"));
            Optional<String> rows = Optional.ofNullable(req.queryParams("rows"));
            JSONObject response = new JSONObject();
            try {
                System.out.println(core);
                System.out.println(keyword);

                SolrjClient client = SolrjMap.getSolrClient(core);
                response = client.solrRequest(keyword, start, rows,"");
            } catch (Exception e) {
                System.out.println(e);
            }

            return response;
        });


 

        post("query/elastic/:query:q:all:min:max:size", (req, res) -> {
            System.out.println("OH");

            String keyWord = req.queryParams("q");
            Integer size = Integer.parseInt(req.queryParams("size")); 
            Integer min = Integer.parseInt(req.queryParams("min")); 
            Integer max = Integer.parseInt(req.queryParams("max")); 
            Boolean allFields = Boolean.valueOf(req.queryParams("all"));
            JSONObject response = new JSONObject();
            JSONArray arrayJson = new JSONArray();
            System.out.println("OH_2");


            try {
                ElasticClient client = new ElasticClient();
                List<Page> pageList =  client.query(keyWord,size,allFields,min,max);
                for (Page page : pageList) {
                    arrayJson.add(page.pageToJson());
                }
                response.put("documents", arrayJson);

            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(response);


            return response;
        });
    }

}