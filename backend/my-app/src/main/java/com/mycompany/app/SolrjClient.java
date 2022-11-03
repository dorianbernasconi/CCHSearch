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

    public JSONObject solrRequest(String keyword, String start, String rows, String collection) {
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

    public JSONObject getAllAffaire() {
        JSONObject jsonDocuments = new JSONObject();

        try {

            Map<String, String> queryParamMap = new HashMap<String, String>();
            queryParamMap.put("q", "*");
            queryParamMap.put("rows", "0");
            queryParamMap.put("facet", "on");
            queryParamMap.put("facet.field", "affaire");

            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = client.query(queryParams);

            jsonDocuments = countListTosonObject(response.getFacetFields().get(0).getValues());

        } catch (Exception e) {
            System.out.println("SOLR COLLECTION : " + e);
        }
        return jsonDocuments;
    }

    public JSONObject getKnowHowNumber() {
        JSONObject jsonDocuments = new JSONObject();
        
        try {
            Map<String, String> queryParamMap = new HashMap<String, String>();
            queryParamMap.put("q", "*");
            queryParamMap.put("rows", "0");
            queryParamMap.put("facet", "on");
            queryParamMap.put("facet.field", "kh");

            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = client.query(queryParams);

            jsonDocuments = countListTosonObject(response.getFacetFields().get(0).getValues());

        } catch (Exception e) {
            System.out.println("SOLR COLLECTION : " + e);
        }
        return jsonDocuments;
    }

    public JSONObject querySpecificField() {

        
        JSONObject jsonDocuments = new JSONObject();
        List<Pair<String,String>> ls = new ArrayList<>();
        
        try {
            Map<String, String> queryParamMap = new HashMap<String, String>();
            queryParamMap.put("q", "*");
            queryParamMap.put("start", "0");

            queryParamMap.put("rows", "10");


            for (Pair st : ls) {
                queryParamMap.put("fq",st.getFirst().toString() + st.getSecond().toString());
            }
            queryParamMap.put("fq", "ftype:Image");

            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = client.query(queryParams);

            final SolrDocumentList documents = response.getResults();
            // JSONArray requestObject = new JSONArray();
            jsonDocuments.put("nbFound", documents.getNumFound());
            jsonDocuments.put("documents", documents);

        } catch (Exception e) {
            System.out.println("SOLR COLLECTION : " + e);
        }
        return jsonDocuments;

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
