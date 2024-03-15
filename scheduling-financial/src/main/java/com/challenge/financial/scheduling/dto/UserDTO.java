package com.challenge.financial.scheduling.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = "Campo requerido")
    private String name;
    @NotBlank(message = "Campo requerido")
    private String lastName;
    @NotBlank(message = "Campo requerido")
    private String age;

}
