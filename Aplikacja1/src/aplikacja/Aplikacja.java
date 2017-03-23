
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package aplikacja;


public class Aplikacja extends ConnectionManager {


    public static void main(String[] args) {
        
        Login log = new Login();
        getConnection();
        log.launch(args);
   
    }
    
}