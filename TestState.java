
import java.util.*;

public class TestState extends SearchState {

    /* ~~~~ CONSTRUCTOR ~~~~ */

    public TestState(int myState, int myEstRemainingCost) {
        state = myState;
        estRemainingCost = myEstRemainingCost;
    }

    private int state;
    public int getState() { return state; }

    private int localCost = 1;
    public int getLocalCost() { return localCost; }
    private int estRemainingCost;
    public int getEstRemainingCost() { return estRemainingCost; }

    /* ~~~~ IMPLEMENTATION ~~~~ */

    public boolean reachedGoal(Search searcher) {
        return (state == ((TestSearch)searcher).getTarget());
    }
    public boolean sameState(SearchState compareState) {
        return (state == ((TestState)compareState).getState());
    }

    public ArrayList<SearchState> getSuccessors(Search searcher) {
        ArrayList<SearchState> successors = new ArrayList<SearchState>();

        if(state - 1 >= -((TestSearch)searcher).getTarget()) {
            TestState decrement = new TestState(state - 1, getRemainingCost(searcher, state - 1));
            successors.add((SearchState)decrement);
        }

        if(state + 1 <= ((TestSearch)searcher).getTarget()) {
            TestState increment = new TestState(state + 1, getRemainingCost(searcher, state + 1));
            successors.add((SearchState)increment);
        }

        return successors;
    }

    public String toString() {
        return "[" + state + "]";
    }

    /* ~~~~ ESTIMATE REMAINING COST ~~~~ */

    private int getRemainingCost(Search searcher, int currentState) {
        return ((TestSearch)searcher).getTarget() - currentState;
    }

}