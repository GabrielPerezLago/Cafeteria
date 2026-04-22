package com.gabriel.cafeteria.cafeteriainterface;

import atlantafx.base.theme.*;
import com.gabriel.cafeteria.cafeteriainterface.utils.CSS;
import com.gabriel.cafeteria.cafeteriainterface.utils.ScreenUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    private static final ScreenUtils UTILS = new ScreenUtils();
    private static final CSS CSS_INSTACE = CSS.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        setUserAgentStylesheet(
                new NordDark().getUserAgentStylesheet()
        );
        Image icon = UTILS.getImage(this.getClass(),"/com/gabriel/cafeteria/cafeteriainterface/assets/icons/cafe_icon.png");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gabriel/cafeteria/cafeteriainterface/layouts/init.layout.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,1366, 768);
        UTILS.CSSComposerAll(scene, CSS_INSTACE.GET() );

        stage.setScene(scene);
        stage.setTitle("Cafeteria Interface");
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
