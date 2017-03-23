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
import javafx.application.Platform;
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
import javafx.scene.image.Image;

/**
 *
 * @author Simon
 */
public class EdycjaD {

    private GridPane grid;
    private Label labelDni, labelInfo1, labelInfo2, labelInfo3, labelInfo4, labelInfo5;
    private Button btnWyjdz, btnZapisz;
    private Scene scene;
    private String cssPath;
    private CheckBox chPn, chWt, chSr, chCz, chPt;
    public static int width = 300, height = 250, pn=1, wt=2, sr=3, cz=4, pt=5;
    private Statement stat;

    EdycjaD() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EdycjaD.class.getName()).log(Level.SEVERE, null, ex);
        }
        GridPane grid = new GridPane();
        grid.setId("edycjad");
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);

        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Wybór dni");
        grid.setAlignment(Pos.TOP_LEFT);
        subStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));

        labelDni = new Label("Dni tygodnia:");
        labelDni.setId("dni");
        grid.add(labelDni, 1, 1);

        labelInfo1 = new Label("Poniedziałek");
        labelInfo1.setId("d");
        grid.add(labelInfo1, 1, 2);

        labelInfo2 = new Label("Wtorek");
        labelInfo2.setId("d");
        grid.add(labelInfo2, 1, 3);

        labelInfo3 = new Label("Środa");
        labelInfo3.setId("d");
        grid.add(labelInfo3, 1, 4);
        
        labelInfo4 = new Label("Czwartek");
        labelInfo4.setId("d");
        grid.add(labelInfo4, 1, 5);
        
        labelInfo5 = new Label("Piątek");
        labelInfo5.setId("d");
        grid.add(labelInfo5, 1, 6);
        
        chPn = new CheckBox();
        grid.add(chPn, 2, 2);
        
        chWt = new CheckBox();
        grid.add(chWt, 2, 3);
        
        chSr = new CheckBox();
        grid.add(chSr, 2, 4);
        
        chCz = new CheckBox();
        grid.add(chCz, 2, 5);
        
        chPt = new CheckBox();
        grid.add(chPt, 2, 6);

        btnZapisz = new Button("Zapisz");
        btnZapisz.setId("btnPacjent");
        btnZapisz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    pst = con.prepareStatement("DELETE FROM dniPrzyjec WHERE idPracownika="+idPacjenta);
                    pst.executeUpdate();
                if(chPn.isSelected()){
                    pn=7;
                    String idDnia="pn";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,pn);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();
                }else{String idDnia="pn";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,pn);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();}
                if(chWt.isSelected()){
                    wt=7;
                    String idDnia="wt";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,wt);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();
                }else{String idDnia="wt";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,wt);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();}
                if(chSr.isSelected()){
                    sr=7;
                    String idDnia="sr";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,sr);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();
                }else{String idDnia="sr";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,sr);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();}
                if(chCz.isSelected()){
                    cz=7;
                    String idDnia="cz";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,cz);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();
                }else{String idDnia="cz";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,cz);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();}
                if(chPt.isSelected()){
                    pt=7;
                    String idDnia="pt";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,pt);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();
                }else{String idDnia="pt";
                    pst = con.prepareStatement("INSERT INTO dniPrzyjec (idDnia, wartosc, idPracownika) VALUES(?,?,?);");
                    pst.setString(1,idDnia);
                    pst.setInt(2,pt);
                    pst.setString(3,idPacjenta);
                    pst.executeUpdate();
                }
                pst.close();
                
                } catch (SQLException ex) {
                    Logger.getLogger(EdycjaD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(btnZapisz, 1, 7, 2, 1);
        grid.setHalignment(btnZapisz, HPos.CENTER);

        btnWyjdz = new Button("Wyjdź");
        btnWyjdz.setId("btnPacjent");
        btnWyjdz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subStage.close();
            }
        });
        grid.add(btnWyjdz, 1, 8, 2, 1);
        grid.setHalignment(btnWyjdz, HPos.CENTER);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();
    }   
}
