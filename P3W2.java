//import java.util.LinkedList;
//import java.util.NoSuchElementException;
//import java.util.PriorityQueue;
//
//public class P3W2 {
//    public Path(CS400Graph.Path copyPath, CS400Graph.Edge extendBy) {
//        // TODO: Implement this constructor in Step 5.
//        this.start = copyPath.start;
//        this.dataSequence = new LinkedList<>(copyPath.dataSequence); //deep copy
//        this.dataSequence.add(extendBy.target.data);
//        this.distance = copyPath.distance + extendBy.weight.doubleValue();
//        this.end = extendBy.target;
//    }
//    protected CS400Graph.Path dijkstrasShortestPath(NodeType start, NodeType end) {
//        // TODO: Implement this method in Step 7.
//        PriorityQueue<CS400Graph.Path> pq = new PriorityQueue<>();
//        LinkedList<CS400Graph.Vertex> visited = new LinkedList<>();
//
//        //if there is no vertex containing start or end can be found
//        if(!vertices.containsKey(start) || !vertices.containsKey(end)){
//            throw new NoSuchElementException("No vertex containing start or end can be found!");
//        }
//        //Else, search the path.
//        CS400Graph.Vertex startVertex = vertices.get(start);
//        //Always add the first starting vertex to visited list.
//        visited.add(startVertex);
//
//        //Add nodes from starting node to successors in priority queue.
//        for(int i = 0; i < startVertex.edgesLeaving.size(); ++i){
//            CS400Graph.Path startP = new CS400Graph.Path(new CS400Graph.Path(startVertex), startVertex.edgesLeaving.get(i));
//            pq.add(startP);
//        }
//
//        //Remove the shortest path when pq is not empty
//        while(!pq.isEmpty()) {
//            CS400Graph.Path shortestP = pq.remove();
//            CS400Graph.Vertex visitingV = shortestP.end; //the vertex were visiting.
//            visited.add(visitingV);
//            if (visitingV.equals(vertices.get(end))) {return shortestP;}
//            //Check if the successor vertex has not in the visited list.
//            for (int i = 0; i < visitingV.edgesLeaving.size(); i++) {
//                //Extend this vertex with the current path and add that path to pq.
//                if (!visited.contains(visitingV.edgesLeaving.get(i).target)) {
//                    pq.add(new CS400Graph.Path(shortestP, visitingV.edgesLeaving.get(i)));
//                }
//            }
//        }
//        //If the search fails to find any path from the start to end vertices.
//        throw new NoSuchElementException("There is not existed path");
//    }
//}
