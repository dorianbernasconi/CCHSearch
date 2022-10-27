package com.mycompany.app;

import org.json.simple.*;

import java.util.*;

public class Page {

    private String id;
    private String fileName;
    private String filePath;
    private List<String> affaireName;
    private List<String> fileNameDecomposed;
    private List<String> filePathDecomposed;
    private String price;
    private String authorProject;
    private Integer pageNumber;
    private String jpgName;
    private Double score;
    private Long lastModificationDate;
    private Integer echelle;
    private List<String> wordList;
    private List<String> wordPerTfIdf;
    private Boolean isFavoris;

    // can add if the page is a detail or a plan

    public Page(String filePath, String fileName, Integer pageNumber, List<String> wordList, String jpgName) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.pageNumber = pageNumber;
        this.wordList = wordList;
        this.jpgName = jpgName;
    }

    public Page(String filePath, String fileName, Integer pageNumber, List<String> wordList, String jpgName,
            List<String> wordPerTfIdf) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.pageNumber = pageNumber;
        this.wordList = wordList;
        this.jpgName = jpgName;
        this.wordPerTfIdf = wordPerTfIdf;
    }

    public Page() {
        // default construct
    }

    private static String isDetail(String path) {
        int index = path.lastIndexOf('/');

        String filename = path.substring(index + 1);
        if (filename.toLowerCase().contains("detail")) {
            return "detail";
        } else {
            return "plan";
        }
    }

    private static String stringMatches(List<String> page) {
        String regExpr10 = "1:\\d{1,4}$";
        String regExpr11 = "1:[5,6,7,8,9]$";
        String regExpr20 = "1/\\d{1,4}$";
        String regExpr21 = "1/[5,6,7,8,9]$";
        for (String w : page) {
            if (w.matches(regExpr10) || w.matches(regExpr11)) {

                return w;
            } else if (w.matches(regExpr20) || w.matches(regExpr21)) {
                return w.replace("/", ":");
            }
        }
        return "";
    }

    public static List<List<String>> pageListToStringList(List<Page> pl) {
        List<List<String>> sl = new ArrayList<>();
        for (Page page : pl) {
            sl.add(page.getWordList());
        }
        return sl;
    }

    public JSONObject pageToJson() {
        JSONObject pageObj = new JSONObject();
        pageObj.put("id", this.id);
        pageObj.put("filename", this.fileName);
        pageObj.put("pagenumber", this.pageNumber);
        pageObj.put("filePath", this.filePath);
        pageObj.put("score", this.score);
        pageObj.put("affaire", this.affaireName);
        pageObj.put("lastModificationDate", this.lastModificationDate);
        pageObj.put("affairename", this.affaireName);
        pageObj.put("jpgname", this.jpgName);
        pageObj.put("echelle", stringMatches(this.wordList));
        pageObj.put("wordlist", this.wordList);
        pageObj.put("kh", this.isFavoris);
        pageObj.put("price", this.price);

        return pageObj;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFileNameDecomposed(List<String> ls) {
        this.fileNameDecomposed = ls;
    }

    public List<String> getFileNameDecomposed() {
        return this.fileNameDecomposed;
    }

    public void setFilePathDecomposed(List<String> ls) {
        this.filePathDecomposed = ls;
    }

    public List<String> getFilePathDecomposed() {
        return this.filePathDecomposed;
    }

    public void setWordPerTfIdf(List<String> wordPerTfIdf) {
        this.wordPerTfIdf = wordPerTfIdf;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthorProject() {
        return this.authorProject;
    }

    public void setAuthorProject(String authorProject) {
        this.authorProject = authorProject;
    }

    public List<String> getAffaireName() {
        return this.affaireName;
    }

    public void setLastModificationDate(Long date) {
        this.lastModificationDate = date;
    }

    public void setAffaireName(List<String> affaireName) {
        this.affaireName = affaireName;
    }

    public String getJpgName() {
        return this.jpgName;
    }

    public Integer getEchelle() {
        return this.echelle;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<String> getWordList() {
        return this.wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    public Boolean getIsFavoris() {
        return this.isFavoris;
    }

    public void setIsFavoris(Boolean isFavoris) {
        this.isFavoris = isFavoris;
    }

    @Override
    public String toString() {
        return "ID : " + this.id + ", Filename : " + this.fileName + ", FilePath : " + this.filePath
                + ", AffaireName : " + this.affaireName + " Favoris : " + isFavoris;
    }

}