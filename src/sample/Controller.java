package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
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

    @FXML
    private GridPane miejsce_na_dane;

    @FXML
    private GridPane miejsce_na_przyciski;

    @FXML
    private Button dodaj;

    static String nazwy_kolumn[] ={"nazwa producenta", "przekątna ekranu", "rozdzielczość ekranu", "rodzaj powierzchni ekranu", "czy ekran jest dotykowy", "nazwa procesora",  "liczba rdzeni fizycznych", "prędkość taktowania MHz", "wielkość pamięci RAM", "pojemność dysku", "rodzaj dysku", "nazwa układu graficznego", "pamięć układu graficznego", "nazwa systemu operacyjnego", "rodzaj napędu fizycznego w komputerze"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int j = 1;
        for(String columHeader : nazwy_kolumn){
            TableColumn<Data, String> col1 = new TableColumn<>(columHeader);
            col1.setMinWidth(100);
            col1.setCellValueFactory(new PropertyValueFactory<Data, String>("col"+j));
            col1.setCellFactory(TextFieldTableCell.<Data>forTableColumn());
            tableView.getColumns().add(col1);
            j++;
        }


        tableView.setMinWidth(500);


        for(int i=0; i<nazwy_kolumn.length; i++){
            TextField textField = new TextField();
            textField.setPromptText(nazwy_kolumn[i]);
            miejsce_na_dane.add(textField, i, 3);
        }


        miejsce_na_dane.setVisible(false);
        miejsce_na_przyciski.setVisible(false);




        wczytaj_txt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                miejsce_na_dane.setVisible(false);
                miejsce_na_przyciski.setVisible(false);
                wczytajZPlikuIUmiescWTabeli(nazwy_kolumn);
            }
        });

        zapisz_txt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                miejsce_na_dane.setVisible(true);
                miejsce_na_przyciski.setVisible(true);
                wczytajZPlikuIUmiescWTabeli(nazwy_kolumn);
            }
        });

        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //List <Data> danetxtList = wczytajDaneZPlikuTXT();
                List <Data> danetxtList = tableView.getItems();
                String cecha []= new String[24];
                Data data1 = new Data();
                for(int i=0; i<15; i++){
                    TextField textField = (TextField)miejsce_na_dane.getChildren().get(i);
                    cecha[i]=textField.getText();
                }
                setData1(data1, cecha);

                danetxtList.add(data1);
                ObservableList<Data> dane = FXCollections.observableArrayList(danetxtList);
                //setcellValues(nazwy_kolumn);
                tableView.setItems(dane);
            }
        });


    }

    private void wczytajZPlikuIUmiescWTabeli(String[] nazwy_kolumn) {
        List <Data> danetxtList = wczytajDaneZPlikuTXT();
        ObservableList<Data> data1 = FXCollections.observableArrayList(danetxtList);
        //setcellValues(nazwy_kolumn);
        tableView.setItems(data1);
    }


    private List<Data> wczytajDaneZPlikuTXT() {
        List<Data> data = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("katalog.txt"))) {
            String line = br.readLine();
            while (line != null) {
                Data data1 = new Data();
                String [] cecha = line.split(";", -1);
                setData1(data1, cecha);
                data.add(data1);
                line = br.readLine();
            }

            System.out.println(data.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void setData1(Data data1, String[] cecha) {
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
    }


    private List<Data> wczytajDaneZPlikuXML(){
        List<Data> data = new ArrayList<>();
        return data;
    }





}
