package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;
    private ImageView imageView;
    private Label label;
    private VBox box = new VBox(-5);

    public VBox getBox(){
        return this.box;
    }

    public GuiElementBox(IMapElement element) {
        try {
            this.image = new Image(new FileInputStream(element.getLinkToImage()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e + "Nie odnaleziono zdjecia");
        }
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(35);
        this.imageView.setFitHeight(35);
        if(element.toString().equals("*")){
            this.label = new Label("Trawa");
            label.setStyle("-fx-text-fill: rgba(0, 100, 0, 1);-fx-font-weight: bold;");
            label.setTranslateY(10);
        }
        else{
            this.label = new Label("Z " + element.getPosition().toString());
            label.setStyle("-fx-text-fill: rgba(128, 50, 0, 1);-fx-font-weight: bold;");
            label.setTranslateY(10);
        }

        StackPane stack = new StackPane(this.imageView,label);
        box.getChildren().addAll((Node) stack);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(1, 0, 1, 4));
    }
}