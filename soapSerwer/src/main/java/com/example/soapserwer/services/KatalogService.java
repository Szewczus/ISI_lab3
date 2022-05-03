package com.example.soapserwer.services;

import com.example.soapserwer.entity.KatalogEntity;
import com.example.soapserwer.entity.ResponseEntity1;
import com.example.soapserwer.repos.KatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KatalogService {

    @Autowired
    private KatalogRepo katalogRepo;

    public ResponseEntity1 getRows(String manufacturer){
        Long count = 0L;
        try {
           count= katalogRepo.getCoutRowByManufacturer(manufacturer);
        }
        catch (Exception e){
            System.out.println(e);
        }

        ResponseEntity1 responseEntity1 = new ResponseEntity1();
        responseEntity1.setCount(count);
        responseEntity1.setComputer(katalogRepo.getRowsByManufacturer(manufacturer));
        return responseEntity1;

    }
}
