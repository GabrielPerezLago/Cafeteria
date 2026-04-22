module com.gabriel.cafeteria.cafeteriainterface {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires atlantafx.base;
    requires java.desktop;
    requires java.sql;
    requires org.apache.commons.lang3;
    requires org.kordamp.ikonli.materialdesign2;

    opens com.gabriel.cafeteria.cafeteriainterface to javafx.fxml;
    exports com.gabriel.cafeteria.cafeteriainterface;
    opens  com.gabriel.cafeteria.cafeteriainterface.views to javafx.fxml;
    exports com.gabriel.cafeteria.cafeteriainterface.views;
    opens com.gabriel.cafeteria.cafeteriainterface.layouts to javafx.fxml;
    exports com.gabriel.cafeteria.cafeteriainterface.threads;
}