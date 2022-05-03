package com.example.soapserwer.services;

import com.example.soapserwer.repos.KatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KatalogService {

    @Autowired
    private KatalogRepo katalogRepo;

    public Long getRows(String manufacturer){
        Long count = 0L;
        try {
           count= katalogRepo.getCoutRowByManufacturer(manufacturer);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return count;

    }
}
