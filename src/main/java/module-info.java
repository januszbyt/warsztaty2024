module com.mycompany.warsztaty2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    opens com.mycompany.warsztaty2024 to javafx.fxml;
    exports com.mycompany.warsztaty2024;
}
