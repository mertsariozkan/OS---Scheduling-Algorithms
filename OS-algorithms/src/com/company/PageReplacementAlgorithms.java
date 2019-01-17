package com.company;

import java.util.*;

public class PageReplacementAlgorithms {

    public static void main(String[] args) {
        int[] sequence = new int[]{1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 4, 3, 7, 6, 6, 5, 3, 2, 1, 2, 3, 6}; // sequence that is already given
        System.out.println("Number of page faults in FIFO: " + FirstInFirstOutPageReplacement(sequence, 3)); // call for FIFO
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Number of page faults in LRU: " + LeastRecentlyUsedPageReplacement(sequence, 3)); // call for LRU
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Number of page faults in Optimal: " + OptimalPageReplacement(sequence, 3)); // call for Optimal
    }

    public static int FirstInFirstOutPageReplacement(int[] sequence, int frameNumber) {
        Queue<Integer> queue = new LinkedList<>();
        int numberOfPageFaults = 0; //initial page fault number is zero
        for (int page : sequence) { //for each page in sequence.....
            // if there is an empty frame in queue and queue doesn't contain the page, just add page to the queue and increment page fault number
            if (queue.size() < frameNumber && !queue.contains(page)) {
                queue.offer(page);
                numberOfPageFaults++;
            }
            // if queue has lenght of frame number and not contain the same page in it, add the page to queue, swap out the oldest page from the queue and increment page fault number
            else if (!queue.contains(page)) {
                queue.poll();
                queue.offer(page);
                numberOfPageFaults++;
            }
            System.out.println(queue); // display the state of the queue
        }
        return numberOfPageFaults;
    }

    public static int LeastRecentlyUsedPageReplacement(int[] sequence, int frameNumber) {
        int numberOfPageFaults = 0; //initial page fault number is zero
        int[] flag = new int[frameNumber]; // array to count LRU situation of each item in the list
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < sequence.length; i++) { // for each element in the sequence...
            //If there is empty frame in list and list doesn't contain the page will be added,
            if (list.size() < frameNumber && !list.contains(sequence[i])) {
                list.add(sequence[i]); // add page to the list,
                //increment each frame flag that is smaller than index of sequence item by one,
                for (int j = i; j >= 0; j--) {
                    flag[j]++;
                }
                numberOfPageFaults++; // and increase the number of page faults one.
            }
            // If there is no empty frame in list and list doesn't contain the next sequence item,
            else if (!list.contains(sequence[i])) {
                int max = 0;
                int index = 0;
                for (int k = 0; k < flag.length; k++) { // foreach flag element
                    if (flag[k] >= max) {
                        max = flag[k];     // calculate the flag item with maximum number and set to max (this gives least recently used page in list)
                        index = k; // assign maximum flag item's index to variable index
                    }
                }
                list.set(index, sequence[i]); // swap the list element that has maximum flag value with next sequence item
                numberOfPageFaults++; // increment number of page faults by one
                flag[index] = 0; // at the end, set the updated list element's flag to zero
                //increment all flag items except updated one by one
                for (int k = 0; k < flag.length; k++) {
                    if (k != index) {
                        flag[k]++;
                    }
                }
            }
            //if next sequence item is already in the list
            else {
                int index = 0;
                //set flag value of already containing list item to zero
                for (int k = 0; k < flag.length; k++) {
                    if (list.get(k) == sequence[i]) {
                        flag[k] = 0;
                        index = k;
                    }
                }
                //increment all flag items except updated one by one
                for (int k = 0; k < flag.length; k++) {
                    if (k != index) {
                        flag[k]++;
                    }
                }
            }
            System.out.println(list); //display the state of the list
        }

        return numberOfPageFaults;
    }

    public static int OptimalPageReplacement(int[] sequence, int frameNumber) {
        int numberOfPageFaults = 0;
        int[] distance = new int[frameNumber]; //distance array for keeping distances of list item to determine which is furthest
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < sequence.length; i++) { //for each sequence element...
            //If there is empty frame in list and list doesn't contain the page will be added,
            if (list.size() < frameNumber && !list.contains(sequence[i])) {
                list.add(sequence[i]); //add sequence item to the list
                numberOfPageFaults++; //increment page fault number by one
            } else if (!list.contains(sequence[i])) { // if list doesn't contain the next sequence item,
                for (int k = 0; k < list.size(); k++) { // for each list item
                    boolean isFound = false;
                    for (int j = i + 1; j < sequence.length; j++) { //for each sequence item which comes after the item will be added,
                        if (sequence[j] == list.get(k)) { // if we find any sequence element same with list element,
                            distance[k] = j; // set distance
                            isFound = true;
                            break; // break the loop
                        }
                    }
                    if (!isFound) { // if we can't find any sequence element same with list element,
                        distance[k] = 1000; // set distance to very large number because this is the element must swapped out
                    }
                }
                int maxDistance = 0;
                int index = 0;
                for (int j = 0; j < distance.length; j++) {
                    if (distance[j] >= maxDistance) {
                        maxDistance = distance[j]; //find the list item with maximum distance value
                        index = j; // index of list item with maximum distance value
                    }
                }
                list.set(index, sequence[i]); // swap furthest list item with the next sequence item
                numberOfPageFaults++; // increment page fault number by one

            }
            System.out.println(list); // display the state of list
        }
        return numberOfPageFaults;
    }

}