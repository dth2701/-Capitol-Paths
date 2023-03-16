// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo7@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String,Integer> graph;

    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E"); //The furthest node from the source vertex.
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to D.
     */
    @Test
    public void testPathAtoD() {
        assertTrue(graph.shortestPath("A", "D").toString().equals(
                "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F").toString().equals(
                "[A, C, F]"
        ));
    }
    /**
     * Step 9: Checks the path cost from the vertex A to E.
     */
    @Test
    public void testDijkstraPathCostAtoE() {
        assertTrue(graph.getPathCost("A", "E") == 6);
    }
    /**
     * Step 10: Checks the sequence of nodes along the path from A to E is correct.
     */
    @Test
    public void testDijkstraPathAtoE() {
        assertTrue(graph.shortestPath("A", "E").toString().equals(
                "[A, C, B, E]"
        ));
    }
    /**
     * Step 11: Checks the path cost from the vertex B to F.
     */
    @Test
    public void testDijkstraPathCostBtoF(){
        assertTrue(graph.getPathCost("B", "F") == 3);
    }
    /**
     * Step 12: Checks if the sequence of nodes along the path from B to F is correct.
     */
    @Test
    public void testDijkstraPathBtoF() {
        assertTrue(graph.shortestPath("B", "F").toString().equals(
                "[B, C, F]"
        ));
    }

    /**
     * Step 13: Checks the ordered sequence of data within vertices
     * and distance/total weight cost from the vertex D to C.
     */
    @Test
    public void testPathAndPathCostDtoC() {
        assertTrue(graph.getPathCost("D", "C") == 9);
        assertTrue(graph.shortestPath("D", "C").toString().contains("E, A,"));
        assertTrue(graph.shortestPath("D", "C").toString().equals("[D, E, A, C]"));
    }
}