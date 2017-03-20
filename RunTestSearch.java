

/* Simply for testing that the Custom Search Engine is written correctly
 * Searches for the optimal path to move along a line to a target position.
 */
public class RunTestSearch {

    public static void main(String[] args) {

        TestSearch searcher = new TestSearch(10, 10);
        TestState initialState = new TestState(0, 0);

        //print the entire solution path
        System.out.println(searcher.runSearch(initialState, "breadthFirst"));
        System.out.println(searcher.runSearch(initialState, "aStar"));

        //print just the efficiency
        System.out.println("Breadth-First: " + searcher.searchEfficiency(initialState, "breadthFirst"));
        System.out.println("A*: " + searcher.searchEfficiency(initialState, "aStar"));
    }

}