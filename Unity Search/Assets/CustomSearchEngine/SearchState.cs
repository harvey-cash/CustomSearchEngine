
using System.Collections.Generic;

/* Abstract SearchState represents any one possible state / situation of the matter at hand.
 * To use, the following variables, accessors, and methods must be implemented.
 */

public abstract class SearchState {

    /* ~~~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~~~ */
    /* ~~~~~~ (Include accessors of these!) ~~~~~~ */

    protected int localCost;
    public int GetLocalCost() { return localCost; }
    public void SetLocalCost(int myLocalCost) { localCost = myLocalCost; }

    protected int estRemainingCost;
    public int GetEstRemainingCost() { return estRemainingCost; }
    public void SetEstRemainingCost(int myEstRemainingCost) { estRemainingCost = myEstRemainingCost; }


    /* ~~~~~~~~~~~~~ METHODS THAT MUST BE IMPLEMENTED ~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~ Remember to include a .ToString() ! ~~~~~~~~~~~~ */

    public abstract bool ReachedGoal(Search searcher); //Have you reached the goal?
    public abstract bool SameState(SearchState compareState); //Is your state the same as this given one?

    //Return an ArrayList of all the possible successor states
    public abstract List<SearchState> GetSuccessors(Search searcher);

    public abstract override string ToString();
}
