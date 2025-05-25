package montclio.heimdall.dto.TagRfidDTO;

import montclio.heimdall.model.Motorcycle;

public record PutTagRfidDTO(
        String frequencia,
        String banda,
        String aplicacao,
        Motorcycle motorcycle
) {
}
