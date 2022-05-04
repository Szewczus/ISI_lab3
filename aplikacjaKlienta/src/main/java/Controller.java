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
                   producentButton.setText(resp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
