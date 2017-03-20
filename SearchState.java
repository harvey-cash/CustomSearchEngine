import java.util.*;

/* Abstract SearchState represents any one possible state / situation of the matter at hand.
 * To use, the following variables, accessors, and methods must be implemented.
 */

public abstract class SearchState {

    /* ~~~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~~~ */

    protected int localCost;
    public int getLocalCost() { return localCost; }
    public void setLocalCost(int myLocalCost) { localCost = myLocalCost; }

    protected int estRemainingCost;
    public int getEstRemainingCost() { return estRemainingCost; }
    public void setEstRemainingCost(int myEstRemainingCost) { estRemainingCost = myEstRemainingCost; }


    /* ~~~~~~~~~~~~~ METHODS THAT MUST BE IMPLEMENTED ~~~~~~~~~~~~~ */

    abstract boolean reachedGoal(Search searcher);
    abstract boolean sameState(SearchState compareState);
    abstract ArrayList<SearchState> getSuccessors(Search searcher);



}