package com.example.demo.services;

import com.example.demo.entities.DataEntity;
import com.example.demo.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    public List<DataEntity> getAll(){
        return dataRepository.findAll();
    }

    public DataEntity saveAll(DataEntity dataEntity){
        dataRepository.save(dataEntity);
        return dataEntity;
    }
}
