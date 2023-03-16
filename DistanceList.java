import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is an extension from dijkstras algorithm. It provides the shortest path to every other
 * capital for user to check
 *
 */
public interface DistanceList {

    /**
     * This method provides the shortest distance from one location to every other location, return a
     * list of path from shortest to longest. In this method, AE needs to go through each dijkstra's
     * algorithm form one place to every capital. Store the shortest path for each capital, and return
     * all the shortest path in ascending order. It provides the shortest path to every other capital
     * for user to check
     *
     * @param city the city user choose to entered
     * @throws java.util.NoSuchElementException if string of city is not found in graph
     */

    public LinkedList<CapitolMapBackend.Path> distanceList(String city, String[] capitals,
                                                           CapitolMapBackend graph);

    /**
     * This method provides a string of the shortest path in ascending order It uses the distanceList
     * to get all path and return the start,end, and distance of each path. As a result, user could
     * see a list of every distance in ascending order on screen by calling this method
     *
     * @param city the city user choose to entered
     */
    public String toStringDistanceList(String city, String[] capitals, CapitolMapBackend graph);
}