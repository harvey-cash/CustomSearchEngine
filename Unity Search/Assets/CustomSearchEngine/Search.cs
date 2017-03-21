
/* Abstract Search is the controller of the whole search engine.
 * The user implementation of this need only contain the abstract GetTarget()
 * and any other widely-referenced variables / constants useful to the matter at hand.
 *
 * Call RunSearch to find the optimal solution path and efficiency,
 * or SearchEfficiency to find just the efficiency of your solution.
 */

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using UnityEngine;

public abstract class Search {

    /* ~~~~~~~~~~~~~ SEARCH STRATEGIES ~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~ Add new strats here ~~~~~~~~~~~~~ */

    public enum StrategyEnum { A_STAR, BREADTH_FIRST };
    public StrategyEnum GetStrategy(string strategyName) {
        switch (strategyName) {
            case "AStar":
            case "aStar":
                return StrategyEnum.A_STAR;

            case "BreadthFirst":
            case "breadthFirst":
                return StrategyEnum.BREADTH_FIRST;

            default:
                return StrategyEnum.A_STAR;
        }
    }

    private SearchNode SelectNode(string strategy) {
        switch (GetStrategy(strategy)) {
            case StrategyEnum.A_STAR:
                return AStar();
            case StrategyEnum.BREADTH_FIRST:
                return BreadthFirst();

            default:
                return AStar();
        }
    }

    /* ~~~~~~~~~~~~~ Provide strat method here, return SearchNode ~~~~~~~~~~~~~ */

    /* Choose the node on openNodes with the minimum cost remaining to the target!
     */
    private SearchNode AStar() {
        SearchNode minCostNode = openNodes[0];
        
        for (int i = 1; i < openNodes.Count; i++) {
            SearchNode nextNode = openNodes[i];
            if (nextNode.GetEstTotalCost() < minCostNode.GetEstTotalCost()) { minCostNode = nextNode; }
        }

        openNodes.Remove(minCostNode);
        return minCostNode;
    }

    /* Choose the node on the top of openNodes
     */
    private SearchNode BreadthFirst() {
        SearchNode nextNode = openNodes[0];
        openNodes.Remove(openNodes[0]);

        return nextNode;
    }


    /* ~~~~~~~~~~~~~ SEARCH VARIABLES ~~~~~~~~~~~~~ */

    protected SearchNode initialNode;
    protected SearchNode currentNode;
    protected SearchNode previousNode;

    protected List<SearchNode> openNodes;
    protected List<SearchNode> closedNodes;
    protected List<SearchNode> successorNodes;


    /* ~~~~~~~~~~~~~ SEARCH METHODS ~~~~~~~~~~~~~ */

    /* Return the optimal solution path for a given problem.
     */
    public string RunSearch(SearchState initialState, string strategy) {

        SearchNode initialNode = new SearchNode(initialState, 0, 0);
        initialNode.SetGlobalCost(0);

        Debug.Log("Starting Custom " + strategy + " Search:");

        openNodes = new List<SearchNode>();
        openNodes.Add(initialNode);
        closedNodes = new List<SearchNode>();

        int iterations = 1;

        while (openNodes.Any()) {
            Debug.Log("\n Iteration #" + iterations);

            currentNode = SelectNode(strategy);
            Debug.Log("\n + Current node: " + currentNode.ToString());

            if (currentNode.ReachedGoal(this)) { return SolutionPath(); } else {
                Expand();
                closedNodes.Add(currentNode);
                iterations++;
            }
        }

        return "Search Fails";
    }

    /* Return only the efficiency of a given solution search.
     */
    public float searchEfficiency(SearchState initialState, String strategy) {

        SearchNode initialNode = new SearchNode(initialState, 0, 0);
        initialNode.SetGlobalCost(0);

        openNodes = new List<SearchNode>();
        openNodes.Add(initialNode);
        closedNodes = new List<SearchNode>();

        int iterations = 1;

        while (openNodes.Any() && iterations < 4) {
            currentNode = SelectNode(strategy);
            if (currentNode.ReachedGoal(this)) { return SolutionEfficiency(); } else {
                Expand();
                closedNodes.Add(currentNode);
                iterations++;
            }
        }

        return 0;
    }


    /* ~~~~~~~~~~~~~ SEARCH INNER WORKINGS ~~~~~~~~~~~~~ */

    private void Expand() {
        successorNodes = currentNode.GetSuccessors(this);

        foreach (SearchNode node in successorNodes) {
            node.SetGlobalCost(currentNode.GetGlobalCost() + node.GetLocalCost());
            node.SetParent(currentNode);
            node.SetEstTotalCost(node.GetGlobalCost() + node.GetEstRemainingCost());
        }

        successorNodes = VetSuccessors(successorNodes);

        foreach (SearchNode node in successorNodes) {
            openNodes.Add(node);
        }
    }

    // modify things when a better route that has already lead to
    // this node is discovered..?
    private List<SearchNode> VetSuccessors(List<SearchNode> successors) {
        List<SearchNode> vettedList = new List<SearchNode>();

        foreach (SearchNode node in successorNodes) {
            if (InOpenList(node)) {
                if (node.GetGlobalCost() <= previousNode.GetGlobalCost()) {
                    UpdateNodeState(previousNode, node);
                }
            } else {
                if (InClosedList(node)) {
                    if (node.GetGlobalCost() <= previousNode.GetGlobalCost()) {
                        UpdateNodeState(previousNode, node);

                        openNodes.Add(previousNode);
                        closedNodes.Remove(previousNode);
                    }
                } else {
                    vettedList.Add(node);
                }
            }
        }

        return vettedList;
    }

    private void UpdateNodeState(SearchNode nodeToUpdate, SearchNode node) {
        nodeToUpdate.SetParent(node.GetParent());
        nodeToUpdate.SetGlobalCost(node.GetGlobalCost());
        nodeToUpdate.SetLocalCost(node.GetLocalCost());
        nodeToUpdate.SetEstTotalCost(node.GetEstTotalCost());
    }

    //Given Node is present on the open list already
    private bool InOpenList(SearchNode node) {
        bool result = false;

        int iterate = 1;
        while (iterate < openNodes.Count && !result) {
            SearchNode openNode = openNodes[iterate];

            if (node.SameState(openNode)) {
                result = true;
                previousNode = openNode;
            }
        }

        return result;
    }

    //Given Node is present on the closed list already
    private bool InClosedList(SearchNode node) {
        bool result = false;

        int iterate = 1;
        while (iterate < closedNodes.Count && !result) {
            SearchNode closedNode = closedNodes[iterate];

            if (node.SameState(closedNode)) {
                result = true;
                previousNode = closedNode;
            }
        }

        return result;
    }


    /* ~~~~~~~~~~~~~~~~ RETURNED SOLUTIONS ~~~~~~~~~~~~~~~~ */
    /* ~~~ Efficiency is useful iterations / wasted iterations ~~~ */

    private string SolutionPath() {
        SearchNode node = currentNode;
        StringBuilder stringBuffer = new StringBuilder(node.ToString());

        int iterate = 1;
        while (node.GetParent() != null) {
            node = node.GetParent();
            stringBuffer.Insert(0, "\n");
            stringBuffer.Insert(0, node.ToString());

            iterate++;
        }

        Debug.Log("\n ~~~~~~~~ SEARCH SUCCEEDS ~~~~~~~~ \n");
        Debug.Log("Efficiency: " + (float)iterate / (closedNodes.Count + 1));
        Debug.Log("Solution Path: \n");

        stringBuffer.Insert(stringBuffer.Length, "\n ~~~~~~~~ SEARCH CONCLUDED ~~~~~~~~ \n");

        return stringBuffer.ToString();
    }

    private float SolutionEfficiency() {
        SearchNode node = currentNode;

        int iterate = 1;
        while (node.GetParent() != null) {
            node = node.GetParent();
            iterate++;
        }

        return (float)iterate / (closedNodes.Count + 1);
    }
}
