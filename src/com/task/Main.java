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
            System.out.println ("Please, input count of rows and count of columns");
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
            scStdin.close();
        } else {
            File file = new File(args[0]);
            if (!file.isFile()) {
                Usage.usage(FILEERR);
            } else {
                try {
                    map = Map.readMapFromFile(file);
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
            Map.allDead(map);
        }
    }
}
