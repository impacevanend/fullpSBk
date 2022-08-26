package com.personalPrueba.crudAutenticacion.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@ToString
public class ProductDto {

    @NotBlank
    private String name;
    @Min(0)
    private Float price;

}
