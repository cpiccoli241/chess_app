package model;

public class Move {
    private int[] start;
    private int[] end = new int[2];
    public Move(int[]start, int end1, int end2){
        this.start = start;
        this.end = new int[]{end1, end2};
    }
    public Move(int[] start, int[] direction){
        this.start=start;
        end[0] = start[0] + direction[0];
        end[1] = start[1] + direction[1];
    }

    public int[] getEnd() {
        return end;
    }

    public int[] getStart() {
        return start;
    }
    @Override
    public boolean equals(Object o){
        Move other = (Move)o;
        return end[0] == other.end[0] && end[1] == other.end[1] &&
                start[1] == other.start[1] && start[0] == other.start[0];
    }
}
