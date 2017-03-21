
/* Find the optimal path through the given maze!
 */
public class RunPathSearch {

    public static void main(String[] args) {
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', '#', '#', '#'},
                {'#', ' ', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#'}
        };

        PathSearch searcher = new PathSearch(maze, 1, 6);
        PathState initialState = new PathState(6, 1, 0, maze);

        //print the entire solution path
        //System.out.println(searcher.runSearch(initialState, "breadthFirst"));

        System.out.println(searcher.runSearchE(initialState, "aStar"));
        System.out.println(searcher.runSearchE(initialState, "breadthFirst"));
    }

}