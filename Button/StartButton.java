package Button;

import AdventureModel.Setting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import views.MenuView;

import java.io.IOException;

/**
 * StartButton will start the game when clicked
 */
public class StartButton extends GameButton{
    ImageView imageView; //image of the button
    ImageView imageViewHover;//image of the button while hovering
    public StartButton(MenuView menuView, Setting setting)
    {
        super(setting);
        // get all the attribute and put them in root
        this.imageView = new ImageView(new Image("Button/Button.png"));
        this.imageView.setFitWidth(this.setting.buttonWidth);
        this.imageView.setFitHeight(this.setting.buttonHeight);
        this.imageViewHover = new ImageView(new Image("Button/Button_hover.png"));
        this.imageViewHover.setFitHeight(this.setting.buttonHeight);
        this.imageViewHover.setFitWidth(this.setting.buttonWidth);
        this.setGraphic(this.imageView);
        this.setStyle("-fx-background-color: transparent;");
        this.makeButtonAccessible("Start Button", "Click this button to start");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    menuView.getAdventureGameView().intiUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.setTranslateY(100);
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                enterHover();
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitHover();
            }
        });
    }

    /**
     * method that change the button image while hovering
     */
    private void enterHover()
    {
        this.setGraphic(this.imageViewHover);
    }

    /**
     * method that reset the button image
     */
    private void exitHover()
    {
        this.setGraphic(this.imageView);
    }

}
