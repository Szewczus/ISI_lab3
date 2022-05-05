import entity.ResponseEntity1;
import front.DataDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.ClassReader;
import org.json.JSONObject;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBException;
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



    static String nazwy_kolumn[] ={"nazwa producenta", "przekątna ekranu", "rozdzielczość ekranu", "rodzaj powierzchni ekranu", "czy ekran jest dotykowy", "nazwa procesora",  "liczba rdzeni fizycznych", "prędkość taktowania MHz", "wielkość pamięci RAM", "pojemność dysku", "rodzaj dysku", "nazwa układu graficznego", "pamięć układu graficznego", "nazwa systemu operacyjnego", "rodzaj napędu fizycznego w komputerze"};

    ClassReader classReader;

    public void initialize(URL location, ResourceBundle resources) {
        matrixCombo.getItems().setAll("matowa", "błyszcząca");
        final String[] matrix = {""};
        final String[] producent = {""};
        matrixCombo.setPromptText("Email address");
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

        matrixButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                        "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                        "         xmlns:std=\"http://ewa.pl/soap-example\"  >" +
                        "  <soap:Body>" +
                        "    <std:getRows>" +
                        "    <manufacturer>Dell</manufacturer>" +
                        "" +
                        "    </std:getRows>" +
                        "  </soap:Body>" +
                        "</soap:Envelope>";
                try {
                   String resp =  connectionHelper.handleRequest("POST", "http://localhost:8081/ws", input ,"1000","1000");
                    try {
                        ResponseEntity1 responseEntity1  = convertToObject(resp);
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


}
