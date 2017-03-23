/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Simon
 */
class Register extends ConnectionManager{


    private Button btnChange, btnReset;
    private RadioButton radioTak, radioNie;
    private Label labelUbezp;
    private String cssPath;
    private TextField tfImie, tfNazwisko, tfMiejscowosc, tfPesel, tfUlica, tfMiasto, tfKodPocz, tfWojew, tfTelefon, tfNrPolisy;
    int width = 280, height = 500;
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    
    Register() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
         con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(NewStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);
        MenuBar menu = new MenuBar();

        //
        Menu file = new Menu("File");
        Menu exit = new Menu("Exit");

        file.getItems().add(exit);
        exit.setOnAction(ActionEvent -> Platform.exit());
        menu.getMenus().addAll(file);
        grid.add(menu, 0, 0);

        //grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Stwórz konto");
        grid.setAlignment(Pos.TOP_LEFT);
        //
        
        
        
        tfImie = new TextField("Imię");
        tfImie.setId("textSize");
        grid.add(tfImie, 1, 1);
        
        tfNazwisko = new TextField("Nazwisko");
        tfNazwisko.setId("textSize");
        grid.add(tfNazwisko, 1, 2);
        
        tfPesel = new TextField("PESEL");
        tfPesel.setId("textSize");
        grid.add(tfPesel, 1, 3);
        
        tfUlica = new TextField("Ulica");
        tfUlica.setId("textSize");
        grid.add(tfUlica, 1, 4);
        
        tfMiasto = new TextField("Miasto");
        tfMiasto.setId("textSize");
        grid.add(tfMiasto, 1, 5);
        
        tfKodPocz = new TextField("Miasto");
        tfKodPocz.setId("textSize");
        grid.add(tfKodPocz, 1, 6);
        
        tfWojew = new TextField("Województwo");
        tfWojew.setId("textSize");
        grid.add(tfWojew, 1, 7);
        
        tfTelefon = new TextField("Telefon");
        tfTelefon.setId("textSize");
        grid.add(tfTelefon, 1, 8);
        
        labelUbezp = new Label("Czy ubezpieczony:");
        grid.add(labelUbezp, 1, 9);
        
        radioTak = new RadioButton("Tak");
        grid.add(radioTak, 1, 10);
        
        radioNie = new RadioButton("Nie");
        grid.add(radioNie, 1, 10);
        grid.setHalignment(radioNie, HPos.RIGHT);
        
        tfNrPolisy = new TextField("Numer Polisy");
        tfNrPolisy.setId("textSize");
        grid.add(tfNrPolisy, 1, 11);


        btnChange = new Button("Zapisz");
        btnChange.setId("btnChange");
        btnChange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                try {
                    


                   
pst = con.prepareStatement("INSERT INTO PRACOWNICY (IMIE, NAZWISKO, MIEJSCOWOSC, WIEK) VALUES(?,?,?,?);");
pst.setString(1,tfImie.getText());
pst.setString(2,tfNazwisko.getText());
pst.setString(3,tfMiejscowosc.getText());
pst.setString(4,tfPesel.getText());
pst.executeUpdate();
pst.close();

                } catch (SQLException ex) {
                    System.err.println(ex);
                }
               
            }

        });
        grid.add(btnChange, 1, 12);
        grid.setHalignment(btnChange, HPos.RIGHT);

        btnReset = new Button("Wyczyść");
        btnReset.setId("btnReset");
        grid.add(btnReset, 1, 12);
        grid.setHalignment(btnReset, HPos.LEFT);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
    }