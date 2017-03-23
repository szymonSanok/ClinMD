package aplikacja;

import javafx.beans.property.SimpleStringProperty;



    public class Person{
        private final SimpleStringProperty numer;
        private final SimpleStringProperty imie;
        private Person (String idd, String imiee){
            this.numer = new SimpleStringProperty(idd);
            this.imie = new SimpleStringProperty(imiee);
            
        }

        public String getNumer(){
            return numer.get();
        }
        
        public void setNumer(String idd){
            numer.set(idd);
        }
        
        public String getImie(){
            return imie.get();
        }
        
        public void setImie(String imiee){
            imie.set(imiee);
        }
    }

