package com.company;

import java.util.ArrayList;

public class BankersAlgorithm {

    public static void main(String[] args) {
        boolean[] process = new boolean[5]; //to detect processes if completed
        int[][] allocationOfProcess = new int[5][3];
        int[][] maxOfProcess = new int[5][3];
        int[][] needOfProcess = new int[5][3];
        int[] availableResource = new int[3];
        int counter = 0; // to detect how many times loop iterated
        ArrayList<Integer> safeSet = new ArrayList<>(5);


        //Allocation values of processes
        allocationOfProcess[0][0] = 0;
        allocationOfProcess[0][1] = 1;
        allocationOfProcess[0][2] = 0;
        allocationOfProcess[1][0] = 2;
        allocationOfProcess[1][1] = 0;
        allocationOfProcess[1][2] = 0;
        allocationOfProcess[2][0] = 3;
        allocationOfProcess[2][1] = 0;
        allocationOfProcess[2][2] = 2;
        allocationOfProcess[3][0] = 2;
        allocationOfProcess[3][1] = 1;
        allocationOfProcess[3][2] = 1;
        allocationOfProcess[4][0] = 0;
        allocationOfProcess[4][1] = 0;
        allocationOfProcess[4][2] = 2;

        //Maximum resources of processes
        maxOfProcess[0][0] = 8;
        maxOfProcess[0][1] = 5;
        maxOfProcess[0][2] = 3;
        maxOfProcess[1][0] = 3;
        maxOfProcess[1][1] = 2;
        maxOfProcess[1][2] = 2;
        maxOfProcess[2][0] = 9;
        maxOfProcess[2][1] = 0;
        maxOfProcess[2][2] = 2;
        maxOfProcess[3][0] = 6;
        maxOfProcess[3][1] = 2;
        maxOfProcess[3][2] = 2;
        maxOfProcess[4][0] = 4;
        maxOfProcess[4][1] = 5;
        maxOfProcess[4][2] = 3;

        //Initial available resources
        availableResource[0] = 3;
        availableResource[1] = 3;
        availableResource[2] = 2;

        //Needed resources of processes
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                needOfProcess[i][j] = maxOfProcess[i][j] - allocationOfProcess[i][j];
            }
        }

        for (int i = 0; ; i++) {
            if (!process[i]) { // if process is not completed
                if (needOfProcess[i][0] <= availableResource[0] && needOfProcess[i][1] <= availableResource[1] && needOfProcess[i][2] <= availableResource[2]) { //if available resources are enough for that process

                    for (int j = 0; j < 3; j++) { // for each resource category of process
                        availableResource[j] = availableResource[j] - needOfProcess[i][j]; // subtract need from available
                        allocationOfProcess[i][j] = allocationOfProcess[i][j] + needOfProcess[i][j]; // add need to allocation
                        availableResource[j] = availableResource[j] + allocationOfProcess[i][j]; //put allocated resources to availables when process completed
                    }
                    process[i] = true; //set process as completed process
                    safeSet.add(i); // add completed process in a result set
                }
            }
            if (process[0] && process[1] && process[2] && process[3] && process[4]) { // if all processes are completed
                System.out.println("Safe"); // safe state
                for (int j = 0; j < safeSet.size(); j++) { // print safe execution sequence of processes that is found by algorithm
                    System.out.print("P" + safeSet.get(j) + "  ");
                }
                break;
            }

            // if all processes checked, go back to the first process and check again
            if (i == 4) {
                i = -1;
            }

            counter++;

            //if loop checks all processes maximum possible times for detecting safe set and still there is uncompleted process, then there is no safe state
            if (counter == process.length * process.length) {
                System.out.println("Unsafe");
                break;
            }

        }
    }
}