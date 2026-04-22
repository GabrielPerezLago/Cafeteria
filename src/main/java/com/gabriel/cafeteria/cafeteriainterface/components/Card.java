package com.gabriel.cafeteria.cafeteriainterface.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;


public class Card extends VBox {

    public Card(String tittle, String estado, String cliente) {
        this.cardComposer(tittle, estado, cliente);
    }

    public Card(String tittle, String estado) {
        cardComposer(tittle, estado, null);
    }

    private void cardComposer(String tittle, String estado, String cliente) {

        FontIcon icon = new  FontIcon(MaterialDesignA.ACCOUNT);
        icon.setIconColor(Color.WHITE);
        icon.getStyleClass().add("icon_size_lg");
        this.getChildren().add(icon);

        Label ttlLabel = new Label(tittle);
        ttlLabel.setId("titulo");
        ttlLabel.getStyleClass().add("def light");
        this.getChildren().add(ttlLabel);

        Label estadoLabel = new Label(estado);
        estadoLabel.setId("estado");
        estadoLabel.getStyleClass().addAll("def " ,"light");
        this.getChildren().add(estadoLabel);

        if (cliente != null) {
            Label  clienteLabel = new Label(cliente);
            clienteLabel.getStyleClass().addAll("def " ,"light");
            this.getChildren().add(clienteLabel);
        }

        this.getStyleClass().add("card");
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20));
        VBox.setVgrow(this, Priority.NEVER);

    }

    public void actualizar(String estado) {
        for (Node node : this.getChildren()) {
            if ("estado".equals(node.getId()) && node instanceof Label) {
                var etdLbl = (Label) node;
                switch (estado) {
                    case "esperando" : this.updateLabel(etdLbl, estado, "");
                    break;
                    case "atendiendo": case "atendido" : this.updateLabel(etdLbl, estado, "warning");
                    break;
                    case "preparando cafe"  : this.updateLabel(etdLbl, estado, "warning");
                    break;
                    case "sirviendo": case "servido" : this.updateLabel(etdLbl, estado, "success");
                    break;
                    case "no servido": this.updateLabel(etdLbl, estado, "danger");
                    break;
                }
            }
        }
    }

    private void updateLabel(Label lbl, String estado, String type) {
        lbl.getStyleClass().clear();
        if (type.equals("danger")) {
            lbl.getStyleClass().add("danger");
        } else if (type.equals("warning")) {
            lbl.getStyleClass().add("warning");
        } else if (type.equals("success")) {
            lbl.getStyleClass().add("success");
        } else  {
            lbl.getStyleClass().add("light");
        }
        lbl.setText(estado);
    }
}
