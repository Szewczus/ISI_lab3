package ewa.example.demo.controllers;

import ewa.example.demo.entities.DataEntity;
import ewa.example.demo.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/getAll")
    ResponseEntity<List<DataEntity>> getAll(){
        return ResponseEntity.ok(dataService.getAll());
    }

    @PostMapping("/saveAll")
    ResponseEntity<DataEntity> saveAll(@RequestBody DataEntity dataEntity){
        return ResponseEntity.ok(dataService.saveAll(dataEntity));
    }


}
