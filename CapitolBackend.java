// --== CS400 File Header Information ==--
// Name: Nattarach Larptaweepornsup
// Email: larptaweepor@wisc.edu
// Team: AD
// TA: YUYE JIANG
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;

/**
 * This interface defines method for backend of the application
 *
 * @author Nattarach Larptaweepornsup
 *
 */
public interface CapitolBackend<NodeType, EdgeType extends Number>
        extends GraphADT<NodeType, EdgeType> {

    /**
     * Adding a capitol and weight to the existing list
     *
     * @param newCapitol   name of the new capitol to be added to the list
     * @param existCapitol name of the existing capital to be connected with the new Capitol
     * @param weight       distance between the new capitol and the existing capitol
     * @return true if add capitol successfully, false otherwise
     */
    public boolean addCapitol(NodeType existCapitol, NodeType newCapitol, EdgeType weight);

    /**
     * Get all the name of capitol existing in the list
     *
     * @return list of nodeType of all capitol
     */
    public List<NodeType> getAllCapitol();

    /**
     * Get all the name of capitol existing in the list specified with prefix
     *
     * @param prefix the prefix for filtering the name of all capitol
     * @return list of nodeType of all capitol based on the filter prefix
     */
    public List<NodeType> getAllCapitol(String prefix);


    /**
     * Get the shortest path between two capitol
     *
     * @param startCapitol the start of the capitol
     * @param endCapitol   the end of the capitol
     * @return Returns a list containing the shortest path(s), if any, between the specified location
     *         of capitol
     */
    public List<NodeType> getShortestPath(NodeType startCapitol, NodeType endCapitol);

    /**
     * Get all of the shortest path based on the starting location
     *
     * @param startCapitol the start of the capitol
     * @return Returns a list containing all shortest paths based on the starting location
     */
    public List<List<NodeType>> getAllShortestPath(NodeType startCapitol);
}