package com.museum.controller;

import com.museum.model.Booking;
import com.museum.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.util.UUID;

public class BookingController {

    private ObservableList<Room> rooms;
    private ObservableList<Booking> bookings = FXCollections.observableArrayList();
    private TableView<Booking> bookingTable = new TableView<>();

    public BookingController(ObservableList<Room> rooms) {
        this.rooms = rooms;
    }

    public Node getView() {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: #faf8f5;");

        // Elegant title section
        Text title = new Text("Room Bookings");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        title.setStyle("-fx-fill: #2c1810;");
        
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: #c4a882;");
        separator.setPadding(new Insets(0, 0, 20, 0));

        // Refined table styling
        bookingTable.setStyle("-fx-background-color: transparent; " +
                              "-fx-border-color: #e0d5c5; " +
                              "-fx-border-width: 1px;");
        
        bookingTable.setRowFactory(tv -> new TableRow<Booking>() {
            @Override
            protected void updateItem(Booking booking, boolean empty) {
                super.updateItem(booking, empty);
                if (booking == null || empty) {
                    setStyle("");
                } else {
                    setStyle("-fx-background-color: white; -fx-font-family: 'Georgia'; -fx-font-size: 13px;");
                }
            }
        });

        // Sophisticated column headers
        TableColumn<Booking, String> idCol = new TableColumn<>("Booking ID");
        idCol.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getBookingId()));
        idCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        TableColumn<Booking, String> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getRoom().getName()));
        roomCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        TableColumn<Booking, String> visitorCol = new TableColumn<>("Visitor");
        visitorCol.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getVisitorName()));
        visitorCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        TableColumn<Booking, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getDate().toString()));
        dateCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        bookingTable.getColumns().addAll(idCol, roomCol, visitorCol, dateCol);
        bookingTable.setItems(bookings);
        bookingTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Elegant form fields
        ComboBox<Room> roomCombo = new ComboBox<>(rooms);
        roomCombo.setPromptText("Select Room");
        roomCombo.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 8px; -fx-background-radius: 2px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 2px;");
        roomCombo.setCellFactory(lv -> new ListCell<>() {
            protected void updateItem(Room r, boolean empty) {
                super.updateItem(r, empty);
                setText(empty || r == null ? null : r.getName() + " (" + r.getStatus() + ")");
            }
        });
        roomCombo.setButtonCell(new ListCell<>() {
            protected void updateItem(Room r, boolean empty) {
                super.updateItem(r, empty);
                setText(empty || r == null ? null : r.getName());
            }
        });

        TextField visitorField = new TextField();
        visitorField.setPromptText("Visitor Name");
        visitorField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 8px; -fx-background-radius: 2px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 2px;");

        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 8px; -fx-background-radius: 2px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 2px;");

        // Refined buttons
        Button bookBtn = new Button("Book Room");
        bookBtn.setStyle("-fx-background-color: #8B6914; " +
                         "-fx-text-fill: white; " +
                         "-fx-font-family: 'Georgia'; " +
                         "-fx-font-weight: bold; " +
                         "-fx-font-size: 13px; " +
                         "-fx-padding: 8px 25px; " +
                         "-fx-background-radius: 2px; " +
                         "-fx-cursor: hand;");
        bookBtn.setOnMouseEntered(e -> bookBtn.setStyle("-fx-background-color: #9B7924; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8px 25px; -fx-background-radius: 2px; -fx-cursor: hand;"));
        bookBtn.setOnMouseExited(e -> bookBtn.setStyle("-fx-background-color: #8B6914; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8px 25px; -fx-background-radius: 2px; -fx-cursor: hand;"));
        
        bookBtn.setOnAction(e -> {
            Room selected = roomCombo.getValue();
            if (selected == null || visitorField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incomplete Information");
                alert.setHeaderText(null);
                alert.setContentText("Please select a room and enter visitor name.");
                alert.show();
                return;
            }
            if (selected.isBooked()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Room Unavailable");
                alert.setHeaderText(null);
                alert.setContentText("This room is already booked.");
                alert.show();
                return;
            }
            selected.setBooked(true);
            bookings.add(new Booking(UUID.randomUUID().toString().substring(0, 8),
                selected, visitorField.getText(), datePicker.getValue()));
            bookingTable.refresh();
            visitorField.clear();
        });

        Button checkoutBtn = new Button("Check Out");
        checkoutBtn.setStyle("-fx-background-color: #8B4513; " +
                             "-fx-text-fill: white; " +
                             "-fx-font-family: 'Georgia'; " +
                             "-fx-font-weight: bold; " +
                             "-fx-font-size: 13px; " +
                             "-fx-padding: 8px 25px; " +
                             "-fx-background-radius: 2px; " +
                             "-fx-cursor: hand;");
        checkoutBtn.setOnMouseEntered(e -> checkoutBtn.setStyle("-fx-background-color: #9B5523; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8px 25px; -fx-background-radius: 2px; -fx-cursor: hand;"));
        checkoutBtn.setOnMouseExited(e -> checkoutBtn.setStyle("-fx-background-color: #8B4513; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8px 25px; -fx-background-radius: 2px; -fx-cursor: hand;"));
        
        checkoutBtn.setOnAction(e -> {
            Booking selected = bookingTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a booking to check out.");
                alert.show();
                return;
            }
            selected.getRoom().setBooked(false);
            bookings.remove(selected);
            bookingTable.refresh();
        });

        // Elegant form container
        HBox form = new HBox(15, roomCombo, visitorField, datePicker, bookBtn, checkoutBtn);
        form.setPadding(new Insets(20));
        form.setStyle("-fx-background-color: white; " +
                      "-fx-border-color: #e0d5c5; " +
                      "-fx-border-width: 1px; " +
                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 10, 0, 0, 2);");

        roomCombo.setPrefWidth(180);
        visitorField.setPrefWidth(180);
        datePicker.setPrefWidth(140);

        mainLayout.getChildren().addAll(title, separator, bookingTable, form);
        return mainLayout;
    }
}