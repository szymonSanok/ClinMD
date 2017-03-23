/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import static aplikacja.ConnectionManager.con;
import static aplikacja.ConnectionManager.pst;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Simon
 */
class Register extends ConnectionManager {

    private Button btnChange, btnReset, btnAnuluj, btnFile;
    private RadioButton radioTak, radioNie;
    private Label lUbezp, lImie, lNazwisko, lPesel, lUlica, lMiasto, lKodPocz, lWojew, lTelefon, lNrPolisy, lHaslo, lFile;
    protected static String cssPath, imagePath = "C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\person.jpg";
    private TextField tfImie, tfNazwisko, tfPesel, tfUlica, tfMiasto, tfKodPocz, tfWojew, tfTelefon, tfNrPolisy;
    private PasswordField pfHaslo;
    private FileChooser fileChooser;
    private Image imgZar, imgWyj, imgClear;
    private FileInputStream fis;
    int width = 320, height = 550;
    String ubezpiecz;
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    private final ToggleGroup radioGroup;
    private File file;
    private Wyjatki wyjatek = new Wyjatki();

    Register() {
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        GridPane grid = new GridPane();
        Login log = new Login();
        Stage subStage = new Stage();
        Scene scene = new Scene(grid, width, height);
        grid.setId("register");
        grid.setHgap(10);
        grid.setVgap(10);
        subStage.setTitle("Stwórz konto");
        grid.setAlignment(Pos.TOP_LEFT);
        subStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));

        lImie = new Label("Imię:");
        grid.add(lImie, 1, 1);
        grid.setHalignment(lImie, HPos.RIGHT);

        tfImie = new TextField(null);
        tfImie.setId("textSize");
        grid.add(tfImie, 2, 1);

        lNazwisko = new Label("Nazwisko:");
        grid.add(lNazwisko, 1, 2);
        grid.setHalignment(lNazwisko, HPos.RIGHT);

        tfNazwisko = new TextField(null);
        tfNazwisko.setId("textSize");
        grid.add(tfNazwisko, 2, 2);

        lPesel = new Label("PESEL:");
        grid.add(lPesel, 1, 3);
        grid.setHalignment(lPesel, HPos.RIGHT);

        tfPesel = new TextField("");
        tfPesel.setId("textSize");
        grid.add(tfPesel, 2, 3);

        lUlica = new Label("Ulica:");
        grid.add(lUlica, 1, 4);
        grid.setHalignment(lUlica, HPos.RIGHT);

        tfUlica = new TextField(null);
        tfUlica.setId("textSize");
        grid.add(tfUlica, 2, 4);

        lMiasto = new Label("Miasto:");
        grid.add(lMiasto, 1, 5);
        grid.setHalignment(lMiasto, HPos.RIGHT);

        tfMiasto = new TextField(null);
        tfMiasto.setId("textSize");
        grid.add(tfMiasto, 2, 5);

        lKodPocz = new Label("Kod pocztowy:");
        grid.add(lKodPocz, 1, 6);
        grid.setHalignment(lKodPocz, HPos.RIGHT);

        tfKodPocz = new TextField(null);
        tfKodPocz.setId("textSize");
        grid.add(tfKodPocz, 2, 6);

        lWojew = new Label("Województwo:");
        grid.add(lWojew, 1, 7);
        grid.setHalignment(lWojew, HPos.RIGHT);

        tfWojew = new TextField(null);
        tfWojew.setId("textSize");
        grid.add(tfWojew, 2, 7);

        lTelefon = new Label("Telefon:");
        grid.add(lTelefon, 1, 8);
        grid.setHalignment(lTelefon, HPos.RIGHT);

        tfTelefon = new TextField(null);
        tfTelefon.setId("textSize");
        grid.add(tfTelefon, 2, 8);

        lUbezp = new Label("Czy ubezpieczony:");
        grid.add(lUbezp, 1, 9);
        grid.setHalignment(lUbezp, HPos.RIGHT);

        radioGroup = new ToggleGroup();

        radioTak = new RadioButton("Tak");
        radioTak.setToggleGroup(radioGroup);
        grid.add(radioTak, 2, 9);

        radioNie = new RadioButton("Nie");
        radioNie.setToggleGroup(radioGroup);
        grid.add(radioNie, 2, 9);
        grid.setHalignment(radioNie, HPos.RIGHT);

        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (radioTak.isSelected()) {
                    ubezpiecz = "Tak";
                }
                if (radioNie.isSelected()) {
                    ubezpiecz = "Nie";
                }
            }
        });

        lNrPolisy = new Label("Numer polisy:");
        grid.add(lNrPolisy, 1, 10);
        grid.setHalignment(lNrPolisy, HPos.RIGHT);

        tfNrPolisy = new TextField(null);
        tfNrPolisy.setId("textSize");
        grid.add(tfNrPolisy, 2, 10);

        lHaslo = new Label("Hasło:");
        grid.add(lHaslo, 1, 11);
        grid.setHalignment(lHaslo, HPos.RIGHT);

        pfHaslo = new PasswordField();
        pfHaslo.setId("textSize");
        grid.add(pfHaslo, 2, 11);

        lFile = new Label("Zdjęcie:");
        grid.add(lFile, 1, 12);
        grid.setHalignment(lFile, HPos.RIGHT);

        
        btnFile = new Button("Wybierz zdjęcie");
        btnFile.setId("btnLogin");
        btnFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser = new FileChooser();
                fileChooser.setTitle("Wybierz zdjęcie");
                fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    imagePath = file.getAbsolutePath();
                    System.out.println("file:" + imagePath);
                } else {
                    imagePath = "C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\person.jpg";
                }
            }
        });
        grid.add(btnFile, 2, 12);

        imgZar = new Image(Pacjent.class.getResourceAsStream("zapisz.png"));
        btnChange = new Button("                               Zapisz", new ImageView(imgZar));
        btnChange.setId("btnPacjent");
        btnChange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (tfPesel.getText().length() != 11 || tfPesel.getText().matches("\\d+") == false) {
                        wyjatek.pesel();
                    }
                    if (tfKodPocz.getText().charAt(2) != '-' || tfKodPocz.getText().length() != 6 || tfKodPocz.getText().replace('-', '0').matches("\\d+") == false) {
                        wyjatek.kodPocztowy();
                    }
                    if (tfTelefon.getText().length() != 9 || tfTelefon.getText().matches("\\d+") == false) {
                        wyjatek.numerTelefonu();
                    }
                    if (tfNrPolisy.getText().length() < 6 || tfNrPolisy.getText().matches("\\d+") == false) {
                        wyjatek.numerPolisy();
                    } else {

                        pst = con.prepareStatement("INSERT INTO pacjenci (imie, nazwisko, pesel, ulica, miasto, kodpocztowy, wojewodztwo, telefon, czyubezpieczony, nrpolisy, haslo, avatar) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
                        pst.setString(1, tfImie.getText());
                        pst.setString(2, tfNazwisko.getText());
                        pst.setString(3, tfPesel.getText());
                        pst.setString(4, tfUlica.getText());
                        pst.setString(5, tfMiasto.getText());
                        pst.setString(6, tfKodPocz.getText());
                        pst.setString(7, tfWojew.getText());
                        pst.setString(8, tfTelefon.getText());
                        pst.setString(9, ubezpiecz);
                        pst.setString(10, tfNrPolisy.getText());
                        pst.setString(11, pfHaslo.getText());
//                      byte[] data = Files.readAllBytes(new File(imagePath).toPath()); ładowanie obrazka z bazy danych ??
                        pst.setString(12, imagePath);
                        pst.executeUpdate();
                        pst.close();
                        subStage.close();
                    }
                } catch (SQLException ex) {
                    wyjatek.pustePola();
                }

                
            }
        });
        btnChange.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnChange, 1, 13, 2, 1);
        grid.setHalignment(btnChange, HPos.RIGHT);
        
        imgClear = new Image(Pacjent.class.getResourceAsStream("clear.png"));
        btnReset = new Button("                             Wyczyść", new ImageView(imgClear));
        btnReset.setId("btnPacjent");
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfImie.setText(null);
                tfNazwisko.setText(null);
                tfPesel.setText(null);
                tfMiasto.setText(null);
                tfUlica.setText(null);
                tfNrPolisy.setText(null);
                tfKodPocz.setText(null);
                tfWojew.setText(null);
                tfTelefon.setText(null);
                pfHaslo.setText(null);
            }
        });
        btnReset.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnReset, 1, 14, 2, 1);
        grid.setHalignment(btnReset, HPos.LEFT);

        imgWyj = new Image(Pacjent.class.getResourceAsStream("exit.png"));
        btnAnuluj = new Button("                                Anuluj", new ImageView(imgWyj));
        btnAnuluj.setId("btnPacjent");
        btnAnuluj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subStage.close();
            }
        });
        btnAnuluj.setAlignment(Pos.BASELINE_LEFT);
        grid.add(btnAnuluj, 1, 15, 2, 1);
        grid.setHalignment(btnAnuluj, HPos.RIGHT);

        scene.getStylesheets().add(cssPath);
        subStage.setScene(scene);
        subStage.show();

    }
}
