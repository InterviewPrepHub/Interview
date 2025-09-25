package com.interview.Interview.Interview2025.Atlassian.Round4;


import java.util.*;

interface MostPopular {
    void increasePopularity(Integer contentId);  //--> increments populaity of content
    Integer mostPopular();  //---> return contentId with highestPopoulaoty so  far
    void decreasePopularity(Integer contentId);    // --> decrements populaity of content
}

class PopularityTracker implements MostPopular {

    private final Map<Integer, Integer> contentToPopularity = new HashMap<>();
    private final TreeMap<Integer, LinkedHashSet<Integer>> popularityToContentIds = new TreeMap<>();


    @Override
    public void increasePopularity(Integer contentId) {

        int prevPop = contentToPopularity.getOrDefault(contentId, 0);
        int newPop = prevPop +1;
        contentToPopularity.put(contentId, newPop);

        if(prevPop > 0) {
            Set<Integer> prevSet  = popularityToContentIds.get(prevPop);
            prevSet.remove(contentId);
            if (prevSet.isEmpty()) {
                popularityToContentIds.remove(prevPop);
            }
        }

        //add to new pop set
        popularityToContentIds.computeIfAbsent(newPop, k -> new LinkedHashSet<>()).add(contentId);


    }

    @Override
    public Integer mostPopular() {

        if(popularityToContentIds.isEmpty()) {
            return -1;
        }

        Map.Entry<Integer, LinkedHashSet<Integer>> highestPopVal = popularityToContentIds.lastEntry();
        LinkedHashSet<Integer> res = highestPopVal.getValue();

        // 1 -> 2  -> 3

        // implement custom LinkedHashSet with reference to tail pointer always --> O(1)
        Integer lastInsertedVal = null;
        for (Integer id : res) {
            lastInsertedVal = id;
        }
//        System.out.println(lastInsertedVal);
        return lastInsertedVal;
//        System.out.println(res);
//        return res.iterator().next();
    }

    @Override
    public void decreasePopularity(Integer contentId) {

        if(!contentToPopularity.containsKey(contentId)) return;

        int prevPop = contentToPopularity.get(contentId);
        if(prevPop == 0) return;
        int newPop = prevPop-1;
        contentToPopularity.put(contentId, newPop);

        //remove from the previous populaity set
        Set<Integer> prevSet = popularityToContentIds.get(prevPop);
        prevSet.remove(contentId);
        if (prevSet.isEmpty()) {
            popularityToContentIds.remove(prevPop);
        }


        //add to new pop set if newPop > 0
        if(newPop > 0) {
            popularityToContentIds.computeIfAbsent(newPop, k -> new LinkedHashSet<>()).add(contentId);
        } else {
            contentToPopularity.remove(contentId);
        }
    }

    public static void main(String[] args) {
        PopularityTracker tracker = new PopularityTracker();

        System.out.println("Test case1");

        tracker.decreasePopularity(7);
        System.out.println(tracker.mostPopular());
        tracker.increasePopularity(7);
        tracker.increasePopularity(7);
        tracker.increasePopularity(8);
        System.out.println(tracker.mostPopular()); //return 7
        tracker.increasePopularity(8);
        tracker.increasePopularity(8);
        System.out.println(tracker.mostPopular());        // returns 8
        tracker.decreasePopularity(8);
        tracker.decreasePopularity(8);
        System.out.println(tracker.mostPopular());        // returns 7
        tracker.decreasePopularity(7);
        tracker.decreasePopularity(7);
        tracker.decreasePopularity(8);
        System.out.println(tracker.mostPopular());        // returns -1 since there is no content with popularity greater than 0

        System.out.println();
        System.out.println("Test case2");

        tracker.increasePopularity(9); //pop : 1
        tracker.increasePopularity(7); //pop : 1
        tracker.increasePopularity(8);  //pop : 1
        tracker.increasePopularity(7);  //pop : 2
        tracker.increasePopularity(9);
        tracker.increasePopularity(8);
        System.out.println(tracker.mostPopular()); // returns 8

        tracker.decreasePopularity(8);
        System.out.println(tracker.mostPopular()); // returns 9


        /*tracker.increasePopularity(8);
//        tracker.increasePopularity(8);

        System.out.println(tracker.contentToPopularity);
        System.out.println(tracker.popularityToContentIds);

        tracker.decreasePopularity(8);
        tracker.decreasePopularity(8);

        System.out.println(tracker.contentToPopularity);
        System.out.println(tracker.popularityToContentIds);*/
    }
}

/*
most popular - O(k) -> K is number of contentIds in the top popularity bucket
 */
