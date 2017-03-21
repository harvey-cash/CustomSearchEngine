using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/* Simply for testing that the Custom Search Engine is written correctly
 * Searches for the optimal path to move along a line to a target position.
 */

public class RunTestSearch : MonoBehaviour {

    void Start() {

        //Test();
        Run();
    }

    void Test() {
        TestSearch searcher = new TestSearch(10, 10);
        TestState initialState = new TestState(0, 0);

        List<SearchState> successors = initialState.GetSuccessors(searcher);
        for (int i = 0; i < successors.Count; i++) {
            Debug.Log(successors[0].ToString());
        }
    }

	void Run () {
        TestSearch searcher = new TestSearch(10, 10);
        TestState initialState = new TestState(0, 0);

        //print the entire solution path
        //Debug.Log(searcher.RunSearch(initialState, "breadthFirst"));
        Debug.Log(searcher.searchEfficiency(initialState, "aStar"));

        //print just the efficiency
        //Debug.Log("BreadthFirst: " + searcher.SearchEfficiency(initialState, "breadthFirst"));
        //Debug.Log("A*: " + searcher.SearchEfficiency(initialState, "aStar"));
    }
}
