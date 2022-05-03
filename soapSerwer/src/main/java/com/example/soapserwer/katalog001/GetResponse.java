
package com.example.soapserwer.katalog001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="computerList" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="screenSize" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="resolution" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="matrixTexture" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="touch" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="processorName" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="physicalCores" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="clockSpeed" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="ram" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="discSize" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="discType" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="graphicCardName" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="graphicCardMemory" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="os" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="discReader" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "count",
    "computerList"
})
@XmlRootElement(name = "getResponse", namespace = "http://ewa.pl/soap-example")
public class GetResponse {

    protected long count;
    protected List<GetResponse.ComputerList> computerList;

    /**
     * Gets the value of the count property.
     * 
     */
    public long getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     */
    public void setCount(long value) {
        this.count = value;
    }

    /**
     * Gets the value of the computerList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the computerList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComputerList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetResponse.ComputerList }
     * 
     * 
     */
    public List<GetResponse.ComputerList> getComputerList() {
        if (computerList == null) {
            computerList = new ArrayList<GetResponse.ComputerList>();
        }
        return this.computerList;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="screenSize" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="resolution" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="matrixTexture" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="touch" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="processorName" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="physicalCores" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="clockSpeed" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="ram" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="discSize" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="discType" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="graphicCardName" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="graphicCardMemory" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="os" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="discReader" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "manufacturer",
        "screenSize",
        "resolution",
        "matrixTexture",
        "touch",
        "processorName",
        "physicalCores",
        "clockSpeed",
        "ram",
        "discSize",
        "discType",
        "graphicCardName",
        "graphicCardMemory",
        "os",
        "discReader"
    })
    public static class ComputerList {

        @XmlElement(required = true)
        protected Object id;
        @XmlElement(required = true)
        protected Object manufacturer;
        @XmlElement(required = true)
        protected Object screenSize;
        @XmlElement(required = true)
        protected Object resolution;
        @XmlElement(required = true)
        protected Object matrixTexture;
        @XmlElement(required = true)
        protected Object touch;
        @XmlElement(required = true)
        protected Object processorName;
        @XmlElement(required = true)
        protected Object physicalCores;
        @XmlElement(required = true)
        protected Object clockSpeed;
        @XmlElement(required = true)
        protected Object ram;
        @XmlElement(required = true)
        protected Object discSize;
        @XmlElement(required = true)
        protected Object discType;
        @XmlElement(required = true)
        protected Object graphicCardName;
        @XmlElement(required = true)
        protected Object graphicCardMemory;
        @XmlElement(required = true)
        protected Object os;
        @XmlElement(required = true)
        protected Object discReader;

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setId(Object value) {
            this.id = value;
        }

        /**
         * Gets the value of the manufacturer property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getManufacturer() {
            return manufacturer;
        }

        /**
         * Sets the value of the manufacturer property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setManufacturer(Object value) {
            this.manufacturer = value;
        }

        /**
         * Gets the value of the screenSize property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getScreenSize() {
            return screenSize;
        }

        /**
         * Sets the value of the screenSize property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setScreenSize(Object value) {
            this.screenSize = value;
        }

        /**
         * Gets the value of the resolution property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getResolution() {
            return resolution;
        }

        /**
         * Sets the value of the resolution property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setResolution(Object value) {
            this.resolution = value;
        }

        /**
         * Gets the value of the matrixTexture property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getMatrixTexture() {
            return matrixTexture;
        }

        /**
         * Sets the value of the matrixTexture property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setMatrixTexture(Object value) {
            this.matrixTexture = value;
        }

        /**
         * Gets the value of the touch property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getTouch() {
            return touch;
        }

        /**
         * Sets the value of the touch property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setTouch(Object value) {
            this.touch = value;
        }

        /**
         * Gets the value of the processorName property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getProcessorName() {
            return processorName;
        }

        /**
         * Sets the value of the processorName property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setProcessorName(Object value) {
            this.processorName = value;
        }

        /**
         * Gets the value of the physicalCores property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getPhysicalCores() {
            return physicalCores;
        }

        /**
         * Sets the value of the physicalCores property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setPhysicalCores(Object value) {
            this.physicalCores = value;
        }

        /**
         * Gets the value of the clockSpeed property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getClockSpeed() {
            return clockSpeed;
        }

        /**
         * Sets the value of the clockSpeed property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setClockSpeed(Object value) {
            this.clockSpeed = value;
        }

        /**
         * Gets the value of the ram property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getRam() {
            return ram;
        }

        /**
         * Sets the value of the ram property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setRam(Object value) {
            this.ram = value;
        }

        /**
         * Gets the value of the discSize property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getDiscSize() {
            return discSize;
        }

        /**
         * Sets the value of the discSize property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setDiscSize(Object value) {
            this.discSize = value;
        }

        /**
         * Gets the value of the discType property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getDiscType() {
            return discType;
        }

        /**
         * Sets the value of the discType property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setDiscType(Object value) {
            this.discType = value;
        }

        /**
         * Gets the value of the graphicCardName property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getGraphicCardName() {
            return graphicCardName;
        }

        /**
         * Sets the value of the graphicCardName property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setGraphicCardName(Object value) {
            this.graphicCardName = value;
        }

        /**
         * Gets the value of the graphicCardMemory property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getGraphicCardMemory() {
            return graphicCardMemory;
        }

        /**
         * Sets the value of the graphicCardMemory property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setGraphicCardMemory(Object value) {
            this.graphicCardMemory = value;
        }

        /**
         * Gets the value of the os property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getOs() {
            return os;
        }

        /**
         * Sets the value of the os property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setOs(Object value) {
            this.os = value;
        }

        /**
         * Gets the value of the discReader property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getDiscReader() {
            return discReader;
        }

        /**
         * Sets the value of the discReader property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setDiscReader(Object value) {
            this.discReader = value;
        }

    }

}
