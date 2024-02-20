package Controller;

import com.sun.speech.freetts.Voice;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

/**
 * SettingController controls all components value of the menu and display the changes
 */
public class SettingController {
    int volume = 50;//initial volume
    int brighness = 50;//initial brightness
    boolean normal = true;//initial normal mode
    ToggleButton c1; //toggle button for normal mode
    ToggleButton c2;//toggle button for non-sight mode
    ArrayList<Pane> panes = new ArrayList<>();//list of all pane
    ArrayList<MediaPlayer> medias = new ArrayList<>();//list of all the media
    ColorAdjust colorAdjust; //color effect

    /**
     * SettingController Constructor
     */
    public SettingController()
    {
        this.colorAdjust = new ColorAdjust();
    }

    /**
     * getter method that get the volume of the game
     * @return returns volumes of the game
     */
    public int getVolume()
    {
        return this.volume;
    }

    /**
     * getter method of the brightness
     * @return returns brightness of the game
     */
    public int getBrighness()
    {
        return this.brighness;
    }

    /**
     * setter method that set the volume
     * @param v new volume
     */
    public void setVolume(int v){
        this.volume = v;
    }

    /**
     * setter method that set the brightness
     * @param v the new brightness
     */
    public void setBrightness(int v){
        this.brighness = v;

    }

    /**
     * method that update the brightness of all panes
     */
    public void updateBrightness()
    {
        this.colorAdjust.setBrightness((double)(this.brighness-50)/50);
        for (Pane p:this.panes)
        {
            p.setEffect(this.colorAdjust);
        }
    }

    /**
     * method that update the media volume
     */
    public void updateVolume()
    {
        for (MediaPlayer m: this.medias)
        {
            m.setVolume((double)this.volume/100);
        }
    }

    /**
     * method that change the mode of the game to non-sight
     */
    public void changeNormal()
    {
        if(!this.normal)
        {
            this.normal = true;
            this.c1.setSelected(this.normal);
            this.c2.setSelected(!this.normal);

        }
        else {
            this.c1.setSelected(true);
        }
    }

    /**
     * method that change the mode of the game to normal
     */
    public void changeAccess()
    {
        if(this.normal)
        {
            c2.setSelected(this.normal);
            c1.setSelected(!this.normal);
            this.normal = false;
        }
        else {
            c2.setSelected(true);
        }
    }

    /**
     * getter method that get the mode of the game
     * @return returns mode of the game
     */
    public boolean getMode()
    {
        return this.normal;
    }

    /**
     * setter method that change the state of 2 toggle button
     * @param t1 first Toggle Button
     * @param t2 second Toggle Button
     */
    public void setToggleButton(ToggleButton t1, ToggleButton t2)
    {
        this.c1 = t1;
        this.c2 = t2;
    }

    /**
     * method that add pane to change the brightness
     * @param pane pane of the game
     */
    public void addPane(Pane pane)
    {
        this.panes.add(pane);
    }

    /**
     * method that add all MediaPlayer to change the volume
     * @param mediaPlayer media that play sound
     */
    public void addMedia(MediaPlayer mediaPlayer)
    {
        this.medias.add(mediaPlayer);
    }

}
