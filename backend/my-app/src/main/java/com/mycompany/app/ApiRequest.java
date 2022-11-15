package com.mycompany.app;

import org.apache.solr.client.solrj.SolrRequest;
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
import org.apache.commons.math3.util.Pair;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRequest {

    private Map<Page, List<Page>> map;

    public JSONObject solrRequest(String core, String keyword, String start, String rows) {

        JSONObject response = new JSONObject();
        try {

            SolrjClient client = SolrjMap.getSolrClient(core);
            response = client.solrRequest( "",keyword, start, rows);
        } catch (Exception e) {
            System.out.println("API REQUEST SOLR_REQUEST : " + e);
        }
        return response;

    }

    public JSONObject solrRequestAdv(String core, String keyword, String start, String rows, String filter) {


        JSONObject response = new JSONObject();
        try {
            // convert the array to a list of pairs (key, value)

            SolrjClient client = SolrjMap.getSolrClient(core);
            response = client.solrRequestAdv( "",keyword, start, rows,filter);
        } catch (Exception e) {
            System.out.println("API REQUEST SOLR_REQUEST : " + e);
        }
        return response;

    }

    public JSONObject elasticRequest(String keyword, Integer size, String field, Integer min, Integer max) {

        List<Page> pageList = new ArrayList<>();

        try {
            ElasticClient client = new ElasticClient();
            pageList = client.query(keyword, 100, field, min, max);

        } catch (Exception e) {
            System.out.println("API REQUEST ELASTIC REQUEST" + e);
        }

        WordSimilarity ws = new WordSimilarity();
        ws.removeListSimilarity(pageList);
        map = ws.getMap();

        return pageListToJsonObject(ws.getSortList());
    }

    public JSONObject getFieldOptionList(String core,String field) {

        JSONObject response = new JSONObject();
        try {
            SolrjClient client = SolrjMap.getSolrClient(core);
            response = client.getFieldOptionList(field);
        } catch (Exception e) {
            System.out.println("API REQUEST GET FIELD OPTION LIST : " + e);
        }
        return response;
    }

    public JSONObject elasticSimilitude(String query) {

        List<Page> lsP = new ArrayList<>();
        System.out.println( "query"+query);

        for (Map.Entry<Page, List<Page>> entry : map.entrySet()) {

            if (entry.getKey().getJpgName().equals(query)) {
                System.out.println( "if");

                return pageListToJsonObject(entry.getValue());
            }
        }
        return pageListToJsonObject(lsP);
    }




    public JSONObject pageListToJsonObject(List<Page> pageList) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        for (Page page : pageList) {

            array.add(page.pageToJson());
        }
        
        json.put("documents", array);
        return json;
    }
}
