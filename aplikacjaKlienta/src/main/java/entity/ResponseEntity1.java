package entity;

import front.DataDto;

import java.util.List;

public class ResponseEntity1 {
    private Long countComputersByManufacturer;
    private Long countComputersByMatrixType;
    private Long countComputersByProportions;
    private List<DataDto> computer;

    public Long getCountComputersByManufacturer() {
        return countComputersByManufacturer;
    }

    public void setCountComputersByManufacturer(Long countComputersByManufacturer) {
        this.countComputersByManufacturer = countComputersByManufacturer;
    }

    public Long getCountComputersByMatrixType() {
        return countComputersByMatrixType;
    }

    public void setCountComputersByMatrixType(Long countComputersByMatrixType) {
        this.countComputersByMatrixType = countComputersByMatrixType;
    }

    public Long getCountComputersByProportions() {
        return countComputersByProportions;
    }

    public void setCountComputersByProportions(Long countComputersByProportions) {
        this.countComputersByProportions = countComputersByProportions;
    }

    public List<DataDto> getComputer() {
        return computer;
    }

    public void setComputer(List<DataDto> computer) {
        this.computer = computer;
    }
}
