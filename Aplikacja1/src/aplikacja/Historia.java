/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
import static aplikacja.ConnectionManager.pst;
import static aplikacja.EdycjaD.*;
import static aplikacja.Login.idPacjenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Simon
 */
public class Historia extends Table {

    private Label labelWybierz, labelDodaj, labelInfo, labelChoroba;
    private TextField tfChoroba;
    private TextArea taInfo;
    private Button btnZapisz, btnAnuluj, btnChoroba;
    private ComboBox comboPacjent, comboChoroba;
    private DatePicker kalendarz;
    private Scene scene;
    private String cssPath, info, stare;
    int width = 530, height = 300, idPacjenta, idChoroby;

    Historia() {
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Historia.class.getName()).log(Level.SEVERE, null, ex);
        }
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);
        subStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));

        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Historia pacjenta");
        grid.setAlignment(Pos.TOP_LEFT);

        labelWybierz = new Label("Wybierz pacjenta:");
        grid.add(labelWybierz, 1, 1);
//      grid.setHalignment(labelWybierz, HPos.RIGHT);

        comboPacjent = new ComboBox();
        comboPacjent.setId("comboPacjent");
        comboPacjent.setValue("Wybierz");
        toObsList("SELECT imie,nazwisko FROM pacjenci");
        comboPacjent.getItems().addAll(lista);
        grid.add(comboPacjent, 2, 1);
        grid.setHalignment(comboPacjent, HPos.LEFT);

        comboPacjent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                idPacjenta = (comboPacjent.getSelectionModel().getSelectedIndex() + 1);
                String sql = "SELECT informacje, idRozpChor FROM rozpoznaneChoroby WHERE idPacjenta=" + idPacjenta;
                try {
                    con = ConnectionManager.getConnection();
                    ResultSet rs = con.createStatement().executeQuery(sql);
                    stare = rs.getString("informacje");
                    idChoroby = rs.getInt("idRozpChor");
                    con.close();
                    taInfo.setText(stare);
                    System.out.println(stare);
                } catch (SQLException ex) {
                    taInfo.setText("Nowy pacjent");;
                }
            }

        });

        labelChoroba = new Label("Choroba:");
        grid.add(labelChoroba, 1, 2);

        comboChoroba = new ComboBox();
        comboChoroba.setId("comboChoroba");
        comboChoroba.setValue("Wybierz");
        toObsList("SELECT nazwa FROM choroby");
        comboChoroba.getItems().addAll(lista);
        grid.add(comboChoroba, 2, 2);
        grid.setHalignment(comboChoroba, HPos.LEFT);

        labelInfo = new Label("Dodatkowe informacje:");
        grid.add(labelInfo, 3, 1);

        taInfo = new TextArea();
        taInfo.setId("taInfo");
        taInfo.setMinHeight(200);
        taInfo.setMaxWidth(250);
        grid.add(taInfo, 3, 2, 1, 6);

        labelDodaj = new Label("Dodaj chorobę:");
        grid.add(labelDodaj, 1, 3);

        tfChoroba = new TextField(null);
        tfChoroba.setId("textSize");
        grid.add(tfChoroba, 2, 3);

        btnChoroba = new Button("Dodaj");
        btnChoroba.setId("btnChoroba");
        btnChoroba.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    con = ConnectionManager.getConnection();
                    pst = con.prepareStatement("INSERT INTO choroby (nazwa) VALUES(?);");
                    pst.setString(1, tfChoroba.getText());
                    pst.executeUpdate();
                    pst.close();
                    System.out.println("Zapisano");
                    comboChoroba.getItems().removeAll(lista);
                    toObsList("SELECT nazwa FROM choroby");
                    comboChoroba.getItems().addAll(lista);
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        });
        grid.add(btnChoroba, 2, 4);

        kalendarz = new DatePicker(LocalDate.now());
        kalendarz.setId("kalendarz");
        grid.add(kalendarz, 1, 5);
        kalendarz.setShowWeekNumbers(false);

        btnZapisz = new Button("Zapisz");
        btnZapisz.setId("btnZapisz");
        btnZapisz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                info = taInfo.getText();
                try {
                    con = ConnectionManager.getConnection();
                    pst = con.prepareStatement("INSERT INTO rozpoznaneChoroby (idPacjenta, data, idChoroby) VALUES(?,?,?);");
                    pst.setInt(1, comboPacjent.getSelectionModel().getSelectedIndex() + 1);
                    pst.setString(2, kalendarz.getValue().toString());
                    pst.setInt(3, comboChoroba.getSelectionModel().getSelectedIndex() + 1);
                    pst.executeUpdate();
                    pst.close();
                    con = ConnectionManager.getConnection();
                    pst= con.prepareStatement("UPDATE rozpoznaneChoroby SET informacje=? WHERE idRozpChor=?;");
                    pst.setString(1, info);
                    pst.setInt(2, idChoroby);
                    pst.executeUpdate();
                    pst.close();
                    System.out.println("Zapisano");
                    subStage.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        });
        grid.add(btnZapisz, 1, 7);

        btnAnuluj = new Button("Anuluj");
        btnAnuluj.setId("btnAnuluj");
        btnAnuluj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subStage.close();
            }

        });

        grid.add(btnAnuluj, 2, 7);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }

}
