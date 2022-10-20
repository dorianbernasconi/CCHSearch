package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;


/*
 * Singleton that initalize the connection to all the 
 * Solr Core. Each Http client is store in a map
 * 
 */
public class SolrjMap {
    
    private static final Map<String,SolrjClient> clientMap = setHashMap(); 

    private SolrjMap(){
        // Empty, Singleton Class
    }

    private static Map<String,SolrjClient>  setHashMap(){
        Map<String,SolrjClient> map = new HashMap<String,SolrjClient>();
        map.put("01_MANDATS_VAL_A",new SolrjClient(("01_MANDATS_VAL_A")));
        return map;
    }

    public static SolrjClient getSolrClient(String clientName){
        return clientMap.get(clientName);
    }
}
