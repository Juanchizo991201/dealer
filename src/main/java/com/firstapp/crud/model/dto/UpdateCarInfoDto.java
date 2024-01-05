package com.firstapp.crud.model.dto;

import lombok.Data;
@Data
public class UpdateCarInfoDto {
    private String registration;
    private String brand;
    private String model;
    private Double displacement;
    private Integer power;
    private String color;
}
