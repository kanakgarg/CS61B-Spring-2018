
public class SearchNode implements Comparable<SearchNode> {
    Long id;
    double distance;
    SearchNode prev;
    GraphDB g;
    Long end;

    SearchNode(Long id, double distance, SearchNode prev, GraphDB g, Long end) {
        this.id = id;
        this.distance = distance;
        this.prev = prev;
        this.g = g;
        this.end = end;
    }

    public int compareTo(SearchNode sn) {
        double thisDistance  = this.distance + this.g.distance(this.id, this.end);
        double snDistance = sn.distance + sn.g.distance(sn.id, sn.end);

        if (thisDistance == snDistance) {
            return 0;
        } else if (thisDistance > snDistance) {
            return 1;
        } else {
            return -1;
        }
    }


}
