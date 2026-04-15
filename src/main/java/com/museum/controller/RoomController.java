package com.museum.controller;

import com.museum.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RoomController {

    private ObservableList<Room> rooms = FXCollections.observableArrayList();
    private TableView<Room> table = new TableView<>();

    public RoomController() {
        rooms.add(new Room("R01", "Gallery A", "Impressionism", 30));
        rooms.add(new Room("R02", "Gallery B", "Modern Art", 20));
        rooms.add(new Room("R03", "Sculpture Hall", "Greek Antiquities", 50));
    }

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    public Node getView() {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: #faf8f5;");

        // Elegant title section
        Text title = new Text("Rooms & Exhibits");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        title.setStyle("-fx-fill: #2c1810;");
        
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: #c4a882;");
        separator.setPadding(new Insets(0, 0, 20, 0));

        // Refined table styling
        table.setStyle("-fx-background-color: transparent; " +
                       "-fx-border-color: #e0d5c5; " +
                       "-fx-border-width: 1px;");
        
        table.setRowFactory(tv -> new TableRow<Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (room == null || empty) {
                    setStyle("");
                } else {
                    setStyle("-fx-background-color: white; -fx-font-family: 'Georgia'; -fx-font-size: 13px;");
                }
            }
        });

        // Sophisticated column headers
        TableColumn<Room, String> idCol = new TableColumn<>("Room ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        TableColumn<Room, String> nameCol = new TableColumn<>("Room Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        TableColumn<Room, String> exhibitCol = new TableColumn<>("Exhibit");
        exhibitCol.setCellValueFactory(new PropertyValueFactory<>("exhibit"));
        exhibitCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        TableColumn<Room, Integer> capCol = new TableColumn<>("Capacity");
        capCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        capCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        TableColumn<Room, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3; -fx-border-color: #e0d5c5; -fx-border-width: 0 0 1px 0;");

        table.getColumns().addAll(idCol, nameCol, exhibitCol, capCol, statusCol);
        table.setItems(rooms);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Elegant form fields with Georgia font
        TextField idField = new TextField();
        idField.setPromptText("Room ID");
        idField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 2px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 2px;");

        TextField nameField = new TextField();
        nameField.setPromptText("Room Name");
        nameField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 2px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 2px;");

        TextField exhibitField = new TextField();
        exhibitField.setPromptText("Exhibit");
        exhibitField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 2px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 2px;");

        TextField capField = new TextField();
        capField.setPromptText("Capacity");
        capField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 2px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 2px;");

        // Refined Add Room button
        Button addBtn = new Button("Add Room");
        addBtn.setStyle("-fx-background-color: #8B6914; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-family: 'Georgia'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 13px; " +
                        "-fx-padding: 10px 25px; " +
                        "-fx-background-radius: 2px; " +
                        "-fx-cursor: hand;");
        addBtn.setOnMouseEntered(e -> addBtn.setStyle("-fx-background-color: #9B7924; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 25px; -fx-background-radius: 2px; -fx-cursor: hand;"));
        addBtn.setOnMouseExited(e -> addBtn.setStyle("-fx-background-color: #8B6914; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 25px; -fx-background-radius: 2px; -fx-cursor: hand;"));
        
        addBtn.setOnAction(e -> {
            try {
                if (idField.getText().isEmpty() || nameField.getText().isEmpty() || 
                    exhibitField.getText().isEmpty() || capField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Incomplete Information");
                    alert.setHeaderText(null);
                    alert.setContentText("All fields are required to add a room.");
                    alert.show();
                    return;
                }
                
                Room r = new Room(
                    idField.getText(), 
                    nameField.getText(),
                    exhibitField.getText(), 
                    Integer.parseInt(capField.getText())
                );
                rooms.add(r);
                
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.setContentText("Room added successfully!");
                success.show();
                
                idField.clear(); 
                nameField.clear(); 
                exhibitField.clear(); 
                capField.clear();
                table.refresh();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Capacity must be a valid number.");
                alert.show();
            }
        });

        // Elegant form container
        HBox form = new HBox(15, idField, nameField, exhibitField, capField, addBtn);
        form.setPadding(new Insets(20));
        form.setStyle("-fx-background-color: white; " +
                      "-fx-border-color: #e0d5c5; " +
                      "-fx-border-width: 1px; " +
                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 10, 0, 0, 2);");

        idField.setPrefWidth(100);
        nameField.setPrefWidth(150);
        exhibitField.setPrefWidth(150);
        capField.setPrefWidth(100);

        mainLayout.getChildren().addAll(title, separator, table, form);
        return mainLayout;
    }
}