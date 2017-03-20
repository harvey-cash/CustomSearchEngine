
public class TestSearch extends Search {

    public TestSearch (int myTarget, int myBounds) {
        target = myTarget;
        bounds = myBounds;
    }

    private int target;
    public int getTarget() { return target; }
    private int bounds;
    public int getBounds() { return bounds; }

}