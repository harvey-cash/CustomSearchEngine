
public class TestSearch : Search {

    public TestSearch(int myTarget, int myBounds) {
        target = myTarget;
        bounds = myBounds;
    }

    private int target;
    public int GetTarget() { return target; }
    private int bounds;
    public int GetBounds() { return bounds; }

}