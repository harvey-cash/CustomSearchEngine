import java.util.*;

/* Abstract Search is the controller of the whole search engine.
 * The user implementation of this need only contain the abstract getTarget()
 * and any other widely-referenced variables / constants useful to the matter at hand.
 *
 * Call runSearch to find the optimal solution path and efficiency,
 * or searchEfficiency to find just the efficiency of your solution.
 */

public abstract class Search {

    /* ~~~~~~~~~~~~~ SEARCH STRATEGIES ~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~ Add new strats here ~~~~~~~~~~~~~ */

    public enum StrategyEnum { A_STAR, BREADTH_FIRST };
    public StrategyEnum getStrategy (String strategyName) {
        switch (strategyName) {
            case "AStar":
            case "aStar":
                return StrategyEnum.A_STAR;

            case "Breadth-First":
            case "breadthFirst":
                return StrategyEnum.BREADTH_FIRST;

            default:
                return StrategyEnum.A_STAR;
        }
    }

    private SearchNode selectNode(String strategy) {
        switch (getStrategy(strategy)) {
            case A_STAR:
                return aStar();
            case BREADTH_FIRST:
                return breadthFirst();

            default:
                return aStar();
        }
    }

    /* ~~~~~~~~~~~~~ Provide strat method here, return SearchNode ~~~~~~~~~~~~~ */

    /* Choose the node on openNodes with the minimum cost remaining to the target!
     */
    private SearchNode aStar() {
        Iterator i = openNodes.iterator();
        SearchNode minCostNode = (SearchNode)i.next();

        //I'm not sure wtf these semi-colons are for
        for(;i.hasNext();) {
            SearchNode nextNode = (SearchNode)i.next();
            if(nextNode.getEstTotalCost() < minCostNode.getEstTotalCost()) { minCostNode = nextNode; }
        }

        openNodes.remove(minCostNode);
        return minCostNode;
    }

    /* Choose the node on the top of openNodes
     */
    private SearchNode breadthFirst() {
        SearchNode nextNode = (SearchNode)openNodes.get(0);
        openNodes.remove(0);

        return nextNode;
    }


    /* ~~~~~~~~~~~~~ SEARCH VARIABLES ~~~~~~~~~~~~~ */

    protected SearchNode initialNode;
    protected SearchNode currentNode;
    protected SearchNode previousNode; //node found on 'open' with same state as new one?

    protected ArrayList<SearchNode> openNodes;
    protected ArrayList<SearchNode> closedNodes;
    protected ArrayList<SearchNode> successorNodes;


    /* ~~~~~~~~~~~~~ SEARCH METHODS ~~~~~~~~~~~~~ */

    /* Return the optimal solution path for a given problem.
     */
    public String runSearch (SearchState initialState, String strategy) {

        SearchNode initialNode = new SearchNode(initialState, 0, 0);
        initialNode.setGlobalCost(0);

        System.out.println("Starting Custom " + strategy + " Search:");

        openNodes = new ArrayList<SearchNode>();
        openNodes.add(initialNode);
        closedNodes = new ArrayList<SearchNode>();

        int iterations = 1;

        while (!openNodes.isEmpty()) {
            System.out.println("\n Iteration #" + iterations);

            currentNode = selectNode(strategy);
            System.out.println("\n + Current node: " + currentNode.toString());

            if(currentNode.reachedGoal(this)) { return solutionPath(); }
            else {
                expand();
                closedNodes.add(currentNode);
                iterations++;
            }
        }

        return "Search Fails";
    }

    /* Return only the efficiency of a given solution search.
     */
    public float searchEfficiency (SearchState initialState, String strategy) {

        SearchNode initialNode = new SearchNode(initialState, 0, 0);
        initialNode.setGlobalCost(0);

        openNodes = new ArrayList<SearchNode>();
        openNodes.add(initialNode);
        closedNodes = new ArrayList<SearchNode>();

        int iterations = 1;

        while (!openNodes.isEmpty()) {
            currentNode = selectNode(strategy);
            if(currentNode.reachedGoal(this)) { return solutionEfficiency(); }
            else {
                expand();
                closedNodes.add(currentNode);
                iterations++;
            }
        }

        return 0;
    }


    /* ~~~~~~~~~~~~~ SEARCH INNER WORKINGS ~~~~~~~~~~~~~ */

    private void expand() {
        successorNodes = currentNode.getSuccessors(this);

        for (SearchNode node : successorNodes) {
            node.setGlobalCost(currentNode.getGlobalCost() + node.getLocalCost());
            node.setParent(currentNode);
            node.setEstTotalCost(node.getGlobalCost() + node.getEstRemainingCost());
        }

        successorNodes = vetSuccessors(successorNodes);

        for (SearchNode node : successorNodes) {
            openNodes.add(node);
        }
    }

    // modify things when a better route that has already lead to
    // this node is discovered..?
    private ArrayList<SearchNode> vetSuccessors(ArrayList<SearchNode> successors) {
        ArrayList<SearchNode> vettedList = new ArrayList<SearchNode>();

        for (SearchNode node : successorNodes) {
            if(onOpenList(node)) {
                if (node.getGlobalCost() <= previousNode.getGlobalCost()) {
                    updateNodeState(previousNode, node);
                }
            }
            else {
                if(onClosedList(node)) {
                    if (node.getGlobalCost() <= previousNode.getGlobalCost()) {
                        updateNodeState(previousNode, node);

                        openNodes.add(previousNode);
                        closedNodes.remove(previousNode);
                    }
                }
                else {
                    vettedList.add(node);
                }
            }
        }

        return vettedList;
    }

    private void updateNodeState(SearchNode nodeToUpdate, SearchNode node) {
        nodeToUpdate.setParent(node.getParent());
        nodeToUpdate.setGlobalCost(node.getGlobalCost());
        nodeToUpdate.setLocalCost(node.getLocalCost());
        nodeToUpdate.setEstTotalCost(node.getEstTotalCost());
    }

    //Given Node is present on the open list already
    private boolean onOpenList(SearchNode node) {
        boolean result = false;

        Iterator iterate = openNodes.iterator();
        while(iterate.hasNext() && !result) {
            SearchNode openNode = (SearchNode)iterate.next();

            if(node.sameState(openNode)) {
                result = true;
                previousNode = openNode;
            }
        }

        return result;
    }

    //Given Node is present on the closed list already
    private boolean onClosedList(SearchNode node) {
        boolean result = false;

        Iterator iterate = closedNodes.iterator();
        while(iterate.hasNext() && !result) {
            SearchNode closedNode = (SearchNode)iterate.next();

            if(node.sameState(closedNode)) {
                result = true;
                previousNode = closedNode;
            }
        }

        return result;
    }


    /* ~~~~~~~~~~~~~~~~ RETURNED SOLUTIONS ~~~~~~~~~~~~~~~~ */
    /* ~~~ Efficiency is useful iterations / wasted iterations ~~~ */

    private String solutionPath() {
        SearchNode node = currentNode;
        StringBuffer stringBuffer = new StringBuffer(node.toString());

        int iterate = 1;
        while (node.getParent() != null) {
            node = node.getParent();
            stringBuffer.insert(0, "\n");
            stringBuffer.insert(0, node.toString());

            iterate++;
        }

        System.out.println("\n ~~~~~~~~ SEARCH SUCCEEDS ~~~~~~~~ \n");
        System.out.println("Efficiency: " + (float)iterate/(closedNodes.size() + 1));
        System.out.println("Solution Path: \n");

        stringBuffer.insert(stringBuffer.length(), "\n ~~~~~~~~ SEARCH CONCLUDED ~~~~~~~~ \n");

        return stringBuffer.toString();
    }

    private float solutionEfficiency() {
        SearchNode node = currentNode;

        int iterate = 1;
        while (node.getParent() != null) {
            node = node.getParent();
            iterate++;
        }

        return (float)iterate/(closedNodes.size() + 1);
    }

}