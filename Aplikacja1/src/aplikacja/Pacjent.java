/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
import static aplikacja.Login.idPacjenta;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Simon
 */
public class Pacjent extends Table{

    private GridPane grid;
    private Label labelWitaj, labelPacjent, labelInfo1, labelInfo2, labelInfo3;
    private Button btnZarejestruj, btnBadanie, btnSprawdz, btnWyjdz;
    private Scene scene;
    private String cssPath, imie, nazwisko, pesel, czyUbezpieczony, nrPolisy,avatar;
    private Image imgUmBad, imgZar, imgSpr, imgPacjent, imgExit;
    private ImageView viewUmBad, viewZar, viewSpr, viewPacjent;
    int width = 300, height = 300;
    private Statement stat;
    private Wyjatki wyjatek = new Wyjatki();

    Pacjent() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Pacjent.class.getName()).log(Level.SEVERE, null, ex);
        }
        GridPane grid = new GridPane();
        grid.setId("pacjent");
        Login log = new Login();
        Stage subStage = new Stage();
        Stage subStage1 = new Stage();
        Stage subStage2 = new Stage();
        Scene scene = new Scene(grid, width, height);

        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Panel Pacjenta");
        grid.setAlignment(Pos.TOP_LEFT);
        subStage.getIcons().add(new Image("file:C:.\\src\\aplikacja\\icon.png"));
        

        String sql = "SELECT imie, nazwisko, pesel, nrPolisy, czyUbezpieczony, avatar FROM pacjenci WHERE idPacjenta="+ idPacjenta;
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            imie = rs.getString("imie");
            nazwisko = rs.getString("nazwisko");
            pesel = rs.getString("pesel");
            nrPolisy = rs.getString("nrPolisy");
            czyUbezpieczony = rs.getString("czyUbezpieczony");
            avatar = rs.getString("avatar");
            System.out.println(avatar);
            con.close();
        } catch (SQLException ex) {
            wyjatek.badConnection();
        }
        
        imgPacjent = new Image("file:"+avatar);
        Circle r = new Circle(50);
        ImagePattern imgPat = new ImagePattern(imgPacjent);
        r.setFill(imgPat);
        viewPacjent = new ImageView(imgPacjent);
        r.setId("avatar");
        grid.add(r, 1, 1, 1, 4);
        
        labelPacjent = new Label(imie + " " + nazwisko);
        labelPacjent.setId("imie");
        grid.add(labelPacjent, 2, 1);

        labelInfo1 = new Label("PESEL: " + pesel);
        labelInfo1.setId("info");
        grid.add(labelInfo1, 2, 2);

        labelInfo2 = new Label("Polisa: " + nrPolisy);
        labelInfo2.setId("info");
        grid.add(labelInfo2, 2, 3);

        labelInfo3 = new Label("Ubezpieczony: " + czyUbezpieczony);
        labelInfo3.setId("info");
        grid.add(labelInfo3, 2, 4);
        
        imgZar = new Image(Pacjent.class.getResourceAsStream("kalendarz.png"));
        btnZarejestruj = new Button("                            Zarejestruj", new ImageView(imgZar));
        btnZarejestruj.setId("btnPacjent");
        btnZarejestruj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Rejestracja();
            }
        });
        btnZarejestruj.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnZarejestruj, 1, 6, 2, 1);
        grid.setHalignment(btnZarejestruj, HPos.CENTER);
        
        imgUmBad = new Image(Pacjent.class.getResourceAsStream("badanie.png"));
        btnBadanie = new Button("                       Umów Badanie", new ImageView(imgUmBad));
        btnBadanie.setId("btnPacjent");
        btnBadanie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Badanie();
            }

        });
        btnBadanie.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnBadanie, 1, 7, 2, 1);
        grid.setHalignment(btnBadanie, HPos.CENTER);

        imgSpr = new Image(Pacjent.class.getResourceAsStream("sprawdz.png"));
        btnSprawdz = new Button("                  Sprawdź Rejestrację", new ImageView(imgSpr));
        btnSprawdz.setId("btnPacjent");
        btnSprawdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableview = new TableView();
                tableview.setEditable(true);
                buildData("SELECT imie, nazwisko, godzinaOd, data, czySkierowanie FROM pacjenci p, wizytyZaplanowane w, godzinyPrzyjec g WHERE w.idGodziny=g.idGodziny AND p.idPacjenta="+idPacjenta);
                Scene scene1 = new Scene(tableview);
                subStage1.setScene(scene1);
                subStage1.show();
                tableview = new TableView();
                tableview.setEditable(true);
                buildData("SELECT imie, nazwisko, godzinaOd, data, czySkierowanie FROM pacjenci p, badaniaZaplanowane w, godzinyPrzyjec g WHERE w.idGodziny=g.idGodziny AND p.idPacjenta="+idPacjenta);
                Scene scene2 = new Scene(tableview);
                subStage2.setScene(scene2);
                subStage2.show();
            }
        });
        btnSprawdz.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnSprawdz, 1, 8, 2, 1);
        grid.setHalignment(btnSprawdz, HPos.CENTER);

        imgExit = new Image(Pacjent.class.getResourceAsStream("exit.png"));
        btnWyjdz = new Button("                                 Wyjdź", new ImageView(imgExit));
        btnWyjdz.setId("btnPacjent");
        btnWyjdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        btnWyjdz.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnWyjdz, 1, 9, 2, 1);
        grid.setHalignment(btnWyjdz, HPos.CENTER);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
//        private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
//        WritableImage image = new WritableImage(width, height);
//        try {
//            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
//            BufferedImage read = ImageIO.read(bis);
//            image = SwingFXUtils.toFXImage(read, null);
//        } catch (IOException ex) {
//            Logger.getLogger(Pacjent.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return image;
//    }
}
