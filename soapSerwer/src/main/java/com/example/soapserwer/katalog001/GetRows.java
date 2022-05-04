
package com.example.soapserwer.katalog001;

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
 *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="matrixTexture" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="proportions" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "manufacturer",
    "matrixTexture",
    "proportions"
})
@XmlRootElement(name = "getRows", namespace = "http://ewa.pl/soap-example")
public class GetRows {

    @XmlElement(required = true)
    protected String manufacturer;
    @XmlElement(required = true)
    protected String matrixTexture;
    @XmlElement(required = true)
    protected String proportions;

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the matrixTexture property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatrixTexture() {
        return matrixTexture;
    }

    /**
     * Sets the value of the matrixTexture property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatrixTexture(String value) {
        this.matrixTexture = value;
    }

    /**
     * Gets the value of the proportions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProportions() {
        return proportions;
    }

    /**
     * Sets the value of the proportions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProportions(String value) {
        this.proportions = value;
    }

}
