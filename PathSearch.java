
public class PathSearch extends Search {

    /* ~~~~~ CONSTRUCTOR ~~~~~ */

    public PathSearch (char[][] myMaze, int myTargetX, int myTargetY) {
        maze = myMaze;
        targetX = myTargetX;
        targetY = myTargetY;
    }

    private char[][] maze;
    public char[][] getMaze() { return maze; }
    private int targetX;
    public int getTargetX() { return targetX; }
    private int targetY;
    public int getTargetY() { return targetY; }


    /* ~~~~~  ~~~~~ */

}