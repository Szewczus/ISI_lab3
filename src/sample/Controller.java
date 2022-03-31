package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private TableColumn col1;
    private TableColumn col2;
    private TableColumn col3;
    private TableColumn col4;
    private TableColumn col5;
    private TableColumn col6;
    private TableColumn col7;
    private TableColumn col8;
    private TableColumn col9;
    private TableColumn col10;
    private TableColumn col11;
    private TableColumn col12;
    private TableColumn col13;
    private TableColumn col14;
    private TableColumn col15;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col1 = new TableColumn("nazwa producenta");
        col2 = new TableColumn("przekątna ekranu");
        col3 = new TableColumn("rozdzielczość ekranu");
        col4 = new TableColumn("rodzaj powierzchni ekranu");
        col5 = new TableColumn("czy ekran jest dotykowy");
        col6 = new TableColumn("nazwa procesora");
        col7 = new TableColumn("liczba rdzeni fizycznych");
        col8 = new TableColumn("prędkość taktowania MHz");
        col9 = new TableColumn("wielkość pamięci RAM");
        col10 = new TableColumn("pojemność dysku");
        col11 = new TableColumn("rodzaj dysku");
        col12 = new TableColumn("nazwa układu graficznego");
        col13 = new TableColumn("pamięć układu graficznego");
        col14 = new TableColumn("nazwa systemu operacyjnego");
        col15 = new TableColumn("rodzaj napędu fizycznego w komputerze");

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
        col15.setMinWidth(230);



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
        tableView.getColumns().add(col15);

        tableView.setMinWidth(500);




        wczytaj_txt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List <Data> danetxtList = wczytajDaneZPliku();
                ObservableList<Data> data1 = FXCollections.observableArrayList(danetxtList);
                setcellValues();
                tableView.setItems(data1);
            }
        });

        zapisz_txt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });


    }

    private void setcellValues() {
        col1.setCellValueFactory(new PropertyValueFactory<Data, String>("col1"));
        col2.setCellValueFactory(new PropertyValueFactory<Data, String>("col2"));
        col3.setCellValueFactory(new PropertyValueFactory<Data, String>("col3"));
        col4.setCellValueFactory(new PropertyValueFactory<Data, String>("col4"));
        col5.setCellValueFactory(new PropertyValueFactory<Data, String>("col5"));
        col6.setCellValueFactory(new PropertyValueFactory<Data, String>("col6"));
        col7.setCellValueFactory(new PropertyValueFactory<Data, String>("col7"));
        col8.setCellValueFactory(new PropertyValueFactory<Data, String>("col8"));
        col9.setCellValueFactory(new PropertyValueFactory<Data, String>("col9"));
        col10.setCellValueFactory(new PropertyValueFactory<Data, String>("col10"));
        col11.setCellValueFactory(new PropertyValueFactory<Data, String>("col11"));
        col12.setCellValueFactory(new PropertyValueFactory<Data, String>("col12"));
        col13.setCellValueFactory(new PropertyValueFactory<Data, String>("col13"));
        col14.setCellValueFactory(new PropertyValueFactory<Data, String>("col14"));
        col15.setCellValueFactory(new PropertyValueFactory<Data, String>("col15"));
    }

    private List<Data> wczytajDaneZPliku() {
        List<Data> data = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("katalog.txt"))) {
            String line = br.readLine();
            while (line != null) {
                Data data1 = new Data();
                String [] cecha = line.split(";", -1);
                data1.setCol1(cecha[0]);
                data1.setCol2(cecha[1]);
                data1.setCol3(cecha[2]);
                data1.setCol4(cecha[3]);
                data1.setCol5(cecha[4]);
                data1.setCol6(cecha[5]);
                data1.setCol7(cecha[6]);
                data1.setCol8(cecha[7]);
                data1.setCol9(cecha[8]);
                data1.setCol10(cecha[9]);
                data1.setCol11(cecha[10]);
                data1.setCol12(cecha[11]);
                data1.setCol13(cecha[12]);
                data1.setCol14(cecha[13]);
                data1.setCol15(cecha[14]);
                data.add(data1);
                line = br.readLine();
            }

            System.out.println(data.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
