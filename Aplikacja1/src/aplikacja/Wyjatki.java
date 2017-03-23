/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import javafx.scene.control.Alert;

/**
 *
 * @author Simon
 */
public class Wyjatki {

    public void kodPocztowy() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Kod pocztowy!");
        alert.setContentText("Kod pocztowy musi składać się z 6 znaków\ni mieć format 00-000!");
        alert.showAndWait();
    }

    public void numerTelefonu() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Numer telefonu!");
        alert.setContentText("Numer telefonu musi składać się z 9 znaków\npisanych nierozłącznie!");
        alert.showAndWait();
    }
    
    public void numerPolisy(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Numer polisy!");
        alert.setContentText("Numer polisy powinien zawierać co najmniej 6 cyfr!");
        alert.showAndWait();
    }
    
    public void pesel(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Numer PESEL!");
        alert.setContentText("Numer PESEL powinien składać się z samych cyfr\ni mieć dokładnie 11 znaków!");
        alert.showAndWait();
    }
    
    public void forget(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Błędne dane");
        alert.setContentText("Podałeś błędny PESEL lub numer telefonu!");
        alert.showAndWait();
    }
    
    public void badConnection(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Błąd!");
        alert.setContentText("Problem połączenia z bazą danych");
        alert.showAndWait();
    }
    
    public void pustePola(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Błąd!");
        alert.setContentText("Nie możnesz pozostawić pustych pól w formularzu!");
        alert.showAndWait();
    }
}
