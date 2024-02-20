package Button;

import Controller.SettingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 * NormalCheckBox determine the mode that the game is on
 */
public class NormalCheckBox extends ToggleButton {
    SettingController settingController;//controller of the button
    ToggleButton other;//the other toggle button
    Label label;//label for the button
    AnchorPane root;//root that the button is on

    /**
     * NormalCheckBox Constructor
     * @param settingController controller of the butotn
     * @param label label for the button
     * @param root root of the button
     */
    public NormalCheckBox(SettingController settingController, Label label, AnchorPane root){
        //setup label and get all attribute
        this.setLayoutX(85);
        this.setLayoutY(320);
        this.setSelected(true);
        this.label = label;
        this.label.setText("Normal");
        this.label.setLayoutY(320);
        this.label.setLayoutX(150);
        this.root = root;
        this.root.getChildren().add(this.label);
        this.settingController = settingController;
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingController.changeNormal();
            }
        });

    }

}