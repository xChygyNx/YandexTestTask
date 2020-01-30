package com.task;

import java.util.ArrayList;
import java.io.*;

public class Map {
    private final static int DEAD = 0;
    private final static int LIVE = 1;
    private final static int NOTVALIDMAP = 4;
    public static ArrayList<ArrayList<Integer>> generateRandomMap(int row, int column) {
        ArrayList<ArrayList<Integer>> map = new ArrayList();
        for (int i = 0; i < row; i++) {
            map.add(new ArrayList());
        }
        for (int i = 0; i <row; i++) {
            for (int j = 0; j < column; j++) {
                map.get(i).add(getRandomCell());
            }
        }
        return map;
    }

    public static ArrayList<ArrayList<Integer>> readMapFromFile (File file) throws Exception {
        ArrayList<ArrayList<Integer>> map = new ArrayList();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int i = -1;
        while (br.ready()) {
            i++;
            String[] elems = br.readLine().split(" ");
            map.add(new ArrayList());
            for (int j = 0; j < elems.length; j++){
                try {
                    map.get(i).add(Integer.parseInt(elems[j]));
                } catch (Exception e) {
                    Usage.usage(NOTVALIDMAP);
                }
            }
        }
        return map;
    }

    public static ArrayList<ArrayList<Integer>> processingMap(ArrayList<ArrayList<Integer>> map) {
        int liveNeighbors;
        ArrayList<ArrayList<Integer>> newMap = new ArrayList();
        int rowCount = map.size();
        int colCount = map.get(0).size();
        for (int i = 0; i < rowCount; i++) {
            newMap.add(new ArrayList());
            for (int j = 0; j < colCount; j++) {
                liveNeighbors = 0;
                if (i > 0 && j > 0) {
                    liveNeighbors += map.get(i - 1).get(j - 1);
                }
                if (i > 0) {
                    liveNeighbors += map.get(i - 1).get(j);
                }
                if (i > 0 && j < colCount - 1) {
                    liveNeighbors += map.get(i - 1).get(j + 1);
                }
                if (j > 0) {
                    liveNeighbors += map.get(i).get(j - 1);
                }
                if (j < colCount - 1) {
                    liveNeighbors += map.get(i).get(j + 1);
                }
                if (i < rowCount - 1 && j > 0) {
                    liveNeighbors += map.get(i + 1).get(j - 1);
                }
                if (i < rowCount - 1) {
                    liveNeighbors += map.get(i + 1).get(j);
                }
                if (i < rowCount - 1 && j < colCount - 1) {
                    liveNeighbors += map.get(i + 1).get(j + 1);
                }
                newMap.get(i).add(Cell.killRebirthCell((int)map.get(i).get(j), liveNeighbors));
            }
        }
        return newMap;
    }

    public static void printMap(ArrayList<ArrayList<Integer>> map) {
        System.out.print("\u001B[1m");
        for (int i = 0; i < map.size(); i++) {
            ArrayList row = map.get(i);
            for (int j = 0; j < row.size(); j++) {
                if ((int)row.get(j) == LIVE) {
                    System.out.printf("\u001B[32m1 \u001B[37m");
                } else if ((int)row.get(j) == DEAD) {
                    System.out.printf("\u001B[31m0 \u001B[37m");
                }
            }
            System.out.println();
        }
        System.out.print("\u001B[0m");
    }

    private static Integer getRandomCell() {
        return ((int)(Math.random() * 2));
    }
}
