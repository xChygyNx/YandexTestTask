package com.task;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main {
    private static final int NUMERR = 1;
    private static final int NUMNEGATIVEERR = 2;
    private static final int FILEERR = 3;
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> map = new ArrayList();
        if (args.length == 0) {
            Scanner scStdin = new Scanner(System.in);
            if (!scStdin.hasNextInt()) {
                Usage.usage(NUMERR);
            }
            int m = scStdin.nextInt();
            if (!scStdin.hasNextInt())
                Usage.usage(NUMERR);
            int n = scStdin.nextInt();
            if (m < 1 || n < 1) {
                Usage.usage(NUMNEGATIVEERR);
            }
            map = Map.generateRandomMap(m, n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%d ", map.get(i).get(j));
                }
                System.out.println();
            }
        } else {
            File file = new File(args[0]);
            if (!file.isFile()) {
                Usage.usage(FILEERR);
            } else {
                try {
                    map = Map.readMapFromFile(file);
                    /*for (int i = 0; i < map.size(); i++){
                        for (int j = 0; j < map.get(i).size(); j++) {
                            System.out.printf("%d ",map.get(i).get(j));
                        }
                        System.out.println();
                    }*/
                } catch (Exception e) {
                    Usage.usage(FILEERR);
                }
                Validate.validateMap(map);
            }
        }
        String command = System.getProperty("os.name").contains("Windows") ? "cls" : "clear";
        while (true) {
            try {
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                System.out.println("Can't clear console");
            }
            map = Map.processingMap(map);
            Map.printMap(map);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
