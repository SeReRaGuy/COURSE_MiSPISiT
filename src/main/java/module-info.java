module com.example.course {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires java.naming;
    requires org.apache.poi.ooxml;
    requires java.desktop;


    opens com.example.course to javafx.fxml;
    exports com.example.course;

    opens com.example.course.Controllers to javafx.fxml;
    exports com.example.course.Controllers;

    opens com.example.course.Models to javafx.fxml;
    exports com.example.course.Models;

}