package com.mycompany.app;

import java.util.*;

public class WordSimilarity {

    private List<Page> lsSort;
    private Map<Page, List<Page>> map;

    public WordSimilarity() {
        lsSort = new ArrayList<>();
        map = new HashMap<Page, List<Page>>();
    }

    private boolean isClose(Page p1, Page p2) {
        Double closability = closability(p1, p2);

        // return closability > 0.55;
        return closability > 0.45;
    }

    private Double closability(Page p1, Page p2) {
        Set<String> s1 = new HashSet<>(p1.getWordList());
        Set<String> s2 = new HashSet<>(p2.getWordList());
        Set<String> s3 = new HashSet<>(p2.getWordList());
        s2.addAll(s1);
        s3.retainAll(s1);
        return ((double) s3.size() / (double) s2.size());
    }

    public void removeListSimilarity(List<Page> lsStart) {
        int start = 0;
        for (Page page : lsStart) {
            boolean isClose = false;
            for (int i = start; i < lsSort.size(); i++) {
                if (isClose(page, lsSort.get(i))) {
                    map.get(lsSort.get(i)).add(page);
                    isClose = true;
                }
            }
            if (!isClose) {
                lsSort.add(page);
                map.put(page, new ArrayList<>());
                if (lsSort.size() > 15) {
                    start += 1;
                }
            } else {

            }
        }
    }

    public Map<Page, List<Page>> getMap() {
        return map;
    }

    public List<Page> getSortList() {
        return lsSort;
    }

    /*
     * for (Page pageCurr : lsPage) {
     * map.put(pageCurr, new ArrayList<>());
     * for (Page pageTmp : lsPage) {
     * if (!pageTmp.equals(pageCurr) && WordSimilarity.isClose(pageCurr, pageTmp)) {
     * map.get(pageCurr).add(pageTmp);
     * } else {
     * 
     * if (!lsPageRes.contains(pageTmp)) {
     * lsPageRes.add(pageTmp);
     * }
     * 
     * }
     * }
     * }
     */

}
