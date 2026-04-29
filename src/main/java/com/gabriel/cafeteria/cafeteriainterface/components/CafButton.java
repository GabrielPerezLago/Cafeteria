package com.gabriel.cafeteria.cafeteriainterface.components;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CafButton extends Button {

    public CafButton(String text) {
        this.btnComposer(text, null);
    }

    public CafButton(String text, EventHandler<ActionEvent> onClick) {
        this.btnComposer(text, onClick);
    }

    private void btnComposer(String text, EventHandler<ActionEvent> onClick) {
        this.setText(text);
        if (onClick != null) this.setOnAction(onClick);
        this.getStyleClass().add("btn-default");
    }
}
