package com.example.soapserwer.controllers;


import com.example.soapserwer.entity.KatalogEntity;
import com.example.soapserwer.entity.ResponseEntity1;
import com.example.soapserwer.katalog001.GetResponse;
import com.example.soapserwer.katalog001.GetRows;
import com.example.soapserwer.services.KatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class KatalogController {

    @Autowired
    private KatalogService katalogService;

    @PayloadRoot(namespace = "http://ewa.pl/soap-example", localPart = "getRows")
    @ResponsePayload
    public GetResponse getRows(@RequestPayload GetRows getRows){
        GetResponse getResponse = new GetResponse();
        ResponseEntity1 responseEntity1 = katalogService.getRows(getRows);
        if(getRows.getManufacturer()!=null){
            getResponse.setCountComputersByManufacturer(responseEntity1.getCountComputersByManufacturer());
        }
        if(getRows.getMatrixTexture()!=null){
            getResponse.setCountComputersByMatrixType(responseEntity1.getCountComputersByMatrixType());
        }
        if(getRows.getProportions()!=null){
            getResponse.setCountComputersByProportions(responseEntity1.getCountComputersByProportions());
        }
        mapToComputerList(responseEntity1, getResponse);
        return  getResponse;
    }


    private void mapToComputerList(ResponseEntity1 responseEntity1, GetResponse getResponse) {
        for(KatalogEntity entity : responseEntity1.getComputer()){
            GetResponse.ComputerList computerList = new GetResponse.ComputerList();
            computerList.setId(entity.getId());
            computerList.setManufacturer(entity.getManufacturer());
            computerList.setScreenSize(entity.getScreenSize());
            computerList.setResolution(entity.getResolution());
            computerList.setMatrixTexture(entity.getMatrixTexture());
            computerList.setTouch(entity.getTouch());
            computerList.setProcessorName(entity.getProcessorName());
            computerList.setPhysicalCores(entity.getPhysicalCores());
            computerList.setClockSpeed(entity.getClockSpeed());
            computerList.setRam(entity.getRam());
            computerList.setDiscSize(entity.getDiscSize());
            computerList.setDiscType(entity.getDiscType());
            computerList.setGraphicCardName(entity.getGraphicCardName());
            computerList.setGraphicCardMemory(entity.getGraphicCardMemory());
            computerList.setOs(entity.getOs());
            computerList.setDiscReader(entity.getDiscReader());
            getResponse.getComputerList().add(computerList);
        }
    }
}
