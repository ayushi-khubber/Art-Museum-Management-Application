package com.museum;

import com.museum.controller.BookingController;
import com.museum.controller.RoomController;
import com.museum.model.Room;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        ObservableList<Room> sharedRooms = FXCollections.observableArrayList();
        
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-background-color: #faf8f5;");

        RoomController roomCtrl = new RoomController(sharedRooms);
        BookingController bookingCtrl = new BookingController(sharedRooms);

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