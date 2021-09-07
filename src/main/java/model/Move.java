package model;

public class Move {
    private int[] end = new int[2];
    public Move(int[] start, int[] direction){
        end[0] = start[0] + direction[0];
        end[1] = start[1] + direction[1];
    }

    public int[] getEnd() {
        return end;
    }
}
