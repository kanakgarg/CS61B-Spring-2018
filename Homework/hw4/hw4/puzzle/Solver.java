package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.List;
import java.util.ArrayList;

public class Solver {

    private MinPQ<SearchNode> distances = new MinPQ<>();
    private List<WorldState> bestVals;
    private SearchNode best;

    public Solver(WorldState initial) {
        bestVals = new ArrayList<>();

        best = new SearchNode(initial, 0, null);
        while (!best.ws.isGoal()) {
            for (WorldState n : best.ws.neighbors()) {
                if (best.prev == null || !n.equals(best.prev.ws)) {
                    distances.insert(new SearchNode(n, best.nMoves + 1, best));
                }
            }

            best = distances.delMin();
        }
    }



    public int moves() {
        return best.nMoves;
    }

    public Iterable<WorldState> solution() {
        SearchNode val = best;
        while (val != null) {
            bestVals.add(0, val.ws);
            val = val.prev;
        }
        return bestVals;
    }


    private class SearchNode implements Comparable<SearchNode> {
        WorldState ws;
        int nMoves;
        SearchNode prev;

        SearchNode(WorldState ws, int nMoves, SearchNode prev) {
            this.ws = ws;
            this.nMoves = nMoves;
            this.prev = prev;
        }

        public int compareTo(SearchNode sn) {
            return (this.nMoves + this.ws.estimatedDistanceToGoal())
                    - (sn.nMoves + sn.ws.estimatedDistanceToGoal());
        }

    }
}
