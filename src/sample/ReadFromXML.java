package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ReadFromXML {
    public void readFromXML(List<Data> dataList, TableView tableView) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(DataList.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        String path = chooseFile();
        DataList laptops = (DataList) unmarshaller.unmarshal(new File(path));
        System.out.println(laptops);
        ObservableList<Data> computerObservableList = FXCollections.observableList(laptops.getLaptop());
        tableView.setItems(computerObservableList);
    }

    private String chooseFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File openedFile = fileChooser.showOpenDialog(new Stage());
        String absolutePath = openedFile.getAbsolutePath();
        return absolutePath;
    }
}
