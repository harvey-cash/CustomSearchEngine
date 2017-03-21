
using System.Collections;
using System.Collections.Generic;

/* SearchNode is the internal representation of any SearchState.
 * This does not need to be used or implemented by the user.
 */

public class SearchNode {

    /* ~~~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~~~ */

    public SearchNode(SearchState myState, int myLocalCost, int myEstRemainingCost) {
        state = myState;
        localCost = myLocalCost;
        estRemainingCost = myEstRemainingCost;
    }

    private SearchState state;
    public SearchState GetState() { return state; }
    public void SetState(SearchState myState) { state = myState; }

    private int localCost;
    public int GetLocalCost() { return localCost; }
    public void SetLocalCost(int myLocalCost) { localCost = myLocalCost; }

    private int estRemainingCost;
    public int GetEstRemainingCost() { return estRemainingCost; }
    public void SetEstRemainingCost(int myEstRemainingCost) { estRemainingCost = myEstRemainingCost; }


    /* ~~~~~~~~~~~~~ OTHER INSTANCE VARIABLES ~~~~~~~~~~~~~ */

    private int globalCost;
    public int GetGlobalCost() { return globalCost; }
    public void SetGlobalCost(int myGlobalCost) { globalCost = myGlobalCost; }

    private int estTotalCost;
    public int GetEstTotalCost() { return estTotalCost; }
    public void SetEstTotalCost(int myEstTotalCost) { estTotalCost = myEstTotalCost; }

    private SearchNode parent;
    public SearchNode GetParent() { return parent; }
    public void SetParent(SearchNode myParent) { parent = myParent; }


    /* ~~~~~~~~~~~~~ METHODS THAT MUST BE IMPLEMENTED IN SEARCH_STATE ~~~~~~~~~~~~~ */

    //Have you reached the target?
    public bool ReachedGoal(Search searcher) { return state.ReachedGoal(searcher); }
    //Is your state the same as this given one?
    public bool SameState(SearchNode compareNode) { return state.SameState(compareNode.GetState()); }

    //Get all the successors to your current state, and return a list of new nodes for each one.
    public List<SearchNode> GetSuccessors(Search searcher) {
        List<SearchState> stateList = state.GetSuccessors(searcher);
        List<SearchNode> nodeList = new List<SearchNode>();

        foreach (SearchState successor in stateList) {
            SearchNode node = new SearchNode(successor, successor.GetLocalCost(), successor.GetEstRemainingCost());
            node.SetParent(this);
            nodeList.Add(node);
        }

        return nodeList;
    }

    //Describe the current node in string format
    public override string ToString() {
        string nodeString = "";

        string parentState;
        if (parent == null) { parentState = "null"; } else { parentState = parent.GetState().ToString(); }

        nodeString += "State: " + state.ToString() + "\n";
        nodeString += "Parent: " + parentState + "\n";
        nodeString += "localCost: " + localCost + ", estRemainingCost: " + estRemainingCost + "\n";

        return nodeString;
    }

}
