import entity.ResponseEntity1;

import front.ClassReader;
import front.Data;
import front.DataDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.w3c.dom.NodeList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ComboBox matrixCombo;

    @FXML
    private Button matrixButton;

    @FXML
    private ComboBox producentCombo;

    @FXML
    private Button producentButton;

    @FXML
    private TableView tableView;

    @FXML
    private TextField liczbaLaptopowProducenta;



    static String nazwy_kolumn[] ={"nazwa producenta", "przekątna ekranu", "rozdzielczość ekranu", "rodzaj powierzchni ekranu", "czy ekran jest dotykowy", "nazwa procesora",  "liczba rdzeni fizycznych", "prędkość taktowania MHz", "wielkość pamięci RAM", "pojemność dysku", "rodzaj dysku", "nazwa układu graficznego", "pamięć układu graficznego", "nazwa systemu operacyjnego", "rodzaj napędu fizycznego w komputerze"};

    ClassReader classReader;

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

        /*set combox*/
        matrixCombo.getItems().setAll("matowa", "błyszcząca");
        final String[] matrix = {""};
        final String[] producent = {""};
        matrixCombo.valueProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue ov, String t, String t1) {
                matrix[0] = t1;
            }
        });

        producentCombo.getItems().setAll("Samsung", "Dell", "Asus", "Fujitsu", "Huawei", "MSI", "Sony");
        producentCombo.valueProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue ov, String t, String t1) {
                producent[0] = t1;
            }
        });
        /*end set combox*/




        producentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                        "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                        "         xmlns:std=\"http://ewa.pl/soap-example\"  >" +
                        "  <soap:Body>" +
                        "    <std:getRows>" +
                        "    <manufacturer>Samsung</manufacturer>" +
                        "" +
                        "    </std:getRows>" +
                        "  </soap:Body>" +
                        "</soap:Envelope>";
                try {
                   String resp =  connectionHelper.handleRequest("POST", "http://localhost:8081/ws", input ,"1000","1000");
                    try {
                        ResponseEntity1 responseEntity1  = convertToObject(resp);

                        try {
                            List<Data> dataList = dataDtotoData(responseEntity1.getComputer());
                            ObservableList<Data> data1 = FXCollections.observableArrayList(dataList);
                            tableView.setItems(data1);
                            tableView.setEditable(true);
                            liczbaLaptopowProducenta.setText(String.valueOf(responseEntity1.getCountComputersByManufacturer()));
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } catch (SOAPException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });
    }
    private static ResponseEntity1 convertToObject(String xml) throws SOAPException, IOException {
        ResponseEntity1 responseEntity1 = new ResponseEntity1();
        List<DataDto> dataDtos = new ArrayList<>();
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(null, is);
        NodeList listResult = message.getSOAPBody().getChildNodes();
        for (int i = 0; i < listResult.getLength(); i++) {
            NodeList children = listResult.item(i).getChildNodes();
            for (int k = 0; k < children.getLength(); k++) {
                System.out.println(children.item(k).getNodeName());
                System.out.println(children.item(k).getTextContent());
                if(children.item(k).getNodeName().equals("countComputersByManufacturer")){
                    responseEntity1.setCountComputersByManufacturer(Long.valueOf(children.item(k).getTextContent()));
                }
                if(children.item(k).getNodeName().equals("countComputersByMatrixType")){
                    responseEntity1.setCountComputersByMatrixType(Long.valueOf(children.item(k).getTextContent()));
                }
                if(children.item(k).getNodeName().equals("countComputersByProportions")){
                    responseEntity1.setCountComputersByProportions(Long.valueOf(children.item(k).getTextContent()));
                }

                if (children.item(k).getNodeName().equals("computerList")) {
                    NodeList children1 = children.item(k).getChildNodes();
                    DataDto dataDto = new DataDto();
                    for(int j=0; j< children1.getLength(); j++){
                        String content = children1.item(j).getTextContent();
                        if(j==0){
                            dataDto.setId(Long.valueOf(content));
                        }
                        if(j==1){
                            dataDto.setManufacturer(content);
                        }
                        if(j==2){
                            dataDto.setScreenSize(content);
                        }
                        if(j==3){
                            dataDto.setResolution(content);
                        }
                        if(j==4){
                            dataDto.setMatrixTexture(content);
                        }
                        if(j==5){
                            dataDto.setTouch(content);
                        }
                        if(j==6){
                            dataDto.setProcessorName(content);
                        }
                        if(j==7){
                            dataDto.setPhysicalCores(content);
                        }
                        if(j==7){
                            dataDto.setClockSpeed(content);
                        }
                        if(j==8){
                            dataDto.setRam(content);
                        }
                        if(j==9){
                            dataDto.setDiscSize(content);
                        }
                        if(j==10){
                            dataDto.setDiscType(content);
                        }
                        if(j==11){
                            dataDto.setGraphicCardName(content);
                        }
                        if(j==12){
                            dataDto.setGraphicCardMemory(content);
                        }
                        if(j==13){
                            dataDto.setOs(content);
                        }
                        if(j==14){
                            dataDto.setDiscReader(content);
                        }

                        System.out.println(children1.item(j).getTextContent());
                    }
                    dataDtos.add(dataDto);
                    responseEntity1.setComputer(dataDtos);
                }

                if (children.item(k).getNodeName().equals("countComputersByMatrixType")) {

                }
            }
        }

        return responseEntity1;
    }

    public List<Data> dataDtotoData(List<DataDto> dataListDto) throws InvocationTargetException, IllegalAccessException {
        ClassReader dataDtoReader = new ClassReader(DataDto.class);
        dataDtoReader.readClassMethods();

        ClassReader dataReader = new ClassReader(Data.class);
        dataReader.readClassMethods();

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
        }
        return dataList;
    }


}
