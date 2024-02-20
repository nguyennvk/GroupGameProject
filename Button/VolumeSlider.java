package Button;

import Controller.SettingController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * VolumeSlider allow the volume of the game to be changed when slide
 */
public class VolumeSlider extends AdoptedSlider{
    SettingController settingController;//controller for the slider
    Label label;//label of the slider

    /**
     * VolumeSlider Constructor
     * @param label label of the slider
     * @param settingController controller of the slider
     * @param root root that the slider is on
     */
    public VolumeSlider(Label label, SettingController settingController, AnchorPane root)
    {
        super(label, settingController);
        // get all attribute and set up
        this.settingController = settingController;
        this.label = label;
        this.setValue(settingController.getVolume());
        this.label.setText(Integer.toString(this.settingController.getVolume()));
        Label bri = new Label("Volume:");
        bri.setLayoutX(85);
        bri.setLayoutY(208);
        bri.setStyle("-fx-font-size: 20px;");
        root.getChildren().add(bri);
        this.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                slide();
            }
        });

    }

    /**
     * method that change the volume whenever the slider is changed
     */
    private void slide(){
        this.settingController.setVolume((int) this.getValue());
        this.label.setText(Integer.toString((int)this.settingController.getVolume()));
        this.settingController.updateVolume();
    }
}
