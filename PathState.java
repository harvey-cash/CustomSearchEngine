
import java.util.*;

/* Represents the state of the maze
 * Can move one step in any direction not blocked by a wall
 */
public class PathState extends SearchState {

    /* ~~~~~ CONSTRUCTOR ~~~~~ */

    public PathState (int myX, int myY, int myEstRemainingCost) {
        xPos = myX;
        yPos = myY;
        estRemainingCost = myEstRemainingCost;
    }
    private int xPos;
    public int getXPos() { return xPos; }
    private int yPos;
    public int getYPos() { return yPos; }

    private int localCost = 1;
    public int getLocalCost() { return localCost; }
    private int estRemainingCost;
    public int getEstRemainingCost() { return estRemainingCost; }


    /* ~~~~~~ IMPLEMENTATION ~~~~~ */


}