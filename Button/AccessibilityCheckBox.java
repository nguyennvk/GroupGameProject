package Button;

import Controller.SettingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 * AccessibilityCheckBox extends ToggleButton to tell which mode that the game is on
 */
public class AccessibilityCheckBox extends ToggleButton {
    SettingController settingController; // controller that control the value of this class
    Label label; // label to be displayed
    AnchorPane root; // pane that the button is in

    /**
     * AccessibilityCheckBox Constructor
     * @param settingController controller that hold the value of this class
     * @param label label to indicate non-sight mode
     * @param root root that this class is in
     */
        public AccessibilityCheckBox(SettingController settingController, Label label, AnchorPane root){
            //setting the layout and the label of this class
            this.setLayoutX(85);
            this.setLayoutY(380);
            this.settingController = settingController; // get the controller
            this.label = label;
            this.label.setText("Non-sight");
            this.label.setLayoutY(380);
            this.label.setLayoutX(150);
            this.root = root;
            this.root.getChildren().add(this.label);
            this.settingController = settingController;
            // set action for toggle button
            this.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    settingController.changeAccess();
                }
            });
        }
}
