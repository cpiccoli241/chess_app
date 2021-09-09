package model;

import static java.lang.Math.abs;

public class Move {
    private int[] start;
    private int[] end = new int[2];
    private int[] dir = new int[2];
    public Move(int[]start, int end1, int end2){
        this.start = start;
        this.end = new int[]{end1, end2};
        dir[0] = end[0]-start[0];
        if(dir[0] != 0)
            dir[0] = dir[0]/abs(dir[0]);
        dir[1] = end[1] - start[1];
        if(dir[1] != 0)
            dir[1] = dir[1]/abs(dir[1]);
    }
    public Move(int[] start, int[] direction){
        this.start=start;
        end[0] = start[0] + direction[0];
        end[1] = start[1] + direction[1];
        dir = direction;
        if(dir[0] != 0)
            dir[0] = dir[0]/abs(dir[0]);
        dir[1] = end[1] - start[1];
        if(dir[1] != 0)
            dir[1] = dir[1]/abs(dir[1]);
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
    public int[] getDir(){
        return dir;
    }
}
