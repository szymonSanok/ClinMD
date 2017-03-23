/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
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
public class Badanie {
    
    private Label labelWybierz, labelSpecjalizacja;
    private Label LabUsrLog, LabUsrPass;
    private TextField usrLogF;
    private PasswordField usrPassF;
    private Button btnZapisz, btnAnuluj, btnReset;
    private ComboBox comboLekarz, comboBadanie;
    private DatePicker kalendarz;
    private Scene scene;
    private String cssPath;
    private TableView tableGodziny;
    private RadioButton radioSkTak, radioSkNie;
    int width = 550, height = 260;


    Badanie() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);
        
        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Badanie");
        grid.setAlignment(Pos.TOP_LEFT);               
        
        labelWybierz = new Label("Wybierz lekarza:");
        grid.add(labelWybierz, 1, 1);
//      grid.setHalignment(labelWybierz, HPos.RIGHT);

        comboLekarz = new ComboBox();
        comboLekarz.setId("comboLekarz");
        comboLekarz.setValue("Wybierz");
        comboLekarz.getItems().addAll(
        "Jan Kowalski","Piotr Nowak","Andrzej Kot"
        );
        grid.add(comboLekarz,2,1);
        grid.setHalignment(comboLekarz, HPos.LEFT);
        
        labelSpecjalizacja = new Label("Wybierz badanie:");
        grid.add(labelSpecjalizacja, 3, 1);

        tableGodziny = new TableView();
        tableGodziny.setId("tableGodziny");
        tableGodziny.setEditable(true);  
        TableColumn firstNameCol = new TableColumn("ID");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("numer"));
        TableColumn lastNameCol = new TableColumn("Imie");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
        tableGodziny.getColumns().addAll(firstNameCol,lastNameCol);
        grid.add(tableGodziny,3,3,2,3);
        
        comboBadanie = new ComboBox();
        comboBadanie.setId("comboBadanie");
        comboBadanie.setValue("Wybierz");
        comboBadanie.getItems().addAll(
        "USG","RTG","Badanie krwi","Rezonans"
        );
        grid.add(comboBadanie,4,1);
        
        kalendarz = new DatePicker(LocalDate.now());
        kalendarz.setId("kalendarz");
        grid.add(kalendarz, 1, 3);
        kalendarz.setShowWeekNumbers(false);
        
        labelWybierz = new Label("Mam skierowanie:");
        grid.add(labelWybierz, 1, 4);
        
        radioSkTak = new RadioButton();
        radioSkTak.setText("Tak");
        grid.add(radioSkTak, 2, 4);
        
        radioSkNie = new RadioButton();
        radioSkNie.setText("Nie");
        grid.add(radioSkNie, 2, 4);
        grid.setHalignment(radioSkNie, HPos.RIGHT);

        btnZapisz = new Button("Zapisz");
        btnZapisz.setId("btnZapisz");
        btnZapisz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (new String(usrLogF.getText()).equals("admin") && new String(usrPassF.getText()).equals("1234")) {
//                    primaryStage.hide();
                    new NewStage();
//                }
//
//                notification.setText("Błędny login lub hasło");
            }

        });
        grid.add(btnZapisz, 1, 7);
        
        
        btnAnuluj = new Button("Anuluj");
        btnAnuluj.setId("btnAnuluj");
        btnAnuluj.setOnAction(new EventHandler<ActionEvent>() {
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
        grid.add(btnAnuluj, 2,7);
        
        
        btnReset = new Button("Reset");
        btnReset.setId("btnReset");
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
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
         grid.add(btnReset, 3,7);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
}
