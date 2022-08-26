package com.personalPrueba.crudAutenticacion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
public class Message {

    @NonNull
    private String message;
}
