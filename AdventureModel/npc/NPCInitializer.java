package AdventureModel.npc;

/**
provides a set of commands for creating, adding, and managing NPCs in the game
 */

import AdventureModel.AdventureGame;
import views.AdventureGameView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import AdventureModel.Room;
import views.AdventureGameView;

import java.util.*;

public class NPCInitializer {

    Map<Integer, VisitableNPC> npcMap;
    AdventureGameView view; //TODO add a way to set this
    //String gameDirPath;
    AdventureGame game;

    /**
    NPCInitializer constructor, initializes attributes and locates NPCs in their rightful place from txt files.

     @param game the game this class is based off of
     */
    public NPCInitializer(AdventureGame game) {
        this.game = game;
        npcMap = new HashMap<>();

        // for each file in npcfiles directory, create a new NPC corresponding to that file then add it to the list of
        // active NPCs in the game

        File gameDir = new File(this.game.getDirectoryName() + "/npcfiles");
        File file1 = new File(this.game.getDirectoryName());
        System.out.println(this.game.getDirectoryName());
        System.out.println(file1.exists());
        List<File> fileList = Arrays.asList(gameDir.listFiles());

        for (File file : fileList) {
            System.out.println(file.getName());
            addNPCFromFile(file);
        }
    }

    /**
    Read the text file with the given path to extract information about the NPC corresponding to that file

     @param file the path to the file with information about an NPC
     */
    private void addNPCFromFile(File file) {

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //read these attributes from the file
            String name = br.readLine();
            int roomNumber = Integer.parseInt(br.readLine());
            List<String> monologue = new ArrayList<>();

            //while there is a non-blank line, that line is monologue
            String line;
            while ((line = br.readLine()) != null) {
                monologue.add(line);
            }

            //System.out.println(name);
            //System.out.println(roomNumber);
            //System.out.println(monologue);

            String imagePath = this.game.getDirectoryName() + "/npc-images/" + file.getName().replace(".txt", ".png");
            //System.out.println(imagePath);

            VisitableNPC npc = new NPC(name, monologue, imagePath);
            this.npcMap.put(roomNumber, npc);
        } catch (IOException e) {
            System.out.println("oopsie doopsie doo");
        }
    }

    /**
    Add a new npc into the game

     @param roomNumber the room number this NPC will be located in
     @param npc the NPC to add into the game
     */
    public void addNPC(int roomNumber, VisitableNPC npc) {
        this.npcMap.put(roomNumber, npc);
    }

    /**
    Getter method for NPC

     @param roomNumber the number of the room in which the NPC resides
     @return the NPC that corresponds to the given room number.
     */
    public VisitableNPC getNPCFromRoomNumber(int roomNumber) {
        return this.npcMap.get(roomNumber);
    }
}