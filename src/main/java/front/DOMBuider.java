package front;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class DOMBuider {
    public void saveToXMLOld(List<Data> dataList){
        DocumentBuilder builder;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element laptops = doc.createElement("laptops");
            //laptops.setAttribute("moddate", new Date().toString());

            ClassReader classReader = new ClassReader(Data.class);
            classReader.readClassMethods();

            int j=0;
            for(Data data : dataList){
                Element laptop = doc.createElement("laptop");
                for(int i=0; i<classReader.getFields().size(); i++){
                    laptop.setAttribute("id", String.valueOf(j));
                    Element element = doc.createElement(classReader.getFields().get(i).getName());
                    Method method = classReader.getGetMethods().get(i);

                    element.setTextContent(method.invoke(data).toString());
                    laptop.appendChild(element);
                }
                laptops.appendChild(laptop);
                j++;
            }

            doc.appendChild(laptops);

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("katalog3.xml")));

        } catch (ParserConfigurationException  | FileNotFoundException | TransformerException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void saveToXML(DataList dataList) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(DataList.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //File file = chooseFile();
        File file = new File("E:/studia/3_semestr_mgr/ISI/lab3/robot_wyniki.xml");
        marshaller.marshal(dataList, file);
    }

    private File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File openedFile = fileChooser.showSaveDialog(new Stage());
        return openedFile;
    }


}
