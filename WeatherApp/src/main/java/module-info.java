module fi.tuni.progthree.weatherapp {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.media;
    requires com.google.gson;
    requires java.net.http;
    requires transitive java.desktop;
    exports fi.tuni.prog3.weatherapp;
    opens fi.tuni.prog3.weatherapp;
    requires org.controlsfx.controls;
    requires javafx.graphics;
}
