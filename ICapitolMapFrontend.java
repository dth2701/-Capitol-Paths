// --== CS400 Project Two File Header ==--
// Name: Michael Song
// CSL Username: msong
// Email: mmsong@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.List;
import javafx.stage.Stage;


/**
 * This interface defines methods for running the Map Loader application
 * 
 * @author Michael Song
 *
 */
public interface ICapitolMapFrontend {
  /**
   * Starts and runs the application
   * 
   * @param stage - Stage object to run
   * @throws FileNotFoundException
   */
  void start(Stage stage) throws FileNotFoundException;

  /**
   * Forwards input of starting and destination capitols and returns the shortest path
   * @param start - starting capitol
   * @param destination - destination capitol
   * @return - List pertaining to the shortest path
   */
  List<String> sendInputToBack(String start, String destination);
}
