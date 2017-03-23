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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
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
public class Pacjent {
    
    private GridPane grid;
    private Label labelWitaj, labelPacjent;
    private Label LabUsrLog, LabUsrPass;
    private TextField usrLogF;
    private PasswordField usrPassF;
    private Button btnZarejestruj, btnBadanie, btnSprawdz, btnWyjdz;
    private Scene scene;
    private String cssPath;
    int width = 420, height = 200;


    Pacjent() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);

        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Panel Pacjenta");
        grid.setAlignment(Pos.TOP_LEFT);               
        
        labelWitaj = new Label("Witaj: ");
        grid.add(labelWitaj, 1, 2);
        grid.setHalignment(labelWitaj, HPos.RIGHT);
        
        labelPacjent = new Label("Jan Kowalski");
        grid.add(labelPacjent, 2, 2);

        btnZarejestruj = new Button("Zarejestruj");
        btnZarejestruj.setId("btnPacjent");
        btnZarejestruj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (new String(usrLogF.getText()).equals("admin") && new String(usrPassF.getText()).equals("1234")) {
//                    primaryStage.hide();
                    new Rejestracja();
//                }
//
//                notification.setText("Błędny login lub hasło");
            }

        });
        grid.add(btnZarejestruj, 2, 5);
        grid.setHalignment(btnZarejestruj, HPos.CENTER);
        
        btnBadanie = new Button("Umów Badanie");
        btnBadanie.setId("btnPacjent");
        btnBadanie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (new String(usrLogF.getText()).equals("admin") && new String(usrPassF.getText()).equals("1234")) {
 //                   primaryStage.hide();
                    new Badanie();
//                }
//
//                notification.setText("Błędny login lub hasło");
            }

        });
        grid.add(btnBadanie, 2, 6);
        
        
        btnSprawdz = new Button("Sprawdź Rejestrację");
        btnSprawdz.setId("btnPacjent");
        btnSprawdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (new String(usrLogF.getText()).equals("admin") && new String(usrPassF.getText()).equals("1234")) {
 //                   primaryStage.hide();
                    new Register();
//                }
//
//                notification.setText("Błędny login lub hasło");
            }

        });
         grid.add(btnSprawdz, 3, 5);

        btnWyjdz = new Button("Wyjdź");
        btnWyjdz.setId("btnPacjent");
        btnWyjdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (new String(usrLogF.getText()).equals("admin") && new String(usrPassF.getText()).equals("1234")) {
 //                   primaryStage.hide();
                    new Register();
//                }
//
//                notification.setText("Błędny login lub hasło");
            }

        });
         grid.add(btnWyjdz, 3, 6);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
}
