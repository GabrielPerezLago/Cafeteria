package com.gabriel.cafeteria.cafeteriainterface.utils;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public class ScreenUtils {

    private static final CSS CSS_INSTANCE = CSS.getInstance();

    public Image getImage(Class<?> cls, String uri) {
        return new Image(getClass().getResourceAsStream(uri));
    }

    public Image getImage(Class<?> cls, String uri, double width) {
        return new Image(getClass().getResourceAsStream(uri), width, width, true, true);
    }


    public ImageView renderizeImage(String uri) {
        Image image = new Image(uri, 200, 200, true, true);
        return new ImageView(image);
    }

    public ImageView renderizeImage(String uri, int width) {
        Image image = new Image(uri, width, width, true, true);
        return new ImageView(image);
    }


    public void CSSComposer(Scene scene, String uri) {
        scene.getStylesheets().add(getClass().getResource(uri).toExternalForm());
    }

    public void CSSComposerAll(Scene scene, List<String> cssList) {
        for  (String s : cssList) {
            scene.getStylesheets().add(getClass().getResource(s).toExternalForm());
        }
    }

    public void renderizeStage(Class<?> clss, Event event, String uri) throws IOException {
        Parent root = FXMLLoader.load(clss.getResource(uri));
        Scene scene = new Scene(root, 1366, 768);

        this.CSSComposerAll(scene, CSS_INSTANCE.GET());

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
