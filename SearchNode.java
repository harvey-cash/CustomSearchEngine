import java.util.*;

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
    public SearchState getState() { return state; }
    public void setState(SearchState myState) { state = myState; }

    private int localCost;
    public int getLocalCost() { return localCost; }
    public void setLocalCost(int myLocalCost) { localCost = myLocalCost; }

    private int estRemainingCost;
    public int getEstRemainingCost() { return estRemainingCost; }
    public void setEstRemainingCost(int myEstRemainingCost) { estRemainingCost = myEstRemainingCost; }


    /* ~~~~~~~~~~~~~ OTHER INSTANCE VARIABLES ~~~~~~~~~~~~~ */

    private int globalCost;
    public int getGlobalCost() { return globalCost; }
    public void setGlobalCost(int myGlobalCost) { globalCost = myGlobalCost; }

    private int estTotalCost;
    public int getEstTotalCost() { return estTotalCost; }
    public void setEstTotalCost(int myEstTotalCost) { estTotalCost = myEstTotalCost; }

    private SearchNode parent;
    public SearchNode getParent() { return parent; }
    public void setParent(SearchNode myParent) { parent = myParent; }


    /* ~~~~~~~~~~~~~ METHODS THAT MUST BE IMPLEMENTED IN SEARCH_STATE ~~~~~~~~~~~~~ */

    public boolean reachedGoal(Search searcher) { return state.reachedGoal(searcher); }
    public boolean sameState(SearchNode compareNode) { return state.sameState(compareNode.getState()); }

    //Get all the successors to my current state, and return a list of new nodes for each one.
    public ArrayList getSuccessors(Search searcher) {
        ArrayList<SearchState> stateList = state.getSuccessors(searcher);
        ArrayList<SearchNode> nodeList = new ArrayList<SearchNode>();

        for (SearchState successor : stateList) {
            SearchNode node = new SearchNode(successor, successor.getLocalCost(), successor.getEstRemainingCost());
            node.setParent(this);
            nodeList.add(node);
        }

        return nodeList;
    }

    public String toString() {
        String nodeString = "";

        String parentState;
        if (parent == null) { parentState = "null"; }
        else { parentState = parent.getState().toString(); }

        nodeString += "State: " + state.toString() + "\n";
        nodeString += "Parent: " + parentState + "\n";
        nodeString += "localCost: " + localCost + ", estRemainingCost: " + estRemainingCost + "\n";

        return nodeString;
    }

}