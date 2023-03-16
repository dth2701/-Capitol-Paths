// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo7@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * The class implements the CapitolMapLoader interface to load the file.
 */
public class CapitolMapLoaderImplement implements CapitolMapLoader {
    /**
     * Returns a list of ICapitols parsed from the input file.
     * @param fileName the file path to the desire DOT file.
     * @return a list of capitols from the graph
     * @throws FileNotFoundException
     */
    @Override
    public List<ICapitol> loadFile(String fileName) throws FileNotFoundException {
        List<ICapitol> allCapitols = new LinkedList<>();
        if (fileName == null) {
            throw new FileNotFoundException("The file's name is invalid!");
        }
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found!");
        }
        Scanner reader = new Scanner(file);
        reader.nextLine(); //Skip the first line
        while (reader.hasNextLine()) {
            String currLine = reader.nextLine().trim();
            if (!reader.hasNextLine()) break; //If read the last line, which contains "{", break the loop.

            String[] twoTokens = currLine.split("--");
            String location = twoTokens[0].substring(1, twoTokens[0].length() - 2);

            //The array of the second token that each token contains the destination and weight in one line.
            String[] secondToken = twoTokens[1].trim().split("\\[");
            String destination = secondToken[0].substring(1, secondToken[0].length() - 2);

            //The array that each token contains the word "weight" and the number.
            String[] weightToken = secondToken[1].split("=");
            String weight = weightToken[1].substring(0, weightToken[1].length() - 1);

            ICapitolImplement newCapitol = new ICapitolImplement(location, destination, weight);
            allCapitols.add(newCapitol);
        }
        return allCapitols;
    }
}
