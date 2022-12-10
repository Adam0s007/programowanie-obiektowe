package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;
    private ImageView imageView;
    private Label label;
    private VBox box = new VBox(-5);  // Uzyj getera nie protected aby nie można było zepsuć wartości

    public VBox getBox(){
        return this.box;
    }

    public GuiElementBox(IMapElement element) {
        try {
            this.image = new Image(new FileInputStream(element.getLinkToImage()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e + "Nie znaleziono pliku ze zdjęciem");
        }
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(35);
        this.imageView.setFitHeight(35);
        if(element.toString().equals("*")){
            this.label = new Label("Trawa");
        }
        else{
            this.label = new Label("Z " + element.getPosition().toString());
        }

        box.getChildren().addAll((Node) this.imageView, this.label);
        box.setPadding(new Insets(1, 0, 1, 4));
    }
}