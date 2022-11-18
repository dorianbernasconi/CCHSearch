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

        ApiRequest apiRequest = new ApiRequest();

        

        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        post("query/solr/request/:query:q:core:start?:rows?", (req, res) -> {
            System.out.println("SOLR");

            String core = req.queryParams("core");
            String keyword = req.queryParams("q");
            String start = req.queryParams("start");
            String rows = req.queryParams("rows");

            JSONObject response = apiRequest.solrRequest(core,keyword,start,rows);

            return response;
        });


        post("query/solr/requestadv/:query:q:core:start?:rows?:fq?", (req, res) -> {
            System.out.println("SOLR ADV");

            String core = req.queryParams("core");
            System.out.println("core : " + core);

            String keyword = req.queryParams("q");
            System.out.println("keyword : " + keyword);
            String start = req.queryParams("start");
            System.out.println("start : " + start);
            String rows = req.queryParams("rows");
            System.out.println("rows : " + rows);
            String fq = req.queryParams("fq");
            System.out.println("fq : " + fq);


            JSONObject response = apiRequest.solrRequestAdv(core,keyword,start,rows,fq);
            return response;
        });


        post("query/solr/fieldnumber/:query:q:core:field?", (req, res) -> {
            System.out.println("SOLR FIELDNUMNER");

            String core = req.queryParams("core");
            String field = req.queryParams("field");

            JSONObject response = apiRequest.getFieldOptionList(core,field);
            return response;
        });


        post("query/elastic/request/:query:q:all:min:max", (req, res) -> {
            System.out.println("Request Elastic");
            System.out.println(req.queryParams());

            String keyword = req.queryParams("q");
            String field = req.queryParams("field");
            Integer min = Integer.parseInt(req.queryParams("min"));
            Integer max = Integer.parseInt(req.queryParams("max"));
            System.out.println(keyword + " " + field + " " + min + " " + max);

            JSONObject response = apiRequest.elasticRequest(keyword, 10, field, min, max);

            return response;
        });


        post("query/elastic/similarity/:query:filepath", (req, res) -> {
            System.out.println("Request SIM");

            String documentPath = req.queryParams("filepath");
            System.out.println("Request " + documentPath);

            JSONObject response = apiRequest.elasticSimilitude(documentPath);
            System.out.println("SIMILARITY RESPONSE : " + response);

            return response;
        });
    }

}