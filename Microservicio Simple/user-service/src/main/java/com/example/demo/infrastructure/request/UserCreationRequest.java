package com.example.demo.infrastructure.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserCreationRequest {
	
	@NotBlank(message = "El nombre es obligatorio")
	private String name;
	
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email no tiene un formato válido")
	private String email;
	
	@NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$",
            message = "La contraseña debe tener exactamente 8 caracteres, incluir al menos una letra y un número"
        )
	private String password;

}
