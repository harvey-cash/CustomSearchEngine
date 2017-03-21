
import java.util.*;

/* Represents the state of the maze
 * Can move one step in any direction not blocked by a wall
 */
public class PathState extends SearchState {

    /* ~~~~~ CONSTRUCTOR ~~~~~ */

    public PathState (int myX, int myY, int myEstRemainingCost, char[][] myMazeState) {
        xPos = myX;
        yPos = myY;
        estRemainingCost = myEstRemainingCost;
        mazeState = myMazeState;
    }
    private int xPos;
    public int getXPos() { return xPos; }
    private int yPos;
    public int getYPos() { return yPos; }

    private int localCost = 1;
    public int getLocalCost() { return localCost; }
    private int estRemainingCost;
    public int getEstRemainingCost() { return estRemainingCost; }

    private char[][] mazeState;
    public char[][] getMazeState() { return mazeState; }


    /* ~~~~~~ IMPLEMENTATION ~~~~~ */

    //are we at the target position?
    public boolean reachedGoal(Search searcher) {
        boolean sameX = (xPos == ((PathSearch)searcher).getTargetX());
        boolean sameY = (yPos == ((PathSearch)searcher).getTargetY());

        if(sameX && sameY) { return true; }
        else { return false; }
    }

    //are these two states the same?
    public boolean sameState(SearchState compareState) {
        boolean sameX = (xPos == ((PathState)compareState).getXPos());
        boolean sameY = (yPos == ((PathState)compareState).getYPos());

        if(sameX && sameY) { return true; }
        else { return false; }
    }

    //return all the possible states after the next move
    public ArrayList<SearchState> getSuccessors(Search searcher) {
        ArrayList<SearchState> successors = new ArrayList<SearchState>();
        char[][] maze = ((PathSearch)searcher).getMaze();

        for(int k = -1; k < 2; k += 2) {
            if(xPos + k >= 0 && xPos + k < maze.length) {
                if(maze[yPos][xPos + k] == ' ' || maze[yPos + k][xPos] == '@') {
                    char[][] nextMaze = nextMazeState(searcher, xPos + k, yPos);
                    successors.add((SearchState)new PathState(xPos + k, yPos, getManhattanCost(searcher), nextMaze));
                }
            }
            if(yPos + k >= 0 && yPos + k < maze.length) {
                if(maze[yPos + k][xPos] == ' ' || maze[yPos + k][xPos] == '@') {
                    char[][] nextMaze = nextMazeState(searcher, xPos, yPos + k);
                    successors.add((SearchState)new PathState(xPos, yPos + k, getManhattanCost(searcher), nextMaze));
                }
            }
        }

        return successors;
    }

    //describe the map
    public String toString() {
        String buffer = "\n";

        for(int j = 0; j < mazeState.length; j++) {
            for(int i = 0; i < mazeState.length; i++) {
                buffer += mazeState[j][i];
            }
            buffer += "\n";
        }

        return buffer;
    }

    private char[][] nextMazeState(Search searcher, int nextX, int nextY) {
        char[][] oldMaze = ((PathSearch)searcher).getMaze();
        int length = ((PathSearch)searcher).getMaze().length;

        char[][] newMaze = new char[length][length];
        for(int j = 0; j < length; j++) {
            for(int i = 0; i < length; i++) {
                newMaze[j][i] = oldMaze[j][i];
            }
        }
        newMaze[nextY][nextX] = '@';

        return newMaze;
    }


    /* ~~~~~~ ESTIMATING COST ~~~~~ */


    private int getManhattanCost(Search searcher) {
        int deltaX = ((PathSearch)searcher).getTargetX() - xPos;
        int deltaY = ((PathSearch)searcher).getTargetY() - yPos;

        return Math.abs(deltaX) + Math.abs(deltaY);
    }


}