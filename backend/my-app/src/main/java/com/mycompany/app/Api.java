package com.mycompany.app;

import static spark.Spark.*;

import spark.Filter;

import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

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

        post("query/elastic/:query:q:min:max", (req, res) -> {

            String keyWord = req.queryParams("q");
            Optional<String> min = Optional.ofNullable(req.queryParams("min"));
            Optional<String> max = Optional.ofNullable(req.queryParams("max"));
            Integer size = 50;

            try {
                ElasticClient client = ElasticClient.getElasticClient();
                client.queryStringRangeAll(keyWord, "home2", 50, min, max);

                SolrjClient client = SolrjMap.getSolrClient(core);
                response = client.solrRequest(keyword, start, rows,"");
            } catch (Exception e) {
                System.out.println(e);
            }

            return response;
        });


        // SolrjClient client = SolrjClient.getSolrClient();

    }

}