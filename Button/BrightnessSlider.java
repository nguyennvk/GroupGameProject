package Button;

import Controller.SettingController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * BrightnessSlider is responsible for the brightness setting of the scene.
 * Changing the slider will change the brightness of the game
 */
public class BrightnessSlider extends AdoptedSlider{
    SettingController settingController; //controller for the brightness
    Label label; //label for brightness

    /**
     * BrightnessSlider Constructor
     * @param label label of the slider
     * @param settingController controller of the slider
     * @param root root that the slider is on
     */
    public BrightnessSlider(Label label, SettingController settingController, AnchorPane root)
    {
        super(label, settingController);
        //get all attributes and set up text
        this.settingController = settingController;
        this.label = label;
        this.setValue(settingController.getVolume());
        this.label.setText(Integer.toString(this.settingController.getVolume()));
        this.setTranslateY(70);
        this.label.setTranslateY(70);
        Label bri = new Label("Brightness:");
        bri.setLayoutX(85);
        bri.setLayoutY(278);
        bri.setStyle("-fx-font-size: 20px;");
        root.getChildren().add(bri);
        //change the value of the slider
        this.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                slide();
            }
        });
    }

    /**
     * method that update the brightness of the scene whenever the slider is changed
     */
    private void slide(){
        this.settingController.setBrightness((int) this.getValue());
        this.settingController.updateBrightness();
        this.label.setText(Integer.toString((int)this.settingController.getBrighness()));
    }
}
