package com.task;

public class Usage {
    public static void usage(int err) {
        if (err == 1) {
            System.out.println("Need to input the number");
            System.exit(0);
        } else if (err == 2) {
            System.out.println("Number must to be positive");
            System.exit(0);
        } else if (err == 3) {
            System.out.println("File isn't exist");
            System.exit(0);
        } else if (err == 4) {
            System.out.println("Map isn't valid");
            System.exit(0);
        }
    }
}
