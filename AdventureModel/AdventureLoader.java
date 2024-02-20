package AdventureModel;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AdventureLoader. Loads an adventure from files.
 */
public class AdventureLoader {

    private AdventureGame game; //the game to return
    private String adventureName; //the name of the adventure

    /**
     * Adventure Loader Constructor
     * __________________________
     * Initializes attributes
     * @param game the game that is loaded
     * @param directoryName the directory in which game files live
     */
    public AdventureLoader(AdventureGame game, String directoryName) {
        this.game = game;
        this.adventureName = directoryName;
    }

     /**
     * Load game from directory
     */
    public void loadGame() throws IOException {
        parseRooms();
        parseObjects();
        parseSynonyms();
        this.game.setHelpText(parseOtherFile("help"));
    }

     /**
     * Parse Rooms File
     */
     private void parseRooms() throws IOException {
         String line;

         int roomNumber;

         String roomFileName = this.adventureName + "/rooms.txt";
         BufferedReader buff = new BufferedReader(new FileReader(roomFileName));

         while (buff.ready()) {

             String currRoom = buff.readLine(); // first line is the number of a room

             roomNumber = Integer.parseInt(currRoom); //current room number

             // now need to get room name
             String roomName = buff.readLine();

             // now we need to get the description
             String roomDescription = buff.readLine();

             // possible monsters
             BasicMonster monster = null;
             String m = buff.readLine();
             if (m.equals("KNIGHT"))
             {
                 monster = new Knight(m, 100, 10, 15, 30, null);
             }
             else if (m.equals("MAGE"))
             {
                 monster = new Mage(m, 100, 20, 5, 60, null);
             } else if (m.equals("BOSS")) {
                 List<SpecialAbility> specialAbilities = new ArrayList<>();
                 specialAbilities.add(new SpecialAbility("Increase Defense", Effect.BUFF));
                 specialAbilities.add(new SpecialAbility("Frenzy", Effect.CUSTOM));
                 monster = new Boss(m, 100, 15, 100, 100, null);

             }


             // now we make the room object
             Room room = new Room(roomName, roomNumber, roomDescription, adventureName, monster); //makes the room

             // now we make the motion table
             buff.readLine(); // reads "-----"
             line = buff.readLine(); //current start of directions
             while (line != null && !line.equals("")) {
                 String[] part = line.split(" \\s+"); // have to use regex \\s+ as we don't know how many spaces are between the direction and the room number
                 String direction = part[0];
                 String dest = part[1];
                 if (dest.contains("/")) {
                     String[] blockedPath = dest.split("/");
                     String dest_part = blockedPath[0];
                     String object = blockedPath[1];
                     Passage entry = new Passage(direction, dest_part, object);
                     room.getMotionTable().addDirection(entry);
                 } else {
                     Passage entry = new Passage(direction, dest);
                     room.getMotionTable().addDirection(entry);
                 }
                 line = buff.readLine();

                 this.game.getRooms().put(room.getRoomNumber(), room);
             }

         }
     }



    /**
     * Parse Objects File
     */
    public void parseObjects() throws IOException {

        String objectFileName = this.adventureName + "/objects.txt";
        BufferedReader buff = new BufferedReader(new FileReader(objectFileName));

        while (buff.ready()) {
            ArrayList<Integer> coordinates = new ArrayList<>();
            String objectName = buff.readLine();
            String objectDescription = buff.readLine();
            String roomLocation = buff.readLine();
            String xCoordinate = buff.readLine();
            String yCoordinate = buff.readLine();
            String fileType = buff.readLine();
            int width = Integer.parseInt(buff.readLine());
            int height = Integer.parseInt(buff.readLine());
            String separator = buff.readLine();
            ImageView imageView = new ImageView(new Image(this.adventureName+"/objectImages/"+objectName+"."+fileType));
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);
            if (separator != null && !separator.isEmpty())
                System.out.println("Formatting Error!");
            int i = Integer.parseInt(roomLocation);
            int x = Integer.parseInt(xCoordinate);
            int y = Integer.parseInt(yCoordinate);
            Room location = this.game.getRooms().get(i); //WILL HAVE TO CHANGE THIS METHOD
            coordinates.add(x);
            coordinates.add(y);
            AdventureObject object = new AdventureObject(objectName, objectDescription, location, coordinates, imageView);
            location.addGameObject(object);
        }

    }

     /**
     * Parse Synonyms File
     */
    public void parseSynonyms() throws IOException {
        String synonymsFileName = this.adventureName + "/synonyms.txt";
        BufferedReader buff = new BufferedReader(new FileReader(synonymsFileName));
        String line = buff.readLine();
        while(line != null){
            String[] commandAndSynonym = line.split("=");
            String command1 = commandAndSynonym[0];
            String command2 = commandAndSynonym[1];
            this.game.getSynonyms().put(command1,command2);
            line = buff.readLine();
        }

    }

    /**
     * Parse Files other than Rooms, Objects and Synonyms
     *
     * @param fileName the file to parse
     */
    public String parseOtherFile(String fileName) throws IOException {
        String text = "";
        fileName = this.adventureName + "/" + fileName + ".txt";
        BufferedReader buff = new BufferedReader(new FileReader(fileName));
        String line = buff.readLine();
        while (line != null) { // while not EOF
            text += line+"\n";
            line = buff.readLine();
        }
        return text;
    }

}
