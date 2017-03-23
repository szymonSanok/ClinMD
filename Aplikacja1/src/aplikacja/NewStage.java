///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package aplikacja;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.HPos;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//import aplikacja.Person;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
///**
// *
// * @author Simon
// */
//class NewStage extends ConnectionManager{
//
//
//    private Button btnChange;
//    private TextField tfId,tfImie, tfNazwisko, tfMiejscowosc, tfWiek;
//    private final ObservableList<Person> data = FXCollections.observableArrayList();
//    
//    NewStage() {
//         con = ConnectionManager.getConnection();
//        try {
//            stat = con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(NewStage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        GridPane grid = new GridPane();
//        Login log = new Login();
//        Stage subStage = new Stage();
//        Scene scene = new Scene(grid, log.width, log.height);
//        MenuBar menu = new MenuBar();
//
//        //
//        Menu file = new Menu("File");
//        Menu exit = new Menu("Exit");
//
//        file.getItems().add(exit);
//        exit.setOnAction(ActionEvent -> Platform.exit());
//        menu.getMenus().addAll(file);
//        grid.add(menu, 0, 0);
//
//        //grid.setPadding(new Insets(25, 25, 25, 25));
//        grid.setHgap(10);
//        grid.setVgap(10);
//        subStage.setTitle("Zalogowano");
//        grid.setAlignment(Pos.TOP_LEFT);
//        //
//                TableView table = new TableView();
//            table.setEditable(true);
//            
//             TableColumn firstNameCol = new TableColumn("ID");
//        firstNameCol.setMinWidth(100);
//        firstNameCol.setCellValueFactory(
//                new PropertyValueFactory<>("numer"));
// 
//        TableColumn lastNameCol = new TableColumn("Imie");
//        lastNameCol.setMinWidth(100);
//        lastNameCol.setCellValueFactory(
//                new PropertyValueFactory<>("imie"));
//            
//            table.setItems(data);
//            table.getColumns().addAll(firstNameCol,lastNameCol);
//            
//            grid.add(table,1,6);
//        
//        
//        
//        tfImie = new TextField("Podaj Imię");
//        grid.add(tfImie, 1, 1);
//        tfNazwisko = new TextField("Podaj Nazwisko");
//        grid.add(tfNazwisko, 1, 2);
//        tfMiejscowosc = new TextField("Podaj Miejscowość");
//        grid.add(tfMiejscowosc, 1, 3);
//        tfWiek = new TextField("Podaj Wiek");
//        grid.add(tfWiek, 1, 4);
//
//        btnChange = new Button("Zapisz");
//        btnChange.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//              
//                try {
//                    
//
//
//                   
//pst = con.prepareStatement("INSERT INTO PRACOWNICY (IMIE, NAZWISKO, MIEJSCOWOSC, WIEK) VALUES(?,?,?,?);");
//pst.setString(1,tfImie.getText());
//pst.setString(2,tfNazwisko.getText());
//pst.setString(3,tfMiejscowosc.getText());
//pst.setString(4,tfWiek.getText());
//pst.executeUpdate();
//pst.close();
//
//                } catch (SQLException ex) {
//                    System.err.println(ex);
//                }
//               
//            }
//
//        });
//        grid.add(btnChange, 2, 4);
//        grid.setHalignment(btnChange, HPos.RIGHT);
//
//        
//
//        //
//        subStage.setScene(scene);
//        subStage.show();
//    }
//    }
