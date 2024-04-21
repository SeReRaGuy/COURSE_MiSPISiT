module com.example.course {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens com.example.course to javafx.fxml;
    exports com.example.course;
    exports com.example.course.Controllers;
    opens com.example.course.Controllers to javafx.fxml;
}