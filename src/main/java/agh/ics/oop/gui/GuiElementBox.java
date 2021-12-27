package agh.ics.oop.gui;
import agh.ics.oop.Animal;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class GuiElementBox {
    VBox vbox= new VBox();
    public GuiElementBox(Image image, Animal animal) {
        ImageView imgview=new ImageView();
        imgview.setImage(image);
        imgview.setFitWidth(20);
        imgview.setFitHeight(20);
        Label position= new Label(animal.getEnergy()+animal.getPos().toString());
        position.setFont(new Font("Serif", 8));
        vbox.getChildren().addAll(imgview,position);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(-7);
    }
}
