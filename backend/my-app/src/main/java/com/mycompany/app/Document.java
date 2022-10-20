package com.mycompany.app;

import org.json.simple.*;

import java.util.*;

import javax.naming.directory.ModificationItem;

public class Document {

    private String id;
    private String fileName;
    private String filePath;
    private String affaire;
    private Long lastModificationDate;
    private Boolean knowHow;
    private String jpgName;

    // can add if the page is a detail or a plan

    public Document(String id, String fileName,String filePath,String affaire,Long lastModificationDate, String jpgName,Boolean knowHow) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.affaire = affaire;
        this.lastModificationDate = lastModificationDate;
        this.jpgName = jpgName;
        this.knowHow = knowHow;
    }

    

    public JSONObject pageToJson() {
        JSONObject documentObj = new JSONObject();
        documentObj.put("id", this.id);
        documentObj.put("fileName", this.fileName);
        documentObj.put("filePath", this.filePath);
        documentObj.put("affaire", this.affaire);
        documentObj.put("lastModificationDate", this.lastModificationDate);
        documentObj.put("jpgName", this.jpgName);
        documentObj.put("konwHow", this.knowHow);

        return documentObj;
    }

    public Document() {
        // default construct
    }

}