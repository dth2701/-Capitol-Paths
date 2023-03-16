// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo7@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;
import java.io.FileNotFoundException;

/**
 * This interface defines the method to load a list if capitols of a graph.
 */
interface CapitolMapLoader {
    /**
     * Returns a list of ICapitols parsed from the input file.
     * @param fileName the file path to the desire DOT file.
     * @return a list of capitols  parsed from the input file path
     * @throws FileNotFoundException if the file is invalid and does not exit.
     */
    public List<ICapitol> loadFile(String fileName) throws FileNotFoundException;
}