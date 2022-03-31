package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button zapisz_txt;

    @FXML
    private Button wczytaj_txt;

    @FXML
    private Button zapisz_xml;

    @FXML
    private Button wczytaj_xml;

    @FXML
    private TableView tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn col1 = new TableColumn("nazwa producenta");
        TableColumn col2 = new TableColumn("przekątna ekranu");
        TableColumn col3 = new TableColumn("rozdzielczość ekranu");
        TableColumn col4 = new TableColumn("rodzaj powierzchni ekranu");
        TableColumn col5 = new TableColumn("czy ekran jest dotykowy");
        TableColumn col6 = new TableColumn("nazwa procesora");
        TableColumn col7 = new TableColumn("liczba rdzeni fizycznych");
        TableColumn col8 = new TableColumn("prędkość taktowania MHz");
        TableColumn col9 = new TableColumn("wielkość pamięci RAM");
        TableColumn col10 = new TableColumn("pojemność dysku");
        TableColumn col11 = new TableColumn("rodzaj dysku");
        TableColumn col12 = new TableColumn("pamięć układu graficznego");
        TableColumn col13 = new TableColumn("nazwa systemu operacyjnego");
        TableColumn col14 = new TableColumn("rodzaj napędu fizycznego w komputerze");

        col1.setMinWidth(100);
        col2.setMinWidth(120);
        col3.setMinWidth(120);
        col4.setMinWidth(140);
        col5.setMinWidth(140);
        col6.setMinWidth(120);
        col7.setMinWidth(140);
        col8.setMinWidth(140);
        col9.setMinWidth(120);
        col10.setMinWidth(120);
        col11.setMinWidth(120);
        col12.setMinWidth(150);
        col13.setMinWidth(170);
        col14.setMinWidth(230);

        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);
        tableView.getColumns().add(col5);
        tableView.getColumns().add(col6);
        tableView.getColumns().add(col7);
        tableView.getColumns().add(col8);
        tableView.getColumns().add(col9);
        tableView.getColumns().add(col10);
        tableView.getColumns().add(col11);
        tableView.getColumns().add(col12);
        tableView.getColumns().add(col13);
        tableView.getColumns().add(col14);

        tableView.setMinWidth(500);
    }
}
