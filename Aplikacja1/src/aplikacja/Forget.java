/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Simon
 */
class Forget extends ConnectionManager {

    Login log = new Login();
    private Button btnPokaz, btnOk;
    private Label labelHaslo, labelHasloW, labPesel, labTel;
    private String cssPath;
    private TextField tfPesel, tfTelefon;
    private String haslo;
    int width = 280, height = 300;
    private Wyjatki wyjatek = new Wyjatki();

    Forget() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            wyjatek.badConnection();
        }
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);
        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Przypomnij hasło");
        grid.setAlignment(Pos.TOP_LEFT);
        subStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));

        labPesel = new Label("PESEL: ");
        grid.add(labPesel, 1, 4);
        grid.setHalignment(labPesel, HPos.LEFT);

        tfPesel = new TextField(null);
        tfPesel.setId("textSize");
        grid.add(tfPesel, 2, 4);

        labTel = new Label("Telefon: ");
        grid.add(labTel, 1, 5);
        grid.setHalignment(labTel, HPos.LEFT);
        tfTelefon = new TextField(null);
        tfTelefon.setId("textSize");
        grid.add(tfTelefon, 2, 5);

        btnPokaz = new Button("Pokaż Hasło");
        btnPokaz.setId("btnLogin");
        btnPokaz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sql = "SELECT haslo FROM pacjenci WHERE pesel='" + tfPesel.getText() + "' AND telefon='"+ tfTelefon.getText() +"'";
                try {
                    ResultSet rs = con.createStatement().executeQuery(sql);
                    haslo = rs.getString("haslo");
                    labelHasloW = new Label("Hasło: " + haslo);
                    grid.add(labelHasloW, 2, 9);
                    grid.setHalignment(labelHasloW, HPos.CENTER);
                    con.close();
                } catch (SQLException ex) {
                    wyjatek.forget();
                }

            }

        });
        grid.add(btnPokaz, 2, 6);
        grid.setHalignment(btnPokaz, HPos.CENTER);

        labelHaslo = new Label("Twoje Hasło:");
        grid.add(labelHaslo, 2, 8);
        grid.setHalignment(labelHaslo, HPos.CENTER);

        btnOk = new Button("OK");
        btnOk.setId("btnLogin");
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subStage.hide();
            }

        });
        grid.add(btnOk, 2, 10);
        grid.setHalignment(btnOk, HPos.CENTER);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }
}
