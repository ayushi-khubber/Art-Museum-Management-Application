package com.museum;

import com.museum.controller.BookingController;
import com.museum.controller.RoomController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-background-color: #faf8f5;");

        RoomController roomCtrl = new RoomController();
        BookingController bookingCtrl = new BookingController(roomCtrl.getRooms());

        Tab roomsTab = new Tab("Rooms & Exhibits", roomCtrl.getView());
        Tab bookingTab = new Tab("Book a Room", bookingCtrl.getView());

        tabPane.getTabs().addAll(roomsTab, bookingTab);

        Scene scene = new Scene(tabPane, 950, 650);
        stage.setTitle("Art Museum Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}