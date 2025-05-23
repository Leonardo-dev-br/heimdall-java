package montclio.heimdall.dto;

import montclio.heimdall.model.Motorcycle;

public record PutTagRfidDTO(
        String frequencia,
        String banda,
        String aplicacao,
        Motorcycle motorcycle
) {
}
