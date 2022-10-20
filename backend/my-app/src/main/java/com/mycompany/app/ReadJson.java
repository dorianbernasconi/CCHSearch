package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

    
    public ReadJson(){

    }

    public static JSONObject readFile(String filename){
        JSONParser jsonParser = new JSONParser();
        JSONObject data = new JSONObject();
        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            data.put("documents",(JSONArray) obj);   
          
            //Iterate over employee array
 
        } catch (Exception e) {
            System.out.println(e);            
        } 

        return data;
    }
}


