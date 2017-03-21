
public class PathSearch extends Search {

    /* ~~~~~ CONSTRUCTOR ~~~~~ */

    public PathSearch (int[][] myMaze, int myTargetX, int myTargetY) {
        maze = myMaze;
        targetX = myTargetX;
        targetY = myTargetY;
    }

    private int[][] maze;
    public int[][] getMaze() { return maze; }
    private int targetX;
    public int getTargetX() { return targetX; }
    private int targetY;
    public int getTargetY() { return targetY; }


    /* ~~~~~  ~~~~~ */

}