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
class Forget extends ConnectionManager{

    Login log = new Login();
    private Button btnPokaz, btnOk;
    private Label labelHaslo;
    private String cssPath;
    private TextField tfPesel, tfTelefon;
    int width = 280, height = 300;
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    
    Forget() {
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
        
        
        tfPesel = new TextField("PESEL");
        tfPesel.setId("textSize");
        grid.add(tfPesel, 1, 4);

        tfTelefon = new TextField("Telefon");
        tfTelefon.setId("textSize");
        grid.add(tfTelefon, 1, 5);
        

        btnPokaz = new Button("Pokaż Hasło");
        btnPokaz.setId("btnPokaz");
        grid.add(btnPokaz, 1, 6);
        grid.setHalignment(btnPokaz, HPos.CENTER);
        
        labelHaslo = new Label("Twoje Hasło!");
        grid.add(labelHaslo, 1, 8);
        grid.setHalignment(labelHaslo, HPos.CENTER);

        btnOk = new Button("OK");
        btnOk.setId("btnOk");
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    subStage.hide();
            }

        });
        grid.add(btnOk, 1, 10);
        grid.setHalignment(btnOk, HPos.CENTER);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
    }