package com.task;

public class Cell {
    private static int DEAD = 0;
    private static int LIVE = 1;
    public static Integer killRebirthCell(int cellStatus, int liveNeighbors) {
        if (cellStatus == LIVE) {
            if (liveNeighbors !=2 && liveNeighbors !=3) {
                return (0);
            }
        } else {
            if (liveNeighbors == 3) {
                return (1);
            }
        }
        return(cellStatus);
    }
}
