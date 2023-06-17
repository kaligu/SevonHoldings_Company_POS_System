module lk.sevonholdings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.lang3;

    opens lk.sevonholdings.view.tm to javafx.base;
    opens lk.sevonholdings.dto to javafx.base;


    opens lk.sevonholdings.controllers to javafx.fxml;
    opens lk.sevonholdings.controllers.managerwindow to javafx.fxml;
    opens lk.sevonholdings.controllers.managerwindow.forms to javafx.fxml;
    exports lk.sevonholdings;
}