package AdventureModel;

import AdventureModel.npc.NPC;
import AdventureModel.npc.NPCInitializer;
import AdventureModel.npc.VisitableNPC;
import views.AdventureGameView;

import java.io.*;
import java.util.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Class AdventureGame.  Handles all the necessary tasks to run the Adventure game.
 */
public class AdventureGame implements Serializable {
    private final String directoryName; //An attribute to store the Introductory text of the game.
    private String helpText; //A variable to store the Help text of the game. This text is displayed when the user types "HELP" command.
    private final HashMap<Integer, Room> rooms; //A list of all the rooms in the game.

    private HashMap<String,String> synonyms = new HashMap<>(); //A HashMap to store synonyms of commands.
    private final String[] actionVerbs = {"QUIT", "INVENTORY", "TAKE", "DROP", "TALK", "OPEN"}; //List of action verbs (other than motions) that exist in all games. Motion vary depending on the room and game.
    public Player player; //The Player of the game.
    public NPCInitializer npcInitializer; //The entity that creates and manages all NPCs
    private boolean inBattle = false; //The state that determine if the player is in any battles
    private Monster currentMonster; //The current monster that player have to face at this room

    /**
     * Adventure Game Constructor
     * __________________________
     * Initializes attributes
     *
     * @param name the name of the adventure
     */
    public AdventureGame(String name) {
        this.synonyms = new HashMap<>();
        this.rooms = new HashMap<>();
        this.directoryName = "Games/" + name; //all games files are in the Games directory!
        this.npcInitializer = new NPCInitializer(this);
        try {
            setUpGame();
        } catch (IOException e) {
            throw new RuntimeException("An Error Occurred: " + e.getMessage());
        }
    }

    /**
     * Save the current state of the game to a file
     *
     * @param file pointer to file to write to
     */
    public void saveModel(File file) {
        try {
            FileOutputStream outfile = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(outfile);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * setUpGame
     * Initialize player status
     *
     * @throws IOException in the case of a file I/O error
     */
    public void setUpGame() throws IOException {
        String directoryName = this.directoryName;
        AdventureLoader loader = new AdventureLoader(this, directoryName);
        loader.loadGame();

        //Initialize player status
        String playerName = "Hero";
        int playerMaxHp = 100;
        int playerAttack = 30;
        int playerDefense = 0;
        int playerMp = 50;

        Room startingRoom = this.rooms.get(1);

        this.player = new Player(startingRoom, playerName, playerMaxHp, playerAttack, playerDefense, playerMp);

    }

    /**
     * tokenize
     * __________________________
     *
     * @param input string from the command line
     * @return a string array of tokens that represents the command.
     */
    public String[] tokenize(String input) {

        input = input.toUpperCase();
        String[] commandArray = input.split(" ");

        int i = 0;
        while (i < commandArray.length) {
            if (this.synonyms.containsKey(commandArray[i])) {
                commandArray[i] = this.synonyms.get(commandArray[i]);
            }
            i++;
        }
        return commandArray;

    }

    /**
     * movePlayer
     * __________________________
     * Moves the player in the given direction, if possible.
     * Return false if the player wins or dies as a result of the move.
     *
     * @param direction the move command
     * @return false, if move results in death or a win (and game is over).  Else, true.
     */
    public boolean movePlayer(String direction) {

        direction = direction.toUpperCase();
        PassageTable motionTable = this.player.getCurrentRoom().getMotionTable(); //where can we move?
        if (!motionTable.optionExists(direction)) return true; //no move

        ArrayList<Passage> possibilities = new ArrayList<>();
        for (Passage entry : motionTable.getDirection()) {
            if (entry.getDirection().equals(direction)) { //this is the right direction
                possibilities.add(entry); // are there possibilities?
            }
        }

        //try the blocked passages first
        Passage chosen = null;
        for (Passage entry : possibilities) {
            if (chosen == null && entry.getIsBlocked()) {
                if (this.player.getInventory().contains(entry.getKeyName())) {
                    chosen = entry; //we can make it through, given our stuff
                    break;
                }
            } else {
                chosen = entry;
            } //the passage is unlocked
        }

        if (chosen == null) return true; //doh, we just can't move.

        int roomNumber = chosen.getDestinationRoom();
        Room room = this.rooms.get(roomNumber);
        this.player.setCurrentRoom(room);
        System.out.println("[movePlayer] player is in room " + room.getRoomNumber());
        return !this.player.getCurrentRoom().getMotionTable().getDirection().get(0).getDirection().equals("FORCED");
    }

    /**
     * interpretAction
     * interpret the user's action.
     *
     * @param command String representation of the command.
     */
    public String interpretAction(String command) {

        String[] inputArray = tokenize(command); //look up synonyms

        PassageTable motionTable = this.player.getCurrentRoom().getMotionTable(); //where can we move?

        if (motionTable.optionExists(inputArray[0])) {
            if (!movePlayer(inputArray[0])) {
                System.out.println("move player returned false");
                if (this.player.getCurrentRoom().getMotionTable().getDirection().get(0).getDestinationRoom() == 0)
                    return "GAME OVER";
                else return "FORCED";
            }

            //something is up here! We are dead or we won.
            //if the player is currently interacting with an NPC, end that interaction
            if (player.getInteractingNPC() != null) {
                player.removeInteractingNPC();
            }
            return null;
        } else if (Arrays.asList(this.actionVerbs).contains(inputArray[0])) {
            if (inputArray[0].equals("QUIT")) {
                return "GAME OVER";
            } //time to stop!
            else if (inputArray[0].equals("INVENTORY") && this.player.getInventory().size() == 0)
                return "INVENTORY IS EMPTY";
            else if (inputArray[0].equals("INVENTORY") && this.player.getInventory().size() > 0)
                return "THESE OBJECTS ARE IN YOUR INVENTORY:\n" + this.player.getInventory().toString();
            else if (inputArray[0].equals("TAKE") && inputArray.length < 2)
                return "THE TAKE COMMAND REQUIRES AN OBJECT";
            else if (inputArray[0].equals("DROP") && inputArray.length < 2)
                return "THE DROP COMMAND REQUIRES AN OBJECT";
            else if (inputArray[0].equals("TAKE") && inputArray.length == 2) {
                if (this.player.getCurrentRoom().checkIfObjectInRoom(inputArray[1])) {
                    this.player.takeObject(inputArray[1]);
                    return "YOU HAVE TAKEN:\n " + inputArray[1];
                } else {
                    return "THIS OBJECT IS NOT HERE:\n " + inputArray[1];
                }
            } else if (inputArray[0].equals("DROP") && inputArray.length == 2) {
                if (this.player.checkIfObjectInInventory(inputArray[1])) {
                    AdventureObject o = this.player.dropObject(inputArray[1]);
                    return "YOU HAVE DROPPED:\n " + inputArray[1];
                } else {
                    return "THIS OBJECT IS NOT IN YOUR INVENTORY:\n " + inputArray[1];
                }
            } else if (inputArray[0].equals("TALK") && this.player.getInteractingNPC() == null) {
                //in the final game, player can walk up to NPC, press button then start interacting
                //for now, if player types "TALK", start/continue an interaction with the NPC in the current room
                VisitableNPC npc = this.getNPCInitializer().getNPCFromRoomNumber(player.getCurrentRoom().getRoomNumber());
                if (npc != null) {
                    this.player.visitNPC(npc);
                } else {
                    return "THERE'S NOBODY HERE TO TALK TO";
                }
                return "NPC " + npc.getNextLine();
            } else if (inputArray[0].equals("TALK") && this.player.getInteractingNPC() != null) {
                String nextLine = this.player.getInteractingNPC().getNextLine();

                if (nextLine.equals("RESTART CONVERSATION")) {

                    this.player.removeInteractingNPC();
                    return "LOOK";
                } else {
                    return "NPC " + nextLine;
                }
            }
        }
        if (inputArray[0].equals("TALK") && this.player.getInteractingNPC() == null) {
            //in the final game, player can walk up to NPC, press button then start interacting
            //for now, if player types "TALK", start/continue an interaction with the NPC in the current room
            VisitableNPC npc = this.getNPCInitializer().getNPCFromRoomNumber(player.getCurrentRoom().getRoomNumber());
            if (npc != null) {
                this.player.visitNPC(npc);
            } else {
                return "THERE'S NOBODY HERE TO TALK TO";
            }
            return "NPC " + npc.getNextLine();
        } else if (inputArray[0].equals("TALK") && this.player.getInteractingNPC() != null) {
            String nextLine = this.player.getInteractingNPC().getNextLine();

            if (nextLine.equals("RESTART CONVERSATION")) {

                this.player.removeInteractingNPC();
                return "LOOK";
            } else {
                return "NPC " + nextLine;
            }
        }
        else if(inputArray[0].equals(("OPEN")) && inputArray.length == 2) {
            return "You opened your bag";
        }
            return "INVALID COMMAND.";
    }

    /**
     * getDirectoryName
     * __________________________
     * Getter method for directory
     *
     * @return directoryName
     */
    public String getDirectoryName() {
        return this.directoryName;
    }

    /**
     * getInstructions
     * __________________________
     * Getter method for instructions
     *
     * @return helpText
     */
    public String getInstructions() {
        return helpText;
    }

    /**
     * getPlayer
     * __________________________
     * Getter method for Player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * getRooms
     * __________________________
     * Getter method for rooms
     *
     * @return map of key value pairs (integer to room)
     */
    public HashMap<Integer, Room> getRooms() {
        return this.rooms;
    }

    /**
     * getSynonyms
     * __________________________
     * Getter method for synonyms
     *
     * @return map of key value pairs (synonym to command)
     */
    public HashMap<String, String> getSynonyms() {
        return this.synonyms;
    }

    /**
     * Getter method for npcInitializer
     *
     * @return this game's NPCInitializer
     */
    public NPCInitializer getNPCInitializer() {
        return this.npcInitializer;
    }

    /**
     * setHelpText
     * __________________________
     * Setter method for helpText
     *
     * @param help which is text to set
     */
    public void setHelpText(String help) {
        this.helpText = help;
    }
    /**
     * setInBattle
     * __________________________
     * Setter method for inBattle
     *
     * @param b which is text to set
     */
    public void setInBattle(boolean b)
    {
        this.inBattle = b;
    }
    /**
     * setCurrentMonster
     * __________________________
     * Setter method for currentMonster
     *
     * @param m which is text to set
     */
    public void setCurrentMonster(Monster m)
    {
        this.currentMonster = m;
    }

    /**
     * playerDefend
     * __________________________
     * make the player defend from monster attack
     *
     */
    public void playerDefend() {
        // Implement defense logic
        if (inBattle) {
            player.defend();
            currentMonster.attack(player);
        }
    }

    /**
     * endBattle
     * __________________________
     * end the battle when player or monster die
     *
     */
    private void endBattle() {
        if (currentMonster.isAlive()) { //end if monster is alive
            currentMonster.resetState();
        }
        inBattle = false;
        currentMonster = null;
        // Switch back to exploration mode
    }


    /**
     * attackMonster
     * __________________________
     * attack the monster subtract monster health
     * @param currentMonster which is the monster that we are facing
     *
     */
    public void attackMonster(Monster currentMonster) {
        if (inBattle) {
            player.attack(currentMonster);
            if (currentMonster.isDefeated()) {
                endBattle();
            }
            currentMonster.attack(player);

        }
    }
    /**
     * Heal
     * __________________________
     * Increase player health by 20
     *
     */
    public boolean Heal() {
        if (inBattle) {
            if (player.getMana() >= 20) {//only increase when enough mana
                player.heal(20);
                player.useMana(20);

                return true;
            }
        }
        return false;
    }

    /**
     * RapidAttack
     * __________________________
     * special attack where monster cannot fight back
     *
     */
    public boolean RapidAttack() {
        if (inBattle) {
            if (player.getMana() >= 30) { //check is enough mana
                player.rapidAttack();
                player.attack(currentMonster);
                player.attack(currentMonster);
                player.useMana(30);
                currentMonster.attack(player);
                return true;
            }
        }
        return false;

    }
}
