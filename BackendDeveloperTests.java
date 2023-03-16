// --== CS400 File Header Information ==--
// Name: Nattarach Larptaweepornsup
// Email: larptaweepor@wisc.edu
// Team: AD
// TA: YUYE JIANG
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/*
 * this class test the backend functionality
 */
public class BackendDeveloperTests {
    CapitolMapBackend<String, Number> graph;
    CapitolMapBackend<Double, Number> graphDouble;

    /**
     * Instantiate graph.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CapitolMapBackend<String, Number>();
        graphDouble = new CapitolMapBackend<Double, Number>();
        // insert vertices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graphDouble.insertVertex(1.1);
        graphDouble.insertVertex(1.2);
        // insert edges
        graph.insertEdge("A", "B", 6);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("A", "D", 5);
        graph.insertEdge("B", "E", 1);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("C", "B", 3);
        graph.insertEdge("C", "F", 1);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("E", "A", 4);
        graph.insertEdge("F", "A", 1);
        graph.insertEdge("F", "D", 1);
        graphDouble.insertEdge(1.1, 1.2, 5);
        graphDouble.insertEdge(1.2, 1.1, 10);

    }

    /**
     * this method test the correctness of addCapitol method
     */
    @Test
    public void testAddCapitol() {
        // adding new capitol to existing location
        graph.addCapitol("A", "Madison", 3);
        graph.addCapitol("B", "Minnesota", 4);
        graph.addCapitol("Minnesota", "Cheyenne", 5);
        graph.addCapitol("F", "Olympia", 5);
        assertTrue(graph.getPathCost("A", "Madison") == 3);
        assertTrue(graph.getPathCost("B", "Minnesota") == 4);
        assertTrue(graph.getPathCost("Minnesota", "Cheyenne") == 5);
        assertTrue(graph.getPathCost("A", "Minnesota") == 9);
        assertTrue(graph.getPathCost("A", "Olympia") == 8);
    }

    /**
     * this method test the correctness of getAllCapitol method
     */
    @Test
    public void testGetAllCapitol() {
        // adding new capitol to existing location
        graph.addCapitol("A", "Madison", 3);
        graph.addCapitol("B", "Minnesota", 4);
        graph.addCapitol("Minnesota", "Cheyenne", 5);
        graph.addCapitol("F", "Olympia", 5);
        assertTrue(graph.getPathCost("A", "Madison") == 3);
        assertTrue(graph.getPathCost("B", "Minnesota") == 4);
        assertTrue(graph.getPathCost("Minnesota", "Cheyenne") == 5);
        assertTrue(graph.getPathCost("A", "Minnesota") == 9);
        assertTrue(graph.getPathCost("A", "Olympia") == 8);
        List<String> allCapitol = graph.getAllCapitol();
        String res = "";
        for (String capitol : allCapitol) {
            res += capitol;
        }
        assertTrue(res.contains("A"));
        assertTrue(res.contains("B"));
        assertTrue(res.contains("C"));
        assertTrue(res.contains("D"));
        assertTrue(res.contains("E"));
        assertTrue(res.contains("F"));
        assertTrue(res.contains("Madison"));
        assertTrue(res.contains("Minnesota"));
        assertTrue(res.contains("Cheyenne"));
        assertTrue(res.contains("Olympia"));
    }

    /**
     * this method test the correctness of getAllCapitol method that accept the prefix as a filter
     */
    @Test
    public void testGetAllCapitolPrefix() {
        // adding new capitol to existing location
        graph.addCapitol("A", "Madison", 3);
        graph.addCapitol("B", "Minnesota", 4);
        graph.addCapitol("Minnesota", "Cheyenne", 5);
        graph.addCapitol("F", "Olympia", 5);
        assertTrue(graph.getPathCost("A", "Madison") == 3);
        assertTrue(graph.getPathCost("B", "Minnesota") == 4);
        assertTrue(graph.getPathCost("Minnesota", "Cheyenne") == 5);
        assertTrue(graph.getPathCost("A", "Minnesota") == 9);
        assertTrue(graph.getPathCost("A", "Olympia") == 8);
        List<String> capitolFilterM = graph.getAllCapitol("M");
        String resM = "";
        for (String capitol : capitolFilterM) {
            resM += capitol;
        }
        assertTrue(resM.contains("Madison"));
        assertTrue(resM.contains("Minnesota"));

        List<String> capitolFilterA = graph.getAllCapitol("A");
        String resA = "";
        for (String capitol : capitolFilterA) {
            resA += capitol;
        }
        assertTrue(resA.contains("A"));
    }

    /**
     * this method test the correctness of getShortestPath method
     */
    @Test
    public void testGetShortestPath() {
        CapitolMapBackend<String, Number> graph = new CapitolMapBackend<String, Number>();
        // insert vertices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");
        graph.insertVertex("Olympia");
        // insert edges
        graph.insertEdge("A", "B", 6);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("A", "D", 5);
        graph.insertEdge("B", "E", 1);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("C", "B", 3);
        graph.insertEdge("C", "F", 1);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("E", "A", 4);
        graph.insertEdge("F", "A", 1);
        graph.insertEdge("F", "D", 1);
        graph.addCapitol("A", "Madison", 3);
        graph.addCapitol("B", "Minnesota", 4);
        graph.addCapitol("A", "Minnesota", 4);
        assertTrue(graph.getPathCost("A", "Madison") == 3);
        assertTrue(graph.getPathCost("B", "Minnesota") == 4);
        // new path with a shorter distance from A to Madison
        graph.insertEdge("A", "G", 1);
        graph.insertEdge("G", "Madison", 1);
        graph.insertEdge("Olympia", "A", 2);
        graph.insertEdge("Madison", "A", 1);
        assertTrue(graph.getPathCost("A", "Madison") == 2);
        assertTrue(graph.shortestPath("A", "Madison").toString().equals("[A, G, Madison]"));
        assertTrue(graph.shortestPath("Olympia", "Minnesota").toString()
                .equals("[Olympia, A, C, B, Minnesota]"));
        assertTrue(graphDouble.getPathCost(1.1, 1.2) == 5);
    }

    /**
     * this method test the correctness of getAllShortestPath method
     */
    @Test
    public void testGetAllShortestPath() {
        CapitolMapBackend<String, Number> graph = new CapitolMapBackend<String, Number>();
        // insert vertices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");
        graph.insertVertex("Olympia");
        // insert edges
        graph.insertEdge("A", "B", 6);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("A", "D", 5);
        graph.insertEdge("B", "E", 1);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("C", "B", 3);
        graph.insertEdge("C", "F", 1);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("E", "A", 4);
        graph.insertEdge("F", "A", 1);
        graph.insertEdge("F", "D", 1);
        graph.addCapitol("A", "Madison", 3);
        graph.addCapitol("B", "Minnesota", 4);
        graph.addCapitol("A", "Minnesota", 4);
        assertTrue(graph.getPathCost("A", "Madison") == 3);
        assertTrue(graph.getPathCost("B", "Minnesota") == 4);
        // new path with a shorter distance from A to Madison
        graph.insertEdge("A", "G", 1);
        graph.insertEdge("G", "Madison", 1);
        graph.insertEdge("Olympia", "A", 2);
        assertTrue(graph.getPathCost("A", "Madison") == 2);
        assertTrue(graph.shortestPath("A", "Madison").toString().equals("[A, G, Madison]"));
        assertTrue(graph.shortestPath("Olympia", "Minnesota").toString()
                .equals("[Olympia, A, C, B, Minnesota]"));

        List<List<String>> allShortestPaths = graph.getAllShortestPath("A");
        String res = "";
        for (int path = 0; path < allShortestPaths.size(); path++) {
            res += allShortestPaths.get(path).toString();
        }
        // System.out.println(res);
        // check if getAllShortestPath works the same way as getShortestPath but return all shortest
        // path
        assertTrue(graph.shortestPath("A", "D").toString().equals("[A, C, F, D]"));
        assertTrue(graph.shortestPath("A", "C").toString().equals("[A, C]"));
        assertTrue(graph.shortestPath("A", "B").toString().equals("[A, C, B]"));
        assertTrue(graph.shortestPath("A", "Madison").toString().equals("[A, G, Madison]"));
        assertTrue(graph.shortestPath("A", "A").toString().equals("[A]"));
        assertTrue(graph.shortestPath("A", "Minnesota").toString().equals("[A, C, B, Minnesota]"));
        assertTrue(graph.shortestPath("A", "G").toString().equals("[A, G]"));
        assertTrue(graph.shortestPath("A", "F").toString().equals("[A, C, F]"));
        assertTrue(graph.shortestPath("A", "E").toString().equals("[A, C, B, E]"));
        // check if the shortestPath is correct
        assertTrue(res.contains("[A, C, F, D]"));
        assertTrue(res.contains("[A, C]"));
        assertTrue(res.contains("[A, C, B]"));
        assertTrue(res.contains("[A, G, Madison]"));
        assertTrue(res.contains("[A, C, B, Minnesota]"));
        assertTrue(res.contains("[A, C, B, E]"));
        assertTrue(res.contains("[A]"));
        assertTrue(res.contains("[A, G]"));
        assertTrue(res.contains("[A, C, F]"));
        assertTrue(res.contains("[A, C, B, E]"));
    }

    /**
     * this method test the correctness of integration with the application
     */
    @Test
    public void testIntegration1() {
        // load capitol map from the data file
        String filepath = "Capitols.dot";
        CapitolMapLoaderImplement capitolLoader = new CapitolMapLoaderImplement();
        // instantiate the backend
        CapitolMapBackend<String, Number> graph = new CapitolMapBackend<String, Number>();
        try {
            // read data from file into graph
            List<ICapitol> allCapitols = capitolLoader.loadFile(filepath);
            // populate graph with vertices
            for (ICapitol capitol : allCapitols) {
                // adding vertex to the graph (only adding unique vertex)
                graph.insertVertex(capitol.getLocation());
                graph.insertVertex(capitol.getDestination());
            }
            // check if the graph contains the right data (data wrangler test)
            List<String> capitolList = graph.getAllCapitol();
            // check the number of vertices
            assertEquals(capitolList.size(), 48);

            for (ICapitol capitol : allCapitols) {
                // adding edges
                graph.insertEdge(capitol.getLocation(), capitol.getDestination(),
                        Integer.parseInt(capitol.getWeight()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // assign backend object to frontned successfully
        // CapitolMapFrontend.backend = graph;
    }

    /**
     * this method test the correctness of integration with the application
     */
    @Test
    public void testIntegration2() {
        // load capitol map from the data file
        String filepath = "src/Capitols.dot";
        CapitolMapLoaderImplement capitolLoader = new CapitolMapLoaderImplement();
        // instantiate the backend
        CapitolMapBackend<String, Number> graph = new CapitolMapBackend<String, Number>();
        try {
            // read data from file into graph
            List<ICapitol> allCapitols = capitolLoader.loadFile(filepath);
            // populate graph with vertices
            for (ICapitol capitol : allCapitols) {
                // adding vertex to the graph (only adding unique vertex)
                graph.insertVertex(capitol.getLocation());
                graph.insertVertex(capitol.getDestination());
            }
            // check if the graph contains the right data (data wrangler test)
            List<String> capitolList = graph.getAllCapitol();
            String[] capitolString = new String[capitolList.size()];
            for (ICapitol capitol : allCapitols) {
                // adding edges
                graph.insertEdge(capitol.getLocation(), capitol.getDestination(),
                        Integer.parseInt(capitol.getWeight()));
            }
            for (int i = 0; i < capitolList.size(); i++) {
                capitolString[i] = capitolList.get(i);
            }
            // testing AE functionality
            PathsDistance AE = new PathsDistance();
            try {
                // test the new york path
                AE.distanceList("New York", capitolString, graph);
                String aeOut = AE.toStringDistanceList("New York", capitolString, graph);
                // System.out.println(aeOut);
                if (!aeOut.contains("Vermont --- 126.0")) {
                    fail("doesn't contain path that should exist");
                }
                if (!aeOut.contains("Pennsylvania --- 230.0")) {
                    fail("doesn't contain path that should exist");
                }
                if (!aeOut.contains("West Virginia --- 517.0")) {
                    fail("doesn't contain path that should exist");
                }
            } catch (NoSuchElementException e) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // assign backend object to frontend successfully
        // CapitolMapFrontend.backend = graph;
    }

    /**
     * this method test the correctness of integration with the application
     */
    @Test
    public void CodeReviewOfDataWrangler1() {
        // load capitol map from the data file
        String filepath = "Capitols.dot";
        CapitolMapLoaderImplement capitolLoader = new CapitolMapLoaderImplement();
        // instantiate the backend
        CapitolMapBackend<String, Number> graph = new CapitolMapBackend<String, Number>();
        try {
            // read data from file into graph
            List<ICapitol> allCapitols = capitolLoader.loadFile(filepath);
            // populate graph with vertices
            for (ICapitol capitol : allCapitols) {
                // adding vertex to the graph (only adding unique vertex)
                graph.insertVertex(capitol.getLocation());
                graph.insertVertex(capitol.getDestination());
            }
            // check if the graph contains the right data (data wrangler test)
            List<String> capitolList = graph.getAllCapitol();
            // check the number of vertices
            assertEquals(capitolList.size(), 48);

            for (ICapitol capitol : allCapitols) {
                // adding edges
                graph.insertEdge(capitol.getLocation(), capitol.getDestination(),
                        Integer.parseInt(capitol.getWeight()));
            }
            String str = "";
            // Alabama must exist in .dot file
            for (String capitol : capitolList) {
                str += capitol;
            }
            if (!str.contains("Alabama")) {
                fail("doesn't contain Alabama that should exist");
            }
            if (!str.contains("Arizona")) {
                fail("doesn't contain Arizona that should exist");
            }
            if (!str.contains("Oregon")) {
                fail("doesn't contain Oregon that should exist");
            }
            if (!str.contains("Washington")) {
                fail("doesn't contain Washington that should exist");
            }
            if (!str.contains("New York")) {
                fail("doesn't contain New York that should exist");
            }
            if (!str.contains("Delaware")) {
                fail("doesn't contain Delaware that should exist");
            }
            // Tests loadFile() method with invalid fileName.
            CapitolMapLoaderImplement testnonvalid = new CapitolMapLoaderImplement();
            FileNotFoundException excp = assertThrows(FileNotFoundException.class, () -> {
                testnonvalid.loadFile(null);
            });
            assertEquals("The file's name is invalid!", excp.getMessage());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // test the content of the graph
        assertEquals(graph.getAllShortestPath("Oregon").toString(), "[[Oregon], [Oregon, Washington]]");
    }

    /**
     * this method test the correctness of integration with the application
     */
    @Test
    public void CodeReviewOfDataWrangler2() {
        // load capitol map from the data file
        String filepath = "Capitols.dot";
        CapitolMapLoaderImplement capitolLoader = new CapitolMapLoaderImplement();
        // instantiate the backend
        CapitolMapBackend<String, Number> graph = new CapitolMapBackend<String, Number>();
        try {
            // read data from file into graph
            List<ICapitol> allCapitols = capitolLoader.loadFile(filepath);
            // populate graph with vertices
            for (ICapitol capitol : allCapitols) {
                // adding vertex to the graph (only adding unique vertex)
                graph.insertVertex(capitol.getLocation());
                graph.insertVertex(capitol.getDestination());
            }
            // check if the graph contains the right data (data wrangler test)
            List<String> capitolList = graph.getAllCapitol();
            // check the number of vertices
            assertEquals(capitolList.size(), 48);

            for (ICapitol capitol : allCapitols) {
                // adding edges
                graph.insertEdge(capitol.getLocation(), capitol.getDestination(),
                        Integer.parseInt(capitol.getWeight()));
            }
            // test if the weight is correct from the datawrangler
            assertEquals(graph.getWeight("Alabama", "Utah"), 178);
            assertEquals(graph.getWeight("Ohio", "Pennsylvania"), 324);
            assertEquals(graph.getWeight("Oregon", "Washington"), 146);
            assertEquals(graph.getWeight("Tennessee", "Virginia"), 524);
            assertEquals(graph.getWeight("Iowa", "Wisconsin"), 409);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // testing the invalid file path
        String file = "./nonexistingfile";
        CapitolMapLoaderImplement testNoFile = new CapitolMapLoaderImplement();
        try {
            List<ICapitol> allCapitols = testNoFile.loadFile(filepath);
            IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, () -> {
                allCapitols.get(-1);
            });
            assertEquals("Index: -1, Size: 102", ex.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail(e);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            fail(e);
        }
    }
}