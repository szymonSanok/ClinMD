package aplikacja;

import aplikacja.ConnectionManager;
import java.sql.ResultSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Table extends ConnectionManager {

    protected ObservableList<ObservableList> data, lista;
    protected String dane;
    protected TableView tableview;
    
    public void buildData(String sql) {
        con = ConnectionManager.getConnection();

        data = FXCollections.observableArrayList();
        tableview.getColumns().clear();
        //ResultSet
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void toObsList(String sqlObs) {
        con = ConnectionManager.getConnection();

        lista = FXCollections.observableArrayList();
        
        try {
            ResultSet rs = con.createStatement().executeQuery(sqlObs);
            while (rs.next()) {
                ObservableList<String> list = FXCollections.observableArrayList();
                
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    list.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + list);
                lista.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie zapisano do listy");
        }
    }

   

}
