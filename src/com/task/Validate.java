package com.task;

import java.util.ArrayList;

public class Validate {
    private final static int NOTVALIDMAP = 4;
    static public void validateMap(ArrayList<ArrayList<Integer>> map) {
        int lenRow = map.get(0).size();
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).size() != lenRow) {
                Usage.usage(NOTVALIDMAP);
            }
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) != 0 && map.get(i).get(j) != 1) {
                    Usage.usage(NOTVALIDMAP);
                }
            }
        }
    }
}
