package com.company;
import java.util.*;

public class DynamicStorageAllocationAlgorithms {
    public static void main(String[] args) {
        int[] data = {50,30,20,80,150};
        int[] freeBlocks = {70,50,90,100,30};
        System.out.println("______________________________________");
        System.out.println("First fit");
        firstFit(data , freeBlocks);
        System.out.println("______________________________________");
        System.out.println("Best fit:");

        ArrayList<Integer> freeBlockList =  new ArrayList<>();
        freeBlockList.add(50);
        freeBlockList.add(70);
        freeBlockList.add(30);
        freeBlockList.add(100);
        freeBlockList.add(20);
        bestFit(data,freeBlockList);
        System.out.println("______________________________________");
        System.out.println("Worst fit:");
        ArrayList<Integer> blockListWF =  new ArrayList<>();
        blockListWF.add(50);
        blockListWF.add(70);
        blockListWF.add(30);
        blockListWF.add(100);
        blockListWF.add(20);
        worstFit(data, blockListWF);

    }

    // FIRST FIT.
    public static void firstFit(int[] data, int[] freeBlocks){
        boolean alloc;
        ArrayList<Integer> allocated = new ArrayList<>();

        for (int i = 0; i < data.length; i++){
            alloc = true;
            for (int k = 0; k < freeBlocks.length; k++){
                if (!allocated.contains(k)){
                    if (data[i] <= freeBlocks[k]){
                        System.out.println("Block: "+(k+1)+" Process: " + (i+1));
                        allocated.add(k);
                        alloc = true;
                        break;
                    }
                    else
                        alloc = false;

                }
            }
            if (!alloc)
                System.out.println("Process "+(i+1)+" could not be placed due to external fragmentation.");


        }

    }

    public static void bestFit(int[] data, ArrayList<Integer> freeBlocks){
        int index=1000;
        for(int i=0;i<data.length;i++) {
            int min=1000;
            for(int j=0;j<freeBlocks.size();j++) {
                if(freeBlocks.get(j) != null && freeBlocks.get(j)>=data[i]) {
                    if(freeBlocks.get(j)<min) {
                        min = freeBlocks.get(j);
                        index = j;
                    }
                }
            }
            if(index!=1000) {
                System.out.println("Process "+(i+1)+" is in block "+(index+1));
                freeBlocks.set(index,null);
                index = 1000;
            } else {
                System.out.println("Process "+(i+1)+" could not be placed due to external fragmentation.");
            }
        }
    }

    public static void worstFit(int[] data, ArrayList<Integer> freeBlocks){
        int index=1000;
        for(int i=0;i<data.length;i++) {
            int max=-10;
            for(int j=0;j<freeBlocks.size();j++) {
                if(freeBlocks.get(j) != null && freeBlocks.get(j)>=data[i]) {
                    if(freeBlocks.get(j)>max) {
                        max = freeBlocks.get(j);
                        index = j;
                    }
                }
            }
            if(index!=1000) {
                System.out.println("Process "+(i+1)+" is in block "+(index+1));
                freeBlocks.set(index,null);
                index = 1000;
            } else {
                System.out.println("Process "+(i+1)+" could not be placed due to external fragmentation.");
            }
        }
    }
}