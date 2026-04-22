package com.gabriel.cafeteria.cafeteriainterface.views;

import com.gabriel.cafeteria.cafeteriainterface.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class InicioView {
    private static final ScreenUtils UTILS = new ScreenUtils();

    @FXML
    private ImageView imgViewInicio;

    @FXML
    private Button btnIniciar;

    @FXML
    public void initialize(){

        imgViewInicio.setImage(UTILS.getImage(this.getClass(), "/com/gabriel/cafeteria/cafeteriainterface/assets/icons/cafe_icon_lg.png", 300));

        btnIniciar.setOnAction(event -> {
            try {
                UTILS.renderizeStage(InicioView.class, event, "/com/gabriel/cafeteria/cafeteriainterface/layouts/cafeteria.layout.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
