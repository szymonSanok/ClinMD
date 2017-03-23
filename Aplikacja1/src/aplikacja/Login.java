package aplikacja;

import static aplikacja.ConnectionManager.con;
import static aplikacja.ConnectionManager.pst;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application {

    private GridPane grid;
    private Label labUsrLog, labUsrPass;
    private TextField usrLogF;
    private PasswordField usrPassF;
    private Button btnLogin, btnLogin1;
    private Button btnRegister;
    private Button btnForget;
    private Scene scene;
    private String cssPath;
    private Image image;
    private ImageView imgView;
    private Statement stat;
    public static String idPacjenta, idPracownika;
    private Wyjatki wyjatek = new Wyjatki();
    private int width = 295, height = 500;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Zaloguj");
        primaryStage.getIcons().add(new Image("file:C:\\Users\\Simon\\Desktop\\Aplikacja1\\src\\aplikacja\\icon.png"));
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        con = ConnectionManager.getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            wyjatek.badConnection();
        }
        grid = new GridPane();
        grid.setId("login");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        imgView = new ImageView();
        image = new Image(Login.class.getResourceAsStream("przychodnia.jpg"));
        imgView.setImage(image);
        grid.add(imgView, 1, 0);
        btnLogin = new Button("Zaloguj");
        btnLogin.setId("btnLogin");
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                labUsrLog = new Label("PESEL: ");
                grid.add(labUsrLog, 1, 1);
                grid.setHalignment(labUsrLog, HPos.LEFT);

                usrLogF = new TextField();
                usrLogF.setId("field");
                usrLogF.setMaxWidth(150);
                grid.add(usrLogF, 1, 1);
                grid.setHalignment(usrLogF, HPos.CENTER);

                labUsrPass = new Label("Hasło: ");
                grid.add(labUsrPass, 1, 2);
                grid.setHalignment(labUsrPass, HPos.LEFT);

                usrPassF = new PasswordField();
                usrPassF.setId("field");
                usrPassF.setMaxWidth(150);
                grid.add(usrPassF, 1, 2);
                grid.setHalignment(usrPassF, HPos.CENTER);

                btnLogin1 = new Button("Zaloguj");
                btnLogin1.setId("btnLogin");
                btnLogin1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            pst = con.prepareStatement("SELECT * FROM pacjenci WHERE pacjenci.pesel=? AND pacjenci.haslo=?");
                            String pesel = usrLogF.getText();
                            String pass = usrPassF.getText();
                            pst.setString(1, pesel);
                            pst.setString(2, pass);
                            ResultSet rs = pst.executeQuery();
                            if (rs.next()) {
                                idPacjenta = rs.getString("idPacjenta");
                                primaryStage.hide();
                                new Pacjent();
                                pst.close();
                                rs.close();
                            } else {
                                pst = con.prepareStatement("SELECT * FROM personelMedyczny WHERE personelMedyczny.pesel=? AND personelMedyczny.haslo=?");
                                pesel = usrLogF.getText();
                                pass = usrPassF.getText();
                                pst.setString(1, pesel);
                                pst.setString(2, pass);
                                rs = pst.executeQuery();
                                if (rs.next()) {
                                    idPacjenta = rs.getString("idPracownika");
                                    primaryStage.hide();
                                    pst.close();
                                    rs.close();
                                    new Lekarz();
                                } else {
                                    usrLogF.setText("błędny login lub hasło");
                                    pst.close();
                                    rs.close();
                                }}
                            //con.close();
                        } catch (SQLException ex) {
                            wyjatek.badConnection();
                        }}});
                grid.add(btnLogin1, 1, 4);
                grid.setHalignment(btnLogin1, HPos.CENTER);
            }

        });
        grid.add(btnLogin, 1, 4);
        grid.setHalignment(btnLogin, HPos.CENTER);

        btnRegister = new Button("Stwórz konto");
        btnRegister.setId("btnLogin");
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Register();
            }

        });
        grid.add(btnRegister, 1, 5);
        grid.setHalignment(btnRegister, HPos.CENTER);

        btnForget = new Button("Przypomnij hasło");
        btnForget.setId("btnLogin");
        btnForget.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Forget();
            }

        });
        grid.add(btnForget, 1, 6);
        grid.setHalignment(btnForget, HPos.CENTER);
        scene = new Scene(grid, width, height);
        scene.getStylesheets().add(cssPath);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
