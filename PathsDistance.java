// --== CS400 File Header Information ==--
// Name: nisitha de silva
// Email: <ntdesilva@wisc.edu email address>
// Team: <AD>
// TA: <name of your team's ta>
// Lecturer: Garry Dhal
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class PathsDistance implements DistanceList {
  private LinkedList<CapitolMapBackend.Path> PathsFound = new LinkedList<CapitolMapBackend.Path>();

  @Override
  public LinkedList<CapitolMapBackend.Path> distanceList(String city, String[] capitals,
                                                         CapitolMapBackend graph) {
    repeatDij(city, capitals, graph);
    return PathsFound;
  }

  private void repeatDij(String city, String[] capitals, CapitolMapBackend<String, Number> graph) {
    for (int i = 0; i < capitals.length; i++) {
      if (capitals[i].equals(city)) {
        continue;
      }
      try {
        sort(graph.dijkstrasShortestPath(city, capitals[i]));
      } catch (NoSuchElementException e) {

      }
      // sort(graph.dijkstrasShortestPath(city, capitals[i]));

    }
  }

  private void sort(CapitolMapBackend.Path temp) {
    System.out.println(temp);
    System.out.println(temp.end);
    System.out.println(temp.distance);
    if (PathsFound.isEmpty()) {
      PathsFound.add(temp);
      return;
    }
    if (PathsFound.get(0).distance > temp.distance) {
      PathsFound.addFirst(temp);
      return;
    }
    if (PathsFound.get(PathsFound.size() - 1).distance < temp.distance) {
      PathsFound.add(temp);
      return;
    }
    for (int i = 0; i < PathsFound.size() - 2; i++) {
      if (PathsFound.get(i).distance <= temp.distance
              && PathsFound.get(i + 1).distance >= temp.distance) {
        PathsFound.add(i + 1, temp);
        return;
      }
    }
  }

  @Override
  public String toStringDistanceList(String city, String[] capitals, CapitolMapBackend graph) {
    String list = "";
    String temp = "";
    for (int i = 0; i < PathsFound.size(); i++) {
//      System.out.println(PathsFound.getFirst());
      temp = PathsFound.get(i).end.data + " --- " + PathsFound.get(i).distance + "\n";
      list = list.concat(temp);
    }
    return list;
  }

}