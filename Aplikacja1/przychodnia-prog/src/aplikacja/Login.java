/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Simon
 */
public class Login extends Application{
    private GridPane grid;

    private Label formTitle, notification;
    private Label LabUsrLog, LabUsrPass;
    private TextField usrLogF;
    private PasswordField usrPassF;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnForget;
    private Button hide;
    private Scene scene;
    private String cssPath;
    int width = 320, height = 200;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Zaloguj");
        cssPath = this.getClass().getResource("screen.css").toExternalForm();
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //grid.setGridLinesVisible(true);

        formTitle = new Label("Witamy");
        grid.add(formTitle, 0, 0, 2, 1);

        LabUsrLog = new Label("User Login: ");
        grid.add(LabUsrLog, 0, 1);

        usrLogF = new TextField();
        grid.add(usrLogF, 1, 1);

        LabUsrPass = new Label("User Password: ");
        grid.add(LabUsrPass, 0, 2);

        usrPassF = new PasswordField();
        grid.add(usrPassF, 1, 2);

        
        hide = new Button("Hide");

        notification = new Label("przykład");
        notification.setId("notification");
        grid.add(notification, 1, 6);
        
        btnLogin = new Button("Zaloguj");
        btnLogin.setId("btnLogin");
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (new String(usrLogF.getText()).equals("admin") && new String(usrPassF.getText()).equals("1234")) {
                    primaryStage.hide();
                    new Pacjent();
//                }
//
//                notification.setText("Błędny login lub hasło");
            }

        });
        grid.add(btnLogin, 1, 4);
        grid.setHalignment(btnLogin, HPos.RIGHT);

        btnRegister = new Button("Stwórz konto");
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    new Register();
            }

        });
        grid.add(btnRegister, 1, 4);
        
        
        btnForget = new Button("Przypomnij hasło");
        btnForget.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    new Forget();
            }

        });
         grid.add(btnForget, 0, 4);
        
        
        
        
        
//        scene.getStylesheets().add(getClass().getResource("screen.css").toExternalForm());
        scene = new Scene(grid, width, height);
        scene.getStylesheets().add(cssPath);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
