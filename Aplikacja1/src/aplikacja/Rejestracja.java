/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import static aplikacja.ConnectionManager.con;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
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
public class Rejestracja extends Table {

    private Label labelWybierz, labelSpecjalizacja;
    private Button btnZapisz, btnAnuluj;
    private ComboBox comboLekarz, comboSpec;
    private DatePicker kalendarz;
    private Scene scene;
    private String cssPath, skierowanie;
    private RadioButton radioSkTak, radioSkNie;
    private final ToggleGroup radioGroup;
    int width = 530, height = 260, pn, wt, sr, cz, pt, idLekarza, idSpec;
    private Wyjatki wyjatek = new Wyjatki();

    private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.getDayOfWeek() == DayOfWeek.of(pn)
                                || item.getDayOfWeek() == DayOfWeek.of(wt)
                                || item.getDayOfWeek() == DayOfWeek.of(sr)
                                || item.getDayOfWeek() == DayOfWeek.of(cz)
                                || item.getDayOfWeek() == DayOfWeek.of(pt)
                                || item.getDayOfWeek() == DayOfWeek.of(6)
                                || item.getDayOfWeek() == DayOfWeek.of(7)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    Rejestracja() {
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Rejestracja.class.getName()).log(Level.SEVERE, null, ex);
        }
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);

        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Badanie");
        grid.setAlignment(Pos.TOP_LEFT);
        subStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));

        labelSpecjalizacja = new Label("Wybierz specjalizację:");
        grid.add(labelSpecjalizacja, 3, 1);

        comboSpec = new ComboBox();
        comboSpec.setId("comboSpec");
        comboSpec.setValue("Wybierz");
        toObsList("SELECT nazwa FROM specjalizacje");
        comboSpec.getItems().addAll(lista);
        grid.add(comboSpec, 4, 1);

        toObsList("SELECT idPracownika, imie,nazwisko FROM personelMedyczny");
        

        comboSpec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                idSpec = comboSpec.getSelectionModel().getSelectedIndex() + 1;
                toObsList("SELECT idPracownika, imie,nazwisko FROM personelMedyczny WHERE idSpecjalizacji=" + idSpec);
                comboLekarz.getItems().clear();
                comboLekarz.getItems().addAll(lista);
            }

        });

        labelWybierz = new Label("Wybierz lekarza:");
        grid.add(labelWybierz, 1, 1);

        comboLekarz = new ComboBox();
        comboLekarz.getItems().clear();
        comboLekarz.getItems().addAll(lista);
        comboLekarz.setId("comboLekarz");
        comboLekarz.setValue("Wybierz");

        grid.add(comboLekarz, 2, 1);
        grid.setHalignment(comboLekarz, HPos.LEFT);

        tableview = new TableView();
        tableview.setId("tableGodziny");
        tableview.setEditable(true);
        grid.add(tableview, 3, 3, 2, 3);

        comboLekarz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String lekarz = ((comboLekarz.getSelectionModel().getSelectedItem()).toString());
                char id = lekarz.charAt(1);
                idLekarza = Character.getNumericValue(id);
                tableview.getItems().removeAll(tableview);
                buildData("SELECT  godzinaOd, godzinaDo, p.idGodziny FROM godzinyPrzyjec p, godzinyLekarzy l WHERE p.idGodziny=l.idGodziny AND l.idPracownika="+idLekarza);
                try {
                    String sql = "SELECT wartosc FROM dniPrzyjec WHERE idPracownika=" + idLekarza + " AND idDnia='pn'";

                    ResultSet rs = con.createStatement().executeQuery(sql);
                    pn = rs.getInt("wartosc");

                    sql = "SELECT wartosc FROM dniPrzyjec WHERE idPracownika=" + idLekarza + " AND idDnia='wt'";

                    rs = con.createStatement().executeQuery(sql);
                    wt = rs.getInt("wartosc");

                    sql = "SELECT wartosc FROM dniPrzyjec WHERE idPracownika=" + idLekarza + " AND idDnia='sr'";

                    rs = con.createStatement().executeQuery(sql);
                    sr = rs.getInt("wartosc");

                    sql = "SELECT wartosc FROM dniPrzyjec WHERE idPracownika=" + idLekarza + " AND idDnia='cz'";

                    rs = con.createStatement().executeQuery(sql);
                    cz = rs.getInt("wartosc");

                    sql = "SELECT wartosc FROM dniPrzyjec WHERE idPracownika=" + idLekarza + " AND idDnia='pt'";

                    rs = con.createStatement().executeQuery(sql);
                    pt = rs.getInt("wartosc");
                    con.close();
                    rs.close();
                } catch (SQLException ex) {
                    wyjatek.badConnection();
                }
                System.out.println("1");
            }

        });

        kalendarz = new DatePicker(LocalDate.now());
        kalendarz.setId("kalendarz");
        grid.add(kalendarz, 1, 3);
        kalendarz.setShowWeekNumbers(false);
        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        kalendarz.setDayCellFactory(dayCellFactory);

        labelWybierz = new Label("Mam skierowanie:");
        grid.add(labelWybierz, 1, 4);

        radioGroup = new ToggleGroup();

        radioSkTak = new RadioButton();
        radioSkTak.setText("Tak");
        radioSkTak.setToggleGroup(radioGroup);
        grid.add(radioSkTak, 2, 4);

        radioSkNie = new RadioButton();
        radioSkNie.setText("Nie");
        radioSkNie.setToggleGroup(radioGroup);
        grid.add(radioSkNie, 2, 4);
        grid.setHalignment(radioSkNie, HPos.RIGHT);

        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (radioSkTak.isSelected()) {
                    skierowanie = "Tak";
                }
                if (radioSkNie.isSelected()) {
                    skierowanie = "Nie";
                }
            }
        });
        
        
        
        btnZapisz = new Button("Zapisz");
        btnZapisz.setId("btnZapisz");
        btnZapisz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String godzina = tableview.getSelectionModel().getSelectedItem().toString();
                char id1 = godzina.charAt(15);
                int id11 = (Character.getNumericValue(id1))*10;
                char id2 = godzina.charAt(16);
                int id22 = Character.getNumericValue(id2);
                int idGodziny = id11+id22;
                try {
                    con = ConnectionManager.getConnection();
                    System.out.println("isClosed = " + con.isClosed());
                    pst = con.prepareStatement("INSERT INTO wizytyZaplanowane (idPacjenta, idGodziny, idPracownika, czySkierowanie, data) VALUES(?,?,?,?,?);");
                    pst.setString(1, idPacjenta);
                    pst.setInt(2, idGodziny);
                    pst.setInt(3, comboLekarz.getSelectionModel().getSelectedIndex() + 1);
                    pst.setString(4, skierowanie);
                    pst.setString(5, kalendarz.getValue().toString());
                    pst.executeUpdate();
                    pst.close();
                    System.out.println("Zapisano");
                    subStage.close();
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Uwaga!");
                    alert.setHeaderText("Puste pola!");
                    alert.setContentText("Nie możesz pozostawić pustych pól w formularzu!");
                    alert.showAndWait();
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
