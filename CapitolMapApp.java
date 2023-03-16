// --== CS400 File Header Information ==--
// Name: Nattarach Larptaweepornsup
// Email: larptaweepor@wisc.edu
// Team: AD
// TA: YUYE JIANG
// Lecturer: Gary Dahl
// Notes to Grader: https://docs.oracle.com/javase/7/docs/api/java/util/Set.html

import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * Main application of Capitol Map
 *
 * @author Nattarach Larptaweepornsup
 *
 */
public class CapitolMapApp {

    public static void main(String[] args) {
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
            for (ICapitol capitol : allCapitols) {
                // adding edges
                graph.insertEdge(capitol.getLocation(), capitol.getDestination(),
                        Integer.parseInt(capitol.getWeight()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CapitolMapFrontend.backend = graph;
        CapitolMapFrontend.main(args);
    }
}