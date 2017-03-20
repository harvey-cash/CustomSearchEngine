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

    private SearchNode aStar() {
        return null;
    }

    private SearchNode breadthFirst() {
        return null;
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


    /* ~~~~~~~~~~~~~ SEARCH STRATEGY AND WORKINGS ~~~~~~~~~~~~~ */

    private void expand() {

    }

    private String solutionPath() {
        return null;
    }

    private float solutionEfficiency() {
        return 0;
    }


}