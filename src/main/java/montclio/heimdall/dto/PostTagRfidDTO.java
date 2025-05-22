package montclio.heimdall.dto;

import jakarta.validation.constraints.NotBlank;
import montclio.heimdall.model.Motorcycle;

public record PostTagRfidDTO(

        Long id,
        @NotBlank(message = "A frequência é obrigatória")
        String frequencia,
        @NotBlank(message = "A banda é obrigatória")
        String banda,
        @NotBlank(message = "A aplicação é obrigatória")
        String aplicacao,
        Motorcycle motorcycle
) {

}
