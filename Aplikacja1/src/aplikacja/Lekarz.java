/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
import static aplikacja.Login.idPacjenta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Simon
 */
public class Lekarz extends Table {

    private GridPane grid;
    private Label labelWitaj, labelInfo1, labelInfo2;
    private Button btnLpac, btnLbadanie, btnSprawdz, btnWyjdz, btnEdycjaD, btnEdycjaG;
    private Scene scene;
    private TableView tablePacjenci, tableBadania;
    private String cssPath, imie, nazwisko, telefon, specjalizacja;
    int width = 300, height = 400;
    private Statement stat;
    private Image imgDni, imgGodz, imgHist, imgWyj, imgBad, imgPac, imgLekarz;
    private ImageView viewUmBad, viewZar, viewSpr, viewLekarz;

    Lekarz() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Lekarz.class.getName()).log(Level.SEVERE, null, ex);
        }
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Stage subStage1 = new Stage();
        Scene scene = new Scene(grid, width, height);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setId("pacjent");
        subStage.setTitle("Panel Lekarza");
        grid.setAlignment(Pos.TOP_LEFT);
        subStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));
        
        String sql = "SELECT imie, nazwisko, telefon, Specjalizacje.nazwa FROM personelMedyczny, specjalizacje WHERE personelMedyczny.idPracownika=" + idPacjenta;
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            imie = rs.getString("imie");
            nazwisko = rs.getString("nazwisko");
            telefon = rs.getString("telefon");
            specjalizacja = rs.getString("nazwa");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
        }

        imgLekarz = new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\lekarz.png");
        Circle r = new Circle(50);
        ImagePattern imgPat = new ImagePattern(imgLekarz);
        r.setFill(imgPat);
        viewLekarz = new ImageView(imgLekarz);
        r.setId("avatar");
        grid.add(r, 1, 1, 1, 3);

        labelWitaj = new Label(imie + " " + nazwisko);
        labelWitaj.setId("imie");
        grid.add(labelWitaj, 2, 1);

        labelInfo1 = new Label("Telefon: " + telefon);
        labelInfo1.setId("info");
        grid.add(labelInfo1, 2, 2);

        labelInfo2 = new Label("Specjalizacja: " + specjalizacja);
        labelInfo2.setId("info");
        grid.add(labelInfo2, 2, 3);

        imgPac = new Image(Pacjent.class.getResourceAsStream("pacjenci.png"));
        btnLpac = new Button("                   Pokaż listę pacjentów", new ImageView(imgPac));
        btnLpac.setId("btnPacjent");
        btnLpac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableview = new TableView();
                tableview.setEditable(true);
                buildData("SELECT imie, nazwisko, godzinaOd, data, czySkierowanie FROM pacjenci p, wizytyZaplanowane w, godzinyPrzyjec g WHERE w.idGodziny=g.idGodziny AND p.idPacjenta=w.idPacjenta");
                Scene scene1 = new Scene(tableview);
                subStage1.setScene(scene1);
                subStage1.show();
            }
        });
        btnLpac.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnLpac, 1, 5, 2, 1);

        imgBad = new Image(Pacjent.class.getResourceAsStream("badanie.png"));
        btnLbadanie = new Button("                     Pokaż listę badań", new ImageView(imgBad));
        btnLbadanie.setId("btnPacjent");
        btnLbadanie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableview = new TableView();
                tableview.setEditable(true);
                buildData("SELECT imie, nazwisko, godzinaOd, data, czySkierowanie FROM pacjenci p, badaniaZaplanowane w, godzinyPrzyjec g WHERE w.idGodziny=g.idGodziny AND p.idPacjenta=w.idPacjenta");
                Scene scene1 = new Scene(tableview);
                subStage1.setScene(scene1);
                subStage1.show();
            }

        });
        btnLbadanie.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnLbadanie, 1, 6, 2, 1);

        imgHist = new Image(Pacjent.class.getResourceAsStream("historia.png"));
        btnSprawdz = new Button("                      Historia pacjenta", new ImageView(imgHist));
        btnSprawdz.setId("btnPacjent");
        btnSprawdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Historia();
            }

        });
        btnSprawdz.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnSprawdz, 1, 7, 2, 1);

        imgDni = new Image(Pacjent.class.getResourceAsStream("kalendarz.png"));
        btnEdycjaD = new Button("                     Zmień dni przyjęć", new ImageView(imgDni));
        btnEdycjaD.setId("btnPacjent");
        btnEdycjaD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new EdycjaD();
            }
        });
        btnEdycjaD.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnEdycjaD, 1, 8, 2, 1);

        imgGodz = new Image(Pacjent.class.getResourceAsStream("clock.png"));
        btnEdycjaG = new Button("                  Zmień godziny przyjęć", new ImageView(imgGodz));
        btnEdycjaG.setId("btnPacjent");
        btnEdycjaG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new EdycjaG();
            }

        });
        btnEdycjaG.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnEdycjaG, 1, 9, 2, 1);

        imgWyj = new Image(Pacjent.class.getResourceAsStream("exit.png"));
        btnWyjdz = new Button("                            Wyjdź", new ImageView(imgWyj));
        btnWyjdz.setId("btnPacjent");
        btnWyjdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }

        });
        btnWyjdz.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnWyjdz, 1, 10, 2, 1);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
}
