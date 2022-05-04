package com.example.soapserwer.services;

import com.example.soapserwer.entity.KatalogEntity;
import com.example.soapserwer.entity.ResponseEntity1;
import com.example.soapserwer.katalog001.GetRows;
import com.example.soapserwer.repos.KatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KatalogService {

    @Autowired
    private KatalogRepo katalogRepo;

    private GetRows getRows;

    public ResponseEntity1 getRows(GetRows getRows){
        this.getRows = getRows;

        ResponseEntity1 responseEntity1 = new ResponseEntity1();

        if(getRows.getManufacturer()!=null && getRows.getMatrixTexture()!=null && getRows.getProportions()!=null){
            responseEntity1 = getRowsByProducentNameAndMatrixAndProportions(getRows);
        }
        if(getRows.getManufacturer()==null && getRows.getMatrixTexture()!=null && getRows.getProportions()!=null){
            responseEntity1 = getRowsByMatrixAndProportions(getRows);
        }

        if(getRows.getManufacturer()!=null && getRows.getMatrixTexture()==null && getRows.getProportions()!=null){
            responseEntity1 = getRowsByProducentNameAndProportions(getRows);
        }

        if(getRows.getManufacturer()!=null && getRows.getMatrixTexture()!=null && getRows.getProportions()==null){
            responseEntity1 = getRowsByProducentNameAndMatrix(getRows);
        }


        if(getRows.getManufacturer()!=null && getRows.getMatrixTexture()==null && getRows.getProportions()==null){
            responseEntity1 = getRowsByProducentName(getRows.getManufacturer());
        }

        if(getRows.getMatrixTexture()!=null && getRows.getManufacturer()==null && getRows.getProportions()==null){
            responseEntity1 = getRowsByMatrixTexture(getRows.getMatrixTexture());
        }

        if (getRows.getProportions()!=null && getRows.getManufacturer()==null && getRows.getMatrixTexture()==null){
            responseEntity1 = getRowsByProportions(getRows.getProportions());
        }
        return responseEntity1;
    }

    public ResponseEntity1 getRowsByProducentNameAndMatrixAndProportions(GetRows getRows){
        List<KatalogEntity> katalogEntity = new ArrayList<>();
        katalogEntity.addAll(katalogRepo.getRowsByManufacturerAndMatrixTexture(getRows.getManufacturer(), getRows.getMatrixTexture()));

        return filterByProportions(getRows, katalogEntity);
    }

    public ResponseEntity1 getRowsByMatrixAndProportions(GetRows getRows){
        List<KatalogEntity> katalogEntity = new ArrayList<>();
        katalogEntity.addAll(katalogRepo.getRowsByMatrixTexture(getRows.getMatrixTexture()));

        return filterByProportions(getRows, katalogEntity);
    }

    public ResponseEntity1 getRowsByProducentNameAndProportions(GetRows getRows){
        List<KatalogEntity> katalogEntity = new ArrayList<>();
        katalogEntity.addAll(katalogRepo.getRowsByManufacturer(getRows.getManufacturer()));

        return filterByProportions(getRows, katalogEntity);
    }

    private ResponseEntity1 filterByProportions(GetRows getRows, List<KatalogEntity> katalogEntity) {
        String[] proportionsArray = getRows.getProportions().split("x");
        ResponseEntity1 responseEntity1 = new ResponseEntity1();
        getResponseBasedOnProportions(proportionsArray, responseEntity1, katalogEntity);
        if(getRows.getProportions()!=null){
            responseEntity1.setCountComputersByProportions((long) getRowsByProportions(this.getRows.getProportions()).getComputer().size());
        }
        if(getRows.getManufacturer()!=null){
            responseEntity1.setCountComputersByManufacturer((long) getRowsByProducentName(this.getRows.getManufacturer()).getComputer().size());
        }
        if(getRows.getMatrixTexture()!=null){
            responseEntity1.setCountComputersByMatrixType((long) getRowsByMatrixTexture(this.getRows.getMatrixTexture()).getComputer().size());
        }
        return responseEntity1;
    }

    public ResponseEntity1 getRowsByProducentNameAndMatrix(GetRows getRows){
        List<KatalogEntity> katalogEntity = new ArrayList<>();
        katalogEntity.addAll(katalogRepo.getRowsByManufacturerAndMatrixTexture(getRows.getManufacturer(), getRows.getMatrixTexture()));
        ResponseEntity1 responseEntity1 = new ResponseEntity1();
        responseEntity1.setCountComputersByManufacturer((long) getRowsByProducentName(this.getRows.getManufacturer()).getComputer().size());
        responseEntity1.setCountComputersByMatrixType((long)getRowsByMatrixTexture(this.getRows.getMatrixTexture()).getComputer().size());
        responseEntity1.setComputer(katalogEntity);
        return responseEntity1;
    }



    public ResponseEntity1 getRowsByProducentName(String manufacturer){
        Long count = 0L;
        try {
           count= katalogRepo.getCoutRowByManufacturer(manufacturer);
        }
        catch (Exception e){
            System.out.println(e);
        }

        ResponseEntity1 responseEntity1 = new ResponseEntity1();
        responseEntity1.setCountComputersByManufacturer(count);
        responseEntity1.setComputer(katalogRepo.getRowsByManufacturer(manufacturer));
        return responseEntity1;
    }

    public ResponseEntity1 getRowsByMatrixTexture(String matrixTexture){
        ResponseEntity1 responseEntity1 = new ResponseEntity1();
        responseEntity1.setComputer(katalogRepo.getRowsByMatrixTexture(matrixTexture));
        responseEntity1.setCountComputersByMatrixType(Long.parseLong(String.valueOf(katalogRepo.getRowsByMatrixTexture(matrixTexture).size())));
        return responseEntity1;
    }

    public ResponseEntity1 getRowsByProportions(String proportions){
        String[] proportionsArray = proportions.split("x");
        ResponseEntity1 responseEntity1 = new ResponseEntity1();
        List<KatalogEntity> katalogEntityList = katalogRepo.getAll();
        return getResponseBasedOnProportions(proportionsArray, responseEntity1, katalogEntityList);
    }

    private ResponseEntity1 getResponseBasedOnProportions(String[] proportionsArray, ResponseEntity1 responseEntity1, List<KatalogEntity> katalogEntityList) {
        List<KatalogEntity> katalogEntityResoultList = new ArrayList<>();
        for(KatalogEntity katalogEntity : katalogEntityList){
            String resolution = katalogEntity.getResolution();
            if(resolution==null || resolution.equals("")){
                continue;
            }
            String[] resolutions = resolution.split("x");

            int solution1 = Integer.parseInt(resolutions[0])/Integer.parseInt(proportionsArray[0]);
            int solution2 = Integer.parseInt(resolutions[1])/Integer.parseInt(proportionsArray[1]);

            int solution3 = Integer.parseInt(resolutions[0])/Integer.parseInt(proportionsArray[1]);
            int solution4 = Integer.parseInt(resolutions[1])/Integer.parseInt(proportionsArray[0]);

            if(solution1 == solution2 || solution3 == solution4){
                katalogEntityResoultList.add(katalogEntity);
            }
        }
        responseEntity1.setComputer(katalogEntityResoultList);
        return responseEntity1;
    }


}
