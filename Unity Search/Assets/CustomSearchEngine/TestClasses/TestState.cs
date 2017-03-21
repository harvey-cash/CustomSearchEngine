
using System.Collections.Generic;
using UnityEngine;

public class TestState : SearchState {

    /* ~~~~ CONSTRUCTOR ~~~~ */

    public TestState(int myState, int myEstRemainingCost) {
        state = myState;
        estRemainingCost = myEstRemainingCost;
    }

    private int state;
    public int GetState() { return state; }

    private new int localCost = 1;
    public new int GetLocalCost() { return localCost; }
    private new int estRemainingCost;
    public new int GetEstRemainingCost() { return estRemainingCost; }

    /* ~~~~ IMPLEMENTATION ~~~~ */

    public override bool ReachedGoal(Search searcher) {
        return (state == ((TestSearch)searcher).GetTarget());
    }
    public override bool SameState(SearchState compareState) {
        return (state == ((TestState)compareState).GetState());
    }

    public override List<SearchState> GetSuccessors(Search searcher) {
        List<SearchState> successors = new List<SearchState>();

        if (state - 1 >= -((TestSearch)searcher).GetTarget()) {
            TestState decrement = new TestState(state - 1, GetRemainingCost(searcher, state - 1));
            successors.Add((SearchState)decrement);
        }

        if (state + 1 <= ((TestSearch)searcher).GetTarget()) {
            TestState increment = new TestState(state + 1, GetRemainingCost(searcher, state + 1));
            successors.Add((SearchState)increment);
        }

        return successors;
    }

    public override string ToString() {
        return "[" + state + "]";
    }

    /* ~~~~ ESTIMATE REMAINING COST ~~~~ */

    private int GetRemainingCost(Search searcher, int currentState) {
        return ((TestSearch)searcher).GetTarget() - currentState;
    }
}
