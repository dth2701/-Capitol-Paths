// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo7@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

/**
 * The class implement the interface ICapitol to get the data of the state capitol
 */
public class ICapitolImplement implements ICapitol{
    private String location; //The location of the state capitol
    private String destination;; //The destination that is mapping to the state capitol
    private String weight; //The weight of the state capitol

    /**
     * The constructor of the class.
     * @param location The location of the state capitol
     * @param location The destination that is mapping to the state capitol
     * @param weight The weight of the state capitol
     */
    public ICapitolImplement (String location, String destination, String weight){
        this.location = location;
        this.destination = destination;
        this.weight = weight;

    }
    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public String getWeight() {
        return this.weight;
    }

}
