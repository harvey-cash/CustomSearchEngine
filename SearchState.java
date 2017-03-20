import java.util.*;

/* Abstract SearchState represents any one possible state / situation of the matter at hand.
 * To use, the following variables, accessors, and methods must be implemented.
 */

public abstract class SearchState {

    /* ~~~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~~~ */
    /* ~~~~~~ (Include accessors of these!) ~~~~~~ */

    protected int localCost;
    public int getLocalCost() { return localCost; }
    public void setLocalCost(int myLocalCost) { localCost = myLocalCost; }

    protected int estRemainingCost;
    public int getEstRemainingCost() { return estRemainingCost; }
    public void setEstRemainingCost(int myEstRemainingCost) { estRemainingCost = myEstRemainingCost; }


    /* ~~~~~~~~~~~~~ METHODS THAT MUST BE IMPLEMENTED ~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~ Remember to also include a .toString() ! ~~~~~~~~~~ */

    abstract boolean reachedGoal(Search searcher); //Have you reached the goal?
    abstract boolean sameState(SearchState compareState); //Is your state the same as this given one?

    //Return an ArrayList of all the possible successor states
    abstract ArrayList<SearchState> getSuccessors(Search searcher);

}