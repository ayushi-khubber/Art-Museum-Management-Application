package com.museum.controller;

import com.museum.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RoomController {

    private ObservableList<Room> rooms;
    private TableView<Room> table = new TableView<>();

    public RoomController(ObservableList<Room> sharedRooms) {
        this.rooms = sharedRooms;
        if (sharedRooms.isEmpty()) {
            sharedRooms.addAll(
                new Room("R01", "Gallery A", "Impressionism", 30),
                new Room("R02", "Gallery B", "Modern Art", 20),
                new Room("R03", "Sculpture Hall", "Greek Antiquities", 50)
            );
        }
    }

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    public Node getView() {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: #faf8f5;");

        // Header Section
        Text title = new Text("Rooms & Exhibits");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        title.setStyle("-fx-fill: #2c1810;");
        
        // Subtitle
        Text subtitle = new Text("Manage museum rooms, exhibits, and capacities");
        subtitle.setFont(Font.font("Georgia", 14));
        subtitle.setStyle("-fx-fill: #6b4c3a;");
        
        VBox headerBox = new VBox(5, title, subtitle);
        
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: #c4a882;");
        separator.setPadding(new Insets(0, 0, 10, 0));

        // Table Styling
        table.setStyle("-fx-background-color: transparent; " +
                       "-fx-border-color: #e0d5c5; " +
                       "-fx-border-width: 1px;");
        
        table.setRowFactory(tv -> new TableRow<Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (room == null || empty) {
                    setStyle("");
                } else if (room.isBooked()) {
                    setStyle("-fx-background-color: #ffebee; -fx-font-family: 'Georgia'; -fx-font-size: 13px;");
                } else {
                    setStyle("-fx-background-color: white; -fx-font-family: 'Georgia'; -fx-font-size: 13px;");
                }
            }
        });

        // Table Columns with better styling
        TableColumn<Room, String> idCol = new TableColumn<>("Room ID");
        idCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        idCol.setPrefWidth(100);
        idCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3;");

        TableColumn<Room, String> nameCol = new TableColumn<>("Room Name");
        nameCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        nameCol.setPrefWidth(150);
        nameCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3;");

        TableColumn<Room, String> exhibitCol = new TableColumn<>("Exhibit");
        exhibitCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getExhibit()));
        exhibitCol.setPrefWidth(150);
        exhibitCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3;");

        TableColumn<Room, Number> capCol = new TableColumn<>("Capacity");
        capCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCapacity()));
        capCol.setPrefWidth(100);
        capCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3;");

        TableColumn<Room, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));
        statusCol.setPrefWidth(100);
        statusCol.setStyle("-fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #f0ebe3;");
        
        // Custom cell factory for status column to add color
        statusCol.setCellFactory(column -> new TableCell<Room, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if ("Booked".equals(item)) {
                        setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-text-fill: #2e7d32; -fx-font-weight: bold;");
                    }
                }
            }
        });

        table.getColumns().addAll(idCol, nameCol, exhibitCol, capCol, statusCol);
        table.setItems(rooms);
        
        // Make table fill width
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Form Section Title
        Text formTitle = new Text("Add New Room");
        formTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        formTitle.setStyle("-fx-fill: #2c1810;");

        // Form Fields with better styling
        TextField idField = new TextField();
        idField.setPromptText("Room ID (e.g., R04)");
        idField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 3px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 3px;");

        TextField nameField = new TextField();
        nameField.setPromptText("Room Name");
        nameField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 3px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 3px;");

        TextField exhibitField = new TextField();
        exhibitField.setPromptText("Exhibit Name");
        exhibitField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 3px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 3px;");

        TextField capField = new TextField();
        capField.setPromptText("Capacity");
        capField.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 13px; -fx-padding: 10px; -fx-background-radius: 3px; -fx-border-color: #d4c5b0; -fx-border-width: 1px; -fx-border-radius: 3px;");

        // Buttons
        Button addBtn = new Button("Add Room");
        addBtn.setStyle("-fx-background-color: #8B6914; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-family: 'Georgia'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 13px; " +
                        "-fx-padding: 10px 25px; " +
                        "-fx-background-radius: 3px; " +
                        "-fx-cursor: hand;");
        addBtn.setOnMouseEntered(e -> addBtn.setStyle("-fx-background-color: #9B7924; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 25px; -fx-background-radius: 3px; -fx-cursor: hand;"));
        addBtn.setOnMouseExited(e -> addBtn.setStyle("-fx-background-color: #8B6914; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 25px; -fx-background-radius: 3px; -fx-cursor: hand;"));
        
        Button clearBtn = new Button("Clear Fields");
        clearBtn.setStyle("-fx-background-color: #6c757d; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-family: 'Georgia'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 13px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-background-radius: 3px; " +
                        "-fx-cursor: hand;");
        clearBtn.setOnMouseEntered(e -> clearBtn.setStyle("-fx-background-color: #5a6268; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 20px; -fx-background-radius: 3px; -fx-cursor: hand;"));
        clearBtn.setOnMouseExited(e -> clearBtn.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 20px; -fx-background-radius: 3px; -fx-cursor: hand;"));
        
        clearBtn.setOnAction(e -> {
            idField.clear();
            nameField.clear();
            exhibitField.clear();
            capField.clear();
        });

        Button deleteBtn = new Button("Delete Selected");
        deleteBtn.setStyle("-fx-background-color: #c62828; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-family: 'Georgia'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 13px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-background-radius: 3px; " +
                        "-fx-cursor: hand;");
        deleteBtn.setOnMouseEntered(e -> deleteBtn.setStyle("-fx-background-color: #b71c1c; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 20px; -fx-background-radius: 3px; -fx-cursor: hand;"));
        deleteBtn.setOnMouseExited(e -> deleteBtn.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 10px 20px; -fx-background-radius: 3px; -fx-cursor: hand;"));
        
        deleteBtn.setOnAction(e -> {
            Room selectedRoom = table.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirm Deletion");
                confirm.setHeaderText("Delete Room");
                confirm.setContentText("Are you sure you want to delete room " + selectedRoom.getId() + "?");
                
                if (confirm.showAndWait().get() == ButtonType.OK) {
                    rooms.remove(selectedRoom);
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Room deleted successfully!");
                    success.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a room to delete.");
                alert.show();
            }
        });

        addBtn.setOnAction(e -> {
            try {
                // Validation
                if (idField.getText().trim().isEmpty() || 
                    nameField.getText().trim().isEmpty() || 
                    exhibitField.getText().trim().isEmpty() || 
                    capField.getText().trim().isEmpty()) {
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Incomplete Information");
                    alert.setHeaderText(null);
                    alert.setContentText("All fields are required to add a room.");
                    alert.show();
                    return;
                }
                
                // Check for duplicate ID
                boolean duplicate = rooms.stream().anyMatch(room -> 
                    room.getId().equalsIgnoreCase(idField.getText().trim()));
                
                if (duplicate) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Duplicate ID");
                    alert.setHeaderText(null);
                    alert.setContentText("A room with this ID already exists. Please use a unique ID.");
                    alert.show();
                    return;
                }
                
                int capacity = Integer.parseInt(capField.getText().trim());
                if (capacity <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Capacity");
                    alert.setHeaderText(null);
                    alert.setContentText("Capacity must be a positive number.");
                    alert.show();
                    return;
                }
                
                Room r = new Room(
                    idField.getText().trim(), 
                    nameField.getText().trim(),
                    exhibitField.getText().trim(), 
                    capacity
                );
                rooms.add(r);
                
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText(null);
                success.setContentText("Room added successfully!");
                success.show();
                
                // Clear form
                idField.clear(); 
                nameField.clear(); 
                exhibitField.clear(); 
                capField.clear();
                
                // Scroll to new item
                table.scrollTo(r);
                table.getSelectionModel().select(r);
                
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Capacity must be a valid number.");
                alert.show();
            }
        });

        // Form layout
        HBox formFields = new HBox(15, idField, nameField, exhibitField, capField);
        formFields.setAlignment(Pos.CENTER_LEFT);
        
        HBox buttonBox = new HBox(10, addBtn, clearBtn, deleteBtn);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        
        VBox formContent = new VBox(15, formTitle, formFields, buttonBox);
        formContent.setPadding(new Insets(20));
        formContent.setStyle("-fx-background-color: white; " +
                              "-fx-border-color: #e0d5c5; " +
                              "-fx-border-width: 1px; " +
                              "-fx-border-radius: 5px; " +
                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 10, 0, 0, 2);");

        // Set preferred widths
        idField.setPrefWidth(120);
        nameField.setPrefWidth(180);
        exhibitField.setPrefWidth(180);
        capField.setPrefWidth(100);
        
        // Table container with spacing
        VBox tableContainer = new VBox(10, table);
        VBox.setVgrow(table, Priority.ALWAYS);

        // Assemble main layout
        mainLayout.getChildren().addAll(headerBox, separator, tableContainer, formContent);
        VBox.setVgrow(tableContainer, Priority.ALWAYS);
        
        return mainLayout;
    }
}