package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class DOMBuider {
    public void saveToXML(List<Data> dataList){
        DocumentBuilder builder;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element laptops = doc.createElement("laptops");
            laptops.setAttribute("moddate", new Date().toString());

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
}
