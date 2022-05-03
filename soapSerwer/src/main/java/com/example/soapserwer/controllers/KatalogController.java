package com.example.soapserwer.controllers;


import com.example.soapserwer.services.KatalogService;
import katalog4.GetResponse;
import katalog4.GetRowCountByProducentName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class KatalogController {

    @Autowired
    private KatalogService katalogService;

    @PayloadRoot(namespace = "http://ewa.pl/soap-example", localPart = "getRowCountByProducentName")
    @ResponsePayload
    public GetResponse getRowCountByProducentName(@RequestPayload GetRowCountByProducentName getRowCountByProducentName){
        Long count = katalogService.getRows(getRowCountByProducentName.getNazwa());
        GetResponse getResponse = new GetResponse();
        getResponse.setCount(count);
        return getResponse;
    }
}
