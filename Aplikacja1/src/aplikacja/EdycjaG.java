/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
import static aplikacja.ConnectionManager.pst;
import static aplikacja.Login.idPacjenta;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 *
 * @author Simon
 */
public class EdycjaG extends Table {

    private GridPane grid;
    private Label labelDni, labelInfo1, labelInfo2, labelSale;
    private Button btnWyjdz, btnZapisz, btnDelete;
    private Scene scene;
    private String cssPath;
    private TextField tfPoczatek, tfKoniec;
    public static int width = 300, height = 330, pn = 1, wt = 2, sr = 3, cz = 4, pt = 5;
    private Statement stat;
    private ComboBox comboSale;

    EdycjaG() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EdycjaG.class.getName()).log(Level.SEVERE, null, ex);
        }
        GridPane grid = new GridPane();
        grid.setId("edycjad");
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);

        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Edycja godzin");
        grid.setAlignment(Pos.TOP_LEFT);
        subStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));

        labelDni = new Label("Godziny:");
        labelDni.setId("godziny");
        grid.add(labelDni, 1, 1);

        labelInfo1 = new Label("Użyj CTRL aby zaznaczyć wiele:");
        labelInfo1.setId("d");
        grid.add(labelInfo1, 1, 2);

        tableview = new TableView();
        tableview.setId("tableGodziny");
        buildData("SELECT godzinaOd, godzinaDo, idGodziny FROM GodzinyPrzyjec");
        tableview.setEditable(true);
     // tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        grid.add(tableview, 1, 3, 2, 4);

        labelSale = new Label("Wybierz salę:");
        labelSale.setId("d");
        grid.add(labelSale, 1, 7);

        comboSale = new ComboBox();
        comboSale.setId("comboSpec");
        comboSale.setValue("Wybierz");
        toObsList("SELECT numer FROM gabinety");
        comboSale.getItems().addAll(lista);
        grid.add(comboSale, 2, 7);

        btnZapisz = new Button("Zapisz");
        btnZapisz.setId("btnPacjent");
        btnZapisz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    pst = con.prepareStatement("INSERT INTO godzinyLekarzy (idGodziny, idGabinetu, idPracownika) VALUES(?,?,?);");
                    pst.setInt(1, tableview.getSelectionModel().getSelectedIndex() + 11);
                    pst.setInt(2, comboSale.getSelectionModel().getSelectedIndex() + 1);
                    pst.setString(3, idPacjenta);
                    pst.executeUpdate();
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EdycjaD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(btnZapisz, 1, 8, 2, 1);
        grid.setHalignment(btnZapisz, HPos.CENTER);

        btnDelete = new Button("Usuń wszystkie");
        btnDelete.setId("btnPacjent");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 try {
                    pst = con.prepareStatement("DELETE FROM godzinyLekarzy WHERE idPracownika=" + idPacjenta);
                    pst.executeUpdate();
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EdycjaD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(btnDelete, 1, 9, 2, 1);
        grid.setHalignment(btnDelete, HPos.CENTER);

        btnWyjdz = new Button("Wyjdź");
        btnWyjdz.setId("btnPacjent");
        btnWyjdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               subStage.close();
            }
        });
        grid.add(btnWyjdz, 1, 10, 2, 1);
        grid.setHalignment(btnWyjdz, HPos.CENTER);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
}
