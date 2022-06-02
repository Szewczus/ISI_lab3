package front;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

//    @FXML
//    private GridPane miejsce_na_dane;

    @FXML
    private GridPane miejsce_na_przyciski;

    @FXML
    private Button dodaj;

    @FXML
    private Button import_danych;

    @FXML
    private Button export_danych;


    static String nazwy_kolumn[] ={"nazwa producenta", "przekątna ekranu", "rozdzielczość ekranu", "rodzaj powierzchni ekranu", "czy ekran jest dotykowy", "nazwa procesora",  "liczba rdzeni fizycznych", "prędkość taktowania MHz", "wielkość pamięci RAM", "pojemność dysku", "rodzaj dysku", "nazwa układu graficznego", "pamięć układu graficznego", "nazwa systemu operacyjnego", "rodzaj napędu fizycznego w komputerze"};

    ClassReader classReader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        classReader = new ClassReader(Data.class);
        classReader.readClassMethods(); //metoda przechowuje metody klasy Data

        int j = 0;
        for(String columHeader : nazwy_kolumn){
            TableColumn<Data, String> col1 = new TableColumn<>(columHeader);
            col1.setMinWidth(100);
            col1.setCellValueFactory(new PropertyValueFactory<Data, String>(classReader.getFields().get(j).getName()));
            col1.setCellFactory(TextFieldTableCell.<Data>forTableColumn());
            col1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Data, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Data, String> event) {
                    Data data = event.getTableView().getItems().get(event.getTablePosition().getRow());
                    Method method = classReader.getSetMethods().get(event.getTablePosition().getColumn());
                    try {
                        method.invoke(data, event.getNewValue());   //computer.setManufacturer(t.getNewValue());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
            tableView.getColumns().add(col1);
            j++;
        }
        tableView.setMinWidth(500);
        tableView.setMinHeight(500);


//        for(int i=0; i<nazwy_kolumn.length; i++){
//            TextField textField = new TextField();
//            textField.setPromptText(nazwy_kolumn[i]);
//            miejsce_na_dane.add(textField, i, 3);
//        }




        wczytaj_txt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wczytajZPlikuIUmiescWTabeli(nazwy_kolumn);
            }
        });

        wczytaj_xml.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    wczytaj_xml();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        });

        zapisz_txt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //miejsce_na_dane.setVisible(true);
                List <Data> danetxtList = tableView.getItems();
                String absolutePth = chooseFile();
                FileWriter w = null;
                try {
                    w = new FileWriter(absolutePth);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(Data data: danetxtList){
                    try {
                        w.write(data.getColumnsValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /** to służyło do odczytywania z textfeildów daych i wstawiania do tabelki teraz jest edytowane bezpośrednio z poziomu tabeli **/
/*              List <Data> danetxtList = tableView.getItems();
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
                tableView.setEditable(true);*/

                /** dodanie nowego wiersza  **/
                tableView.getItems().add(new Data());
            }
        });

        zapisz_xml.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wczytajDaneDOPlikuXML();
            }
        });

        import_danych.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                importujDaneZBazy();
            }
        });

        export_danych.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exportujDaneZBazy();

            }
        });



    }

    private void wczytajZPlikuIUmiescWTabeli(String[] nazwy_kolumn) {
        List <Data> danetxtList = wczytajDaneZPlikuTXT();
        ObservableList<Data> data1 = FXCollections.observableArrayList(danetxtList);
        tableView.setItems(data1);
        tableView.setEditable(true);
    }

    private  void importujDaneZBazy()  {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        try {

            ClassReader dataDtoReader = new ClassReader(DataDto.class);
            dataDtoReader.readClassMethods();

            ClassReader dataReader = new ClassReader(Data.class);
            dataReader.readClassMethods();

            List<DataDto> dataListDto= connectionHelper.showData();
            List<Data> dataList = new ArrayList<>();
            for(DataDto dataDto : dataListDto){
                Data data = new Data();
                int i = 1;
                for (Method method : dataReader.getSetMethods()){
                    String d1 = (String) dataDtoReader.getGetMethods().get(i).invoke(dataDto);
                    method.invoke(data, d1);
                    i++;
                }


                dataList.add(data);
                ObservableList<Data> data1 = FXCollections.observableArrayList(dataList);
                tableView.setItems(data1);
            }
        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void exportujDaneZBazy() {
        List <Data> danetxtList = tableView.getItems();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Long i=(long)1;

        ClassReader dataDtoReader = new ClassReader(DataDto.class);
        dataDtoReader.readClassMethods();

        ClassReader dataReader = new ClassReader(Data.class);
        dataReader.readClassMethods();
        for(Data data : danetxtList){
            int[] licznik = {0};
            DataDto dataDto = new DataDto();
            dataDto.setId(i);
            /*for (Method method : dataDtoReader.getSetMethods()){
                String d1 = null;
                try {
                    d1 = (String) dataReader.getGetMethods().get(licznik[0] -1).invoke(data);
                    method.invoke(dataDto, d1);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                licznik[0]++;
            }*/

            dataDtoReader.getSetMethods().stream().skip(1).forEach(x -> {
                String d1 = null;
                try {
                    d1 = (String) dataReader.getGetMethods().get(licznik[0]).invoke(data);
                    x.invoke(dataDto, d1);

                } catch (IllegalAccessException | InvocationTargetException  e) {
                    e.printStackTrace();
                }
                licznik[0]++;
            });

            try {
                connectionHelper.saveData(dataDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }

    }


    private List<Data> wczytajDaneZPlikuTXT() {
        List<Data> data = new ArrayList<>();
        //String absolutePath = chooseFile();
        String absolutePath = "E:/studia/3_semestr_mgr/ISI/lab3/katalog.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line = br.readLine();
            while (line != null) {
                Data data1 = new Data();
                String [] cecha = line.split(";", -1);
                setData1(data1, cecha, classReader);
                data.add(data1);
                line = br.readLine();
            }

        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String chooseFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File openedFile = fileChooser.showOpenDialog(new Stage());
        String absolutePath = openedFile.getAbsolutePath();
        return absolutePath;
    }

    private void setData1(Data data1, String[] cecha, ClassReader classReader) throws InvocationTargetException, IllegalAccessException {
        int i=0;
        for(Method method : classReader.getSetMethods()){
            method.invoke(data1, cecha[i]); //zamiast    data1.setCol1(cecha[0]); data1.setCol2(cecha[1]); itd
            i++;
        }
    }


    private List<Data> wczytajDaneDOPlikuXML(){
        List<Data> data = tableView.getItems();
        DataList dataList = new DataList(data);
        DOMBuider domBuider = new DOMBuider();
        try {
            domBuider.saveToXML(dataList);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return data;
    }


    private void wczytaj_xml() throws JAXBException {
        List<Data> laptops = tableView.getItems();
        ReadFromXML readFromXML = new ReadFromXML();
        readFromXML.readFromXML(laptops, tableView);
    }



}
