module org.example.btn_3013 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.btn_3013 to javafx.fxml;
    exports org.example.btn_3013;
}