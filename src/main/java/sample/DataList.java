package sample;

import sample.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DataList {
    List<Data> laptop;

    public DataList() {
    }

    public DataList(List<Data> laptop) {
        this.laptop = laptop;
    }

    public List<Data> getLaptop() {
        return laptop;
    }

    public void setLaptop(List<Data> laptop) {
        this.laptop = laptop;
    }
}
