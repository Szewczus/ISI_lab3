package sample;

public class Data {
    private String manufacturer ="";
    private String screenSize ="";
    private String resolution ="";
    private String matrixTexture ="";
    private String touch ="";
    private String processorName ="";
    private String physicalCores ="";
    private String clockSpeed ="";
    private String ram ="";
    private String discSize ="";
    private String discType ="";
    private String graphicCardName ="";
    private String graphicCardMemory ="";
    private String os ="";
    private String discReader ="";

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMatrixTexture() {
        return matrixTexture;
    }

    public void setMatrixTexture(String matrixTexture) {
        this.matrixTexture = matrixTexture;
    }

    public String getTouch() {
        return touch;
    }

    public void setTouch(String touch) {
        this.touch = touch;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public String getPhysicalCores() {
        return physicalCores;
    }

    public void setPhysicalCores(String physicalCores) {
        this.physicalCores = physicalCores;
    }

    public String getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(String clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDiscSize() {
        return discSize;
    }

    public void setDiscSize(String discSize) {
        this.discSize = discSize;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public String getGraphicCardName() {
        return graphicCardName;
    }

    public void setGraphicCardName(String graphicCardName) {
        this.graphicCardName = graphicCardName;
    }

    public String getGraphicCardMemory() {
        return graphicCardMemory;
    }

    public void setGraphicCardMemory(String graphicCardMemory) {
        this.graphicCardMemory = graphicCardMemory;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDiscReader() {
        return discReader;
    }

    public void setDiscReader(String discReader) {
        this.discReader = discReader;
    }

    public String getColumnsValue(){
        return getManufacturer() + ";"
                + getScreenSize()+";"
                + getResolution()+";"
                + getMatrixTexture()+";"
                + getTouch() + ";"
                + getProcessorName() + ";"
                + getPhysicalCores() + ";"
                + getClockSpeed() + ";"
                + getRam() + ";"
                + getDiscSize() + ";"
                + getDiscType() +";"
                + getGraphicCardName() +";"
                + getGraphicCardMemory() + ";"
                + getOs() +";"
                + getDiscReader()+";"+ "\n";

    }


}
