package ewa.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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



}
