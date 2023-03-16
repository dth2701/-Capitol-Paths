// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo7@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * This class tests the files and code of the Data Wrangler of Team Blue for Project 3
 *
 * @author Huong Thien Do
 *
 */
public class DataWranglerTests {
    /**
     * Tests the correctness and functionality of the getName(), getDestination() and getWeight() in
     * ICapitolImplement class.
     */
    @Test
    public void test1() {
        String location = "location";
        String destination = "destination";
        String weight = "100";
        ICapitolImplement test1 = new ICapitolImplement(location, destination , weight);

        assertEquals("location", test1.getLocation());
        assertEquals("destination", test1.getDestination());
        assertEquals("100", test1.getWeight());
    }
    /**
     * Tests the correctness and functionality of the loadFile() method with invalid fileName.
     */
    @Test
    public void test2() {
        CapitolMapLoaderImplement test3 = new CapitolMapLoaderImplement();
        FileNotFoundException ex = assertThrows(FileNotFoundException.class, () -> {
            test3.loadFile(null);
        });
        assertEquals("The file's name is invalid!", ex.getMessage());
    }

    /**
     * Tests the correctness and functionality of the loadFile() method with valid data.
     */
    @Test
    public void test3() {
        String filepath = "src/Capitols.dot";
        CapitolMapLoaderImplement test2 = new CapitolMapLoaderImplement();
        try {
            List<ICapitol> allCapitols = test2.loadFile(filepath);
            assertEquals("Alabama", allCapitols.get(2).getLocation());
            assertEquals("Georgia", allCapitols.get(2).getDestination());
            assertEquals("104", allCapitols.get(2).getWeight());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests the correctness and functionality of the loadFile() method with invalid fileName.
     */
    @Test
    public void test4() {
        String filepath = "src/Capitols.dot";
        CapitolMapLoaderImplement test4 = new CapitolMapLoaderImplement();
        try {
            List<ICapitol> allCapitols = test4.loadFile(filepath);
            IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, () -> {
                allCapitols.get(-1);
            });
            assertEquals("Index: -1, Size: 102", ex.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail(e);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    /**
     * Tests the correctness and functionality of the loadFile() method with random valid datas.
     */
    @Test
    public void test5() {
        String filepath = "src/Capitols.dot";
        CapitolMapLoaderImplement test2 = new CapitolMapLoaderImplement();
        try {
            List<ICapitol> allCapitols = test2.loadFile(filepath);
            assertEquals("Delaware", allCapitols.get(25).getLocation());
            assertEquals("Missouri", allCapitols.get(11).getDestination());
            assertEquals("186", allCapitols.get(37).getWeight());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Checks the Backend Developer code when running with the Data Wrangler code work as expected.
     */
    @Test
    public void CodeReviewOfBackendDeveloper1() {
        String filepath = "src/Capitols.dot";
        CapitolMapLoaderImplement loaderTest = new CapitolMapLoaderImplement();
        CapitolMapBackend<String, Number> graphTest = new CapitolMapBackend<>();
        try {
            // read data from file into graph
            List<ICapitol> allCapitols = loaderTest.loadFile(filepath);
            for (ICapitol capitol : allCapitols) {
                // adding vertex to the graph
                graphTest.insertVertex(capitol.getLocation());
                graphTest.insertVertex(capitol.getDestination());

            }
            List<String> capitolList = graphTest.getAllCapitol();
            for (ICapitol capitol : allCapitols) {
                // adding edges
                graphTest.insertEdge(capitol.getLocation(), capitol.getDestination(),
                                      Integer.parseInt(capitol.getWeight()));
            }
            // Tests the size of vertices
            assertEquals(capitolList.size(), 48);
            // Tests getAllCapitol() method with valid data.
            assertEquals(true, capitolList.contains("Alabama"));
            assertEquals(true, capitolList.contains("Texas"));
            assertEquals(true, capitolList.contains("South Dakota"));
            assertEquals(true, capitolList.contains("Vermont"));
            // Tests getAllCapitol() method with invalid data.
            assertEquals(false, capitolList.contains("None"));

            // Tests getAllCapitol() with the prefix parameter.
            List<String> capitolListwithM = graphTest.getAllCapitol("M");
            // Tests the size of vertices
            assertEquals(capitolListwithM.size(), 8);
            assertEquals(true, capitolList.contains("Montana"));
            assertEquals(true, capitolList.contains("Minnesota"));
            // Tests getAllCapitol() method with invalid data.
            assertEquals(false, capitolList.contains("Mmmmmmm"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Tests the correctness and functionality of the loadFile() method with random valid datas.
     */
    @Test
    public void CodeReviewOfBackendDeveloper2() {
        String filepath = "src/Capitols.dot";
        CapitolMapLoaderImplement loaderTest = new CapitolMapLoaderImplement();
        CapitolMapBackend<String, Number> graphTest = new CapitolMapBackend<>();
        try {
            List<ICapitol> allCapitols = loaderTest.loadFile(filepath);
            for (ICapitol capitol : allCapitols) {
                graphTest.insertVertex(capitol.getLocation());
                graphTest.insertVertex(capitol.getDestination());
            }
            List<String> capitolList = graphTest.getAllCapitol();
            for (ICapitol capitol : allCapitols) {
                // adding edges
                graphTest.insertEdge(capitol.getLocation(), capitol.getDestination(),
                        Integer.parseInt(capitol.getWeight()));
            }
            assertEquals(250, graphTest.getWeight("Colorado", "New Mexico"));
            assertEquals(297, graphTest.getWeight("Idaho", "Utah"));
            assertEquals("[Alabama, New York, Vermont]",
                    graphTest.shortestPath("Alabama", "Vermont").toString());
            NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> {
                graphTest.shortestPath("Ohio", "Texas").toString();
            });
            assertEquals("Path not exist", ex.getMessage());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the codes of 4 roles work as expected.
     */
    @Test
    public void testIntegration1() {
        String filepath = "src/Capitols.dot";
        CapitolMapLoaderImplement loaderTest = new CapitolMapLoaderImplement();
        CapitolMapBackend<String, Number> graphTest = new CapitolMapBackend<>();
        PathsDistance testAE = new PathsDistance();
        try {
            List<ICapitol> allCapitols = loaderTest.loadFile(filepath);
            for (ICapitol capitol : allCapitols) {
                graphTest.insertVertex(capitol.getLocation());
                graphTest.insertVertex(capitol.getDestination());
            }
            List<String> capitolList = graphTest.getAllCapitol();
            for (ICapitol capitol : allCapitols) {
                graphTest.insertEdge(capitol.getLocation(), capitol.getDestination(),
                        Integer.parseInt(capitol.getWeight()));
            }
            String[] capitolStr = new String[capitolList.size()];
            for (int i = 0; i < capitolList.size(); i++) {
                capitolStr[i] = capitolList.get(i);
            }
            testAE.distanceList("Nevada", capitolStr, graphTest);
            String resultAE = testAE.toStringDistanceList("Nevada", capitolStr, graphTest);

            if (!resultAE.contains("Utah --- 430.0")) {
                fail("fail Test!");
            }
            if (!resultAE.contains("Washington --- 578.0")) {
                fail("fail Test!");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        /**
         * Checks the codes of 4 roles work as expected.
         */
        @Test
        public void testIntegration2() {
            String filepath = "src/Capitols.dot";
            CapitolMapLoaderImplement loaderTest = new CapitolMapLoaderImplement();
            CapitolMapBackend<String, Number> graphTest = new CapitolMapBackend<>();
            PathsDistance testAE = new PathsDistance();
            try {
                List<ICapitol> allCapitols = loaderTest.loadFile(filepath);
                for (ICapitol capitol : allCapitols) {
                    // adding vertex to the graph (only adding unique vertex)
                    graphTest.insertVertex(capitol.getLocation());
                    graphTest.insertVertex(capitol.getDestination());
                }
                List<String> capitolList = graphTest.getAllCapitol();
                for (ICapitol capitol : allCapitols) {
                    graphTest.insertEdge(capitol.getLocation(), capitol.getDestination(),
                            Integer.parseInt(capitol.getWeight()));
                }
                assertEquals(capitolList.size(), 48);

            }  catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
}
