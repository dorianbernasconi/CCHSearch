package com.mycompany.app;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.json.JSONObject;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;



/*
 * 
 */
public class SolrjClient {
    private String solrUrl = "";
    private final SolrClient client;

    public SolrjClient(String core) {
        this.solrUrl = "http://localhost:8983/solr/" + core;
        this.client =  new HttpSolrClient.Builder(solrUrl).build();
    }

    public JSONObject solrRequest(Optional<String> keyword,Optional<String> start,Optional<String> rows,String collection ){
        final MapSolrParams queryParamMap = setSolrParams(keyword, start ,rows);
        JSONObject jsonDocuments = new JSONObject();

        try {
            final QueryResponse response = client.query(collection, queryParamMap);
            final SolrDocumentList documents = response.getResults();
            //JSONArray requestObject = new JSONArray();
            jsonDocuments.put("nbFound",documents.getNumFound());
            jsonDocuments.put("documents",documents);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return jsonDocuments;

    }

    private MapSolrParams setSolrParams(Optional<String> keyword,Optional<String> start,Optional<String> rows){
        Map<String, String> queryParamMap = new HashMap<String, String>();

        if(keyword.isPresent()){
            queryParamMap.put("q", keyword.get());
        }
        if(start.isPresent()){
            if(!rows.isPresent()){
                // this is possibly a bad practice 
                // rows need to be define here
                throw new java.lang.RuntimeException("rows Not definie");
            }
            queryParamMap.put("start", start.get());
            queryParamMap.put("rows", rows.get());
        }
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        return queryParams;
    }



}
