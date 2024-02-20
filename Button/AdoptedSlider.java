package Button;

import Controller.SettingController;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * AdopterSlider is a parent class of both volume and bright slider
 */
public class AdoptedSlider extends Slider {
    Label label; //label of the slider
    SettingController settingController; //controller of the slider

    /**
     * AdoptedSlider Constructor
     * @param label label for the slider
     * @param settingController controller for the slider
     */
    public AdoptedSlider(Label label, SettingController settingController)
    {
        this.settingController = settingController; // get the controller
        //set up label
        this.label = label;
        this.setMin(0);
        this.setMax(100);
        this.setPrefSize(300, 50);
        this.setLayoutX(200);
        this.setLayoutY(200);
        this.setBlockIncrement(1);
        this.label.setLayoutX(500);
        this.label.setLayoutY(202);


    }


}
