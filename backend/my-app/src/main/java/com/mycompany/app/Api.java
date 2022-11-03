package com.mycompany.app;

import static spark.Spark.*;

import spark.Filter;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.CollectionParams.CollectionAction;
import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.util.Map;

import java.util.List;
import java.util.Optional;
import com.mycompany.app.*;

/**
 * Api
 * Permet au frontend de réaliser des requête à elasticSearch
 * Contient toutes les routes mise à disposition
 */


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.json.JSONObject;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Api {


    public static void main(String[] args) {

        SolrjClient s = new SolrjClient("01_MANDATS_VAL_A");
        System.out.println(s.getAllAffaire());
        System.out.println(s.querySpecificField());


        ApiRequest apiRequest = new ApiRequest();

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        post("query/solr/:query:q:core:start?:rows?", (req, res) -> {

            String core = req.queryParams("core");
            String keyword = req.queryParams("q");
            String start = req.queryParams("start");
            String rows = req.queryParams("rows");

            JSONObject response = apiRequest.solrRequest(core,keyword,start,rows);
            return response;
        });


        post("query/elastic/request/:query:q:all:min:max", (req, res) -> {
            System.out.println("Request Elastic");

            String keyword = req.queryParams("q");
            String field = req.queryParams("field");
            Integer min = Integer.parseInt(req.queryParams("min"));
            Integer max = Integer.parseInt(req.queryParams("max"));

            System.out.println("Request Elastic");

            JSONObject response = apiRequest.elasticRequest(keyword, 10, field, min, max);
            System.out.println(response);
            return response;
        });


        post("query/elastic/similarity/:query:q:all:min:max:size", (req, res) -> {
            System.out.println("SIMILARITY");

            String documentPath = req.queryParams("documentPath");

            JSONObject response = apiRequest.elasticSimilitude(documentPath);
            return response;
        });
    }

}