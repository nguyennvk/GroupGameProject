package AdventureModel;

import javafx.scene.image.ImageView;

import java.io.Serializable; //you will need this to save the game!
import java.util.ArrayList;

/**
 * This class keeps track of the props or the objects in the game.
 * These objects have a name, description, and location in the game.
 * The player with the objects can pick or drop them as they like and
 * these objects can be used to pass certain passages in the game.
 */
public class AdventureObject implements Serializable {
    /**
     * The name of the object.
     */
    private String objectName;

    /**
     * The description of the object.
     */
    private String description;

    /**
     * The location of the object.
     */
    private Room location = null;
    private ArrayList<Integer> coordinates;
    private ImageView imageView;

    /**
     * Adventure Object Constructor
     * ___________________________
     * This constructor sets the name, description, and location of the object.
     *
     * @param name The name of the Object in the game.
     * @param description One line description of the Object.
     * @param location The location of the Object in the game.
     */
    public AdventureObject(String name, String description, Room location, ArrayList<Integer> coordinates, ImageView imageView){
        this.objectName = name;
        this.description = description;
        this.location = location;
        this.coordinates = coordinates;
        this.imageView =  imageView;
    }

    /**
     * Getter method for the name attribute.
     *
     * @return name of the object
     */
    public String getName(){
        return this.objectName;
    }

    /**
     * Getter method for the description attribute.
     *
     * @return description of the game
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * This method returns the location of the object if the object is still in
     * the room. If the object has been pickUp up by the player, it returns null.
     *
     * @return returns the location of the object if the objects is still in
     * the room otherwise, returns null.
     */
    public Room getLocation(){
        return this.location;
    }

    /**
     * This method returns the short description of the object
     *
     * @return returns the description as a String
     */
    public String getShortDescription() {
        // For now, we return the same description.
        // If needed, this method can be modified to return a shorter or different formatted description.
        return this.description;
    }

    /**
     * This method returns image of the object
     *
     * @return returns the image of this object
     */
    public ImageView getImageView()
    {
        return this.imageView;
    }

    /**
     * This method returns the coordinate of the object in 2D plane
     *
     * @return returns the coordinate (x, y) as a ArrayList
     */
    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    /**
     * This method change the coordinate of the object when we drop
     *
     * @param newcoords ArrayList of x an y coordinate
     */
    public void changeLocation(ArrayList<Integer> newcoords) {
        this.coordinates = newcoords;
    }
}
