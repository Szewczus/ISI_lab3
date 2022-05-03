package com.example.soapserwer.entity;

import javax.persistence.Entity;
import java.util.List;

public class ResponseEntity1 {
    private Long count;
    private List<KatalogEntity> computer;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<KatalogEntity> getComputer() {
        return computer;
    }

    public void setComputer(List<KatalogEntity> computer) {
        this.computer = computer;
    }
}
