package model;

import java.util.Arrays;

import static java.lang.Math.abs;

public class Move {
    private int[] start = new int[2];
    private int[] end = new int[2];
    private int[] dir = new int[2];
    Piece tomove;

    public Move(int start1, int start2, int end1, int end2, Piece tomove) {
        start[0] = start1;
        start[1] = start2;
        end[0] = end1;
        end[1] = end2;
        dir[0] = (end1 - start1);
        dir[1] = (end2 - start2);
        if (dir[0] != 0)
            dir[0] = dir[0] / abs(dir[0]);
        dir[1] = end[1] - start[1];
        if (dir[1] != 0)
            dir[1] = dir[1] / abs(dir[1]);
        this.tomove = tomove;
    }

    public Move(int[] start, int end1, int end2) {
        this.start = Arrays.copyOf(start, 2);
        end[0] = end1;
        end[1] = end2;
        dir[0] = end[0] - start[0];
        if (dir[0] != 0)
            dir[0] = dir[0] / abs(dir[0]);
        dir[1] = end[1] - start[1];
        if (dir[1] != 0)
            dir[1] = dir[1] / abs(dir[1]);
    }

    public Move(int[] start, int[] direction) {
        this.start = start;
        end[0] = start[0] + direction[0];
        end[1] = start[1] + direction[1];
        dir = direction;
        if (dir[0] != 0)
            dir[0] = dir[0] / abs(dir[0]);
        dir[1] = end[1] - start[1];
        if (dir[1] != 0)
            dir[1] = dir[1] / abs(dir[1]);
    }

    public static Move MoveEndStart(int[] start, int[] end, Piece toMove) {
        return new Move(start[0], start[1], end[0], end[1], toMove);
    }

    public int[] getEnd() {
        return end;
    }

    public int[] getStart() {
        return start;
    }

    @Override
    public boolean equals(Object o) {
        Move other = (Move) o;
        return end[0] == other.end[0] && end[1] == other.end[1] &&
                start[1] == other.start[1] && start[0] == other.start[0];
    }

    public int[] getDir() {
        return dir;
    }

    public Move reverse() {
        return Move.MoveEndStart(end, start, tomove);
    }
}
