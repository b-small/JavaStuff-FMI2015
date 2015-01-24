package graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author bas
 */
public class GraphTest {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 4);
        g.addEdge(0, 3, 8);
        g.addEdge(1, 4, 12);
        g.addEdge(2, 3, 2);
        g.addEdge(2, 4, 13);
        g.addEdge(3, 4, 5);
        g.addEdge(5, 5, 0);

        System.out.println(g.toString());
        System.out.println(g.getEdge(0, 1));

        ArrayList<Integer> bfs = g.breadthFirstTraverse(0);
        System.out.println(bfs.toString());

        ArrayList<Integer> dfs = g.depthFirstTraverse(2);
        System.out.println(dfs.toString());

        System.out.println(Arrays.toString(g.distancesFrom(3)));
    }
}
