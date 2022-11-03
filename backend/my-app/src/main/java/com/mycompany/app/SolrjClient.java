package com.mycompany.app;

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

    public void solrCollection() {
        try {
            JSONObject jsonDocuments = new JSONObject();

            Map<String, String> queryParamMap = new HashMap<String, String>();
            queryParamMap.put("q", "Facture");

            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = client.query(queryParams);
            final SolrDocumentList documents = response.getResults();
            jsonDocuments.put("nbFound", documents.getNumFound());
            jsonDocuments.put("documents", documents);

            final List<String> collectionsList = CollectionAdminRequest.List.listCollections(client);
            System.out.println("documents : " + collectionsList);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private MapSolrParams setSolrParams(String keyword, String start, String rows) {
        Map<String, String> queryParamMap = new HashMap<String, String>();

        queryParamMap.put("q", keyword);

        queryParamMap.put("start", start);
        queryParamMap.put("rows", rows);

        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        return queryParams;
    }

}
