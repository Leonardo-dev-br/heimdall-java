package montclio.heimdall.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PostUserDTO(
        Long id,
        @NotBlank( message = "Nome é obrigatório")
        String name,
        @NotBlank( message = "Sobrenome é obrigatório")
        String middleName,
        @NotNull(message = "Data de nascimento é obrigatória")
        @Past(message = "Data de nascimento deve ser no passado")
        LocalDate birthday,
        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
        String cpf,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,
        @NotNull(message = "Categoria do usuário é obrigatória")
        Long userCategoryId
) {

    }

