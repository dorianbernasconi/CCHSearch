package com.mycompany.app;

import org.apache.commons.math3.util.Pair;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.json.Json;

import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 */
public class SolrjClient {
    private String solrUrl = "";
    private final SolrClient client;

    public SolrjClient(String core) {
        this.solrUrl = "http://localhost:8983/solr/" + core;
        this.client = new HttpSolrClient.Builder(solrUrl).build();
    }

    public JSONObject solrRequest(String collection,String keyword, String start, String rows) {
        final MapSolrParams queryParamMap = setSolrParams(keyword, start, rows);
        JSONObject jsonDocuments = new JSONObject();

        try {
            final QueryResponse response = client.query(collection, queryParamMap);
            final SolrDocumentList documents = response.getResults();
            // JSONArray requestObject = new JSONArray();
            jsonDocuments.put("nbFound", documents.getNumFound());
            jsonDocuments.put("documents", documents);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return jsonDocuments;

    }

    public JSONObject solrRequestAdv(String collection,String keyword, String start, String rows,String filter) {
        final MapSolrParams queryParamMap = setSolrParamsAdv(keyword, start, rows,filter);
        JSONObject jsonDocuments = new JSONObject();

        try {
            final QueryResponse response = client.query(collection, queryParamMap);
            final SolrDocumentList documents = response.getResults();
            // JSONArray requestObject = new JSONArray();
            jsonDocuments.put("nbFound", documents.getNumFound());
            jsonDocuments.put("documents", documents);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return jsonDocuments;

    }

    // For a field return all the existing values
    // and for each value the number of occurence
    //
    // exenmple : {"facet":[{"name":"non","count":2414},{"name":"oui","count":32}]}
    public JSONObject getFieldOptionList(String field) {
        JSONObject jsonDocuments = new JSONObject();
        
        try {
            Map<String, String> queryParamMap = new HashMap<String, String>();
            queryParamMap.put("q", "*");
            queryParamMap.put("rows", "0");
            queryParamMap.put("facet", "on");
            queryParamMap.put("facet.field", field);

            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = client.query(queryParams);

            jsonDocuments = countListTosonObject(response.getFacetFields().get(0).getValues());

        } catch (Exception e) {
            System.out.println("SOLR COLLECTION : " + e);
        }
        return jsonDocuments;
    }

    // Pair => field : value
    // list of fq 
    public JSONObject querySpecificField(List<Pair<String,String>> ls ) {
        JSONObject jsonDocuments = new JSONObject();
        try {
            Map<String, String> queryParamMap = new HashMap<String, String>();
            queryParamMap.put("q", "*");
            queryParamMap.put("start", "0");
            queryParamMap.put("rows", "0");

            for (Pair st : ls) {
                queryParamMap.put("fq",st.getFirst().toString() +":"+ st.getSecond().toString());
            }

            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = client.query(queryParams);
            final SolrDocumentList documents = response.getResults();
            // JSONArray requestObject = new JSONArray();
            jsonDocuments.put("nbFound", documents.getNumFound());


        } catch (Exception e) {
            System.out.println("SOLR COLLECTION : " + e);
        }
        return jsonDocuments;

    }

    

    private MapSolrParams setSolrParamsAdv(String keyword, String start, String rows,String filtersString) {
        Map<String, String> queryParamMap = new HashMap<String, String>();

        queryParamMap.put("q", keyword);

        String[] filterArray = filtersString.split(",");
        for (String f : filterArray) {
            queryParamMap.put("fq",f);
        }

        queryParamMap.put("start", start);
        queryParamMap.put("rows", rows);

        MapSolrParams queryParams = new MapSolrParams(queryParamMap);
        return queryParams;
    }

    private MapSolrParams setSolrParams(String keyword, String start, String rows) {
        Map<String, String> queryParamMap = new HashMap<String, String>();

        queryParamMap.put("q", keyword);

        queryParamMap.put("start", start);
        queryParamMap.put("rows", rows);        

        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        return queryParams;
    }

    private JSONObject countListTosonObject(List<Count> lsC){

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        for (Count count : lsC) {
            JSONObject obj2 = new JSONObject();
            obj2.put("name", count.getName());
            obj2.put("count", count.getCount());
            arr.put(obj2);
            
        }
        obj.put("facet", arr);
        return obj;        
    }

}
